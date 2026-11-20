package com.example.data.local

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.OpenableColumns
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

sealed interface ImageValidationResult {
    data class Valid(
        val localFilePath: String,
        val bitmap: Bitmap,
        val displaySizeKb: Long,
        val mimeType: String
    ) : ImageValidationResult

    data class Invalid(val errorMessage: String) : ImageValidationResult
}

object ImageStorageHelper {
    private const val MAX_RAW_BYTES = 15L * 1024L * 1024L // 15 MB input guard
    private const val MAX_DIMENSION = 1280
    private val SUPPORTED_MIME_TYPES = setOf(
        "image/jpeg",
        "image/jpg",
        "image/png",
        "image/webp"
    )

    fun createCameraCaptureFileAndUri(context: Context): Pair<File, Uri> {
        val captureDir = File(context.cacheDir, "leaf_captures").apply { mkdirs() }
        val file = File(captureDir, "leaf_${System.currentTimeMillis()}.jpg")
        val uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.fileprovider",
            file
        )
        return file to uri
    }

    suspend fun validateAndPrepareImage(
        context: Context,
        uri: Uri,
        subfolder: String = "leaf_history"
    ): ImageValidationResult = withContext(Dispatchers.IO) {
        try {
            val resolver = context.contentResolver
            val mimeType = resolver.getType(uri)?.lowercase() ?: "image/jpeg"

            if (mimeType !in SUPPORTED_MIME_TYPES && !mimeType.startsWith("image/")) {
                return@withContext ImageValidationResult.Invalid(
                    "Unsupported file format ($mimeType). Please choose a JPG, PNG, or WEBP leaf image."
                )
            }

            if (mimeType !in SUPPORTED_MIME_TYPES) {
                return@withContext ImageValidationResult.Invalid(
                    "Unsupported image format ($mimeType). Please select a JPG, PNG, or WEBP photo."
                )
            }

            // Check file size if available
            var rawSizeBytes = -1L
            resolver.query(uri, arrayOf(OpenableColumns.SIZE), null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val idx = cursor.getColumnIndex(OpenableColumns.SIZE)
                    if (idx >= 0 && !cursor.isNull(idx)) {
                        rawSizeBytes = cursor.getLong(idx)
                    }
                }
            }

            if (rawSizeBytes > MAX_RAW_BYTES) {
                return@withContext ImageValidationResult.Invalid(
                    "Image is larger than 15 MB. Please select a smaller photograph."
                )
            }

            // Decode bounds first
            val boundsOptions = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            resolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, boundsOptions)
            }

            if (boundsOptions.outWidth <= 0 || boundsOptions.outHeight <= 0) {
                return@withContext ImageValidationResult.Invalid(
                    "Could not read the selected image. Please try another leaf photograph."
                )
            }

            // Compute inSampleSize
            var sampleSize = 1
            val maxSide = maxOf(boundsOptions.outWidth, boundsOptions.outHeight)
            while (maxSide / sampleSize > MAX_DIMENSION * 2) {
                sampleSize *= 2
            }

            val decodeOptions = BitmapFactory.Options().apply { inSampleSize = sampleSize }
            val decoded = resolver.openInputStream(uri)?.use { stream ->
                BitmapFactory.decodeStream(stream, null, decodeOptions)
            } ?: return@withContext ImageValidationResult.Invalid(
                "Unable to decode image data. Please select a valid JPG or PNG photo."
            )

            // Apply EXIF orientation if available
            val oriented = applyExifOrientation(context, uri, decoded)

            // Scale down cleanly to <= MAX_DIMENSION on longest side
            val scaled = scaleBitmapIfNeeded(oriented, MAX_DIMENSION)
            if (scaled != oriented && !oriented.isRecycled) {
                oriented.recycle()
            }

            // Save persistent compressed JPEG under app filesDir
            val targetDir = File(context.filesDir, subfolder).apply { mkdirs() }
            val outFile = File(targetDir, "leaf_${UUID.randomUUID()}.jpg")
            FileOutputStream(outFile).use { fos ->
                scaled.compress(Bitmap.CompressFormat.JPEG, 88, fos)
            }

            val sizeKb = (outFile.length() / 1024L).coerceAtLeast(1L)
            if (outFile.length() > MAX_RAW_BYTES) {
                outFile.delete()
                return@withContext ImageValidationResult.Invalid(
                    "Processed image still exceeds size limits. Please choose a smaller photo."
                )
            }

            ImageValidationResult.Valid(
                localFilePath = outFile.absolutePath,
                bitmap = scaled,
                displaySizeKb = sizeKb,
                mimeType = mimeType
            )
        } catch (e: Exception) {
            ImageValidationResult.Invalid(
                e.localizedMessage ?: "Failed to load selected image."
            )
        }
    }

    suspend fun loadBitmapFromPath(path: String): Bitmap? = withContext(Dispatchers.IO) {
        try {
            val file = File(path)
            if (!file.exists()) return@withContext null
            BitmapFactory.decodeFile(file.absolutePath)
        } catch (_: Exception) {
            null
        }
    }

    fun deleteFileQuietly(path: String?) {
        if (path.isNullOrBlank()) return
        try {
            File(path).delete()
        } catch (_: Exception) {
        }
    }

    private fun applyExifOrientation(context: Context, uri: Uri, bitmap: Bitmap): Bitmap {
        return try {
            val orientation = context.contentResolver.openInputStream(uri)?.use { stream ->
                ExifInterface(stream).getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL
                )
            } ?: ExifInterface.ORIENTATION_NORMAL

            val matrix = Matrix()
            when (orientation) {
                ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
                ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
                ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
                ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.postScale(-1f, 1f)
                ExifInterface.ORIENTATION_FLIP_VERTICAL -> matrix.postScale(1f, -1f)
                else -> return bitmap
            }
            val rotated = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            if (rotated != bitmap && !bitmap.isRecycled) {
                bitmap.recycle()
            }
            rotated
        } catch (_: Exception) {
            bitmap
        }
    }

    private fun scaleBitmapIfNeeded(bitmap: Bitmap, maxDimension: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height
        val longest = maxOf(width, height)
        if (longest <= maxDimension) return bitmap
        val ratio = maxDimension.toFloat() / longest.toFloat()
        val newWidth = (width * ratio).toInt().coerceAtLeast(1)
        val newHeight = (height * ratio).toInt().coerceAtLeast(1)
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }
}
