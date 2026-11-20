package com.example.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Grass
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalFlorist
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.data.model.CropCatalogRepository
import com.example.data.model.CropCategory
import com.example.data.model.DiagnosisAssessment
import com.example.ui.theme.ForestGreen
import com.example.ui.theme.ForestGreenDark
import com.example.ui.theme.HarvestAmber
import com.example.ui.theme.HarvestAmberContainer
import com.example.ui.theme.HarvestAmberText
import com.example.ui.theme.LeafGreen
import com.example.ui.theme.LightGreenSurface
import com.example.ui.theme.MintGreen
import com.example.ui.theme.SageGreen
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AgriLensLogoMark(
    modifier: Modifier = Modifier,
    size: Dp = 36.dp
) {
    Canvas(modifier = modifier.size(size)) {
        val w = this.size.width
        val h = this.size.height
        val center = Offset(w / 2f, h / 2f)

        // Deep forest green badge circle
        drawCircle(
            brush = Brush.linearGradient(listOf(ForestGreen, LeafGreen)),
            radius = w * 0.48f,
            center = center
        )

        // Precision lens ring
        drawCircle(
            color = MintGreen,
            radius = w * 0.34f,
            center = center,
            style = Stroke(width = w * 0.065f)
        )

        // Botanical leaf silhouette
        val leafPath = Path().apply {
            moveTo(w * 0.30f, h * 0.68f)
            cubicTo(
                w * 0.30f, h * 0.42f,
                w * 0.44f, h * 0.28f,
                w * 0.72f, h * 0.27f
            )
            cubicTo(
                w * 0.71f, h * 0.55f,
                w * 0.56f, h * 0.69f,
                w * 0.30f, h * 0.68f
            )
            close()
        }
        drawPath(path = leafPath, color = Color.White)

        // Leaf midrib vein
        drawLine(
            color = ForestGreen,
            start = Offset(w * 0.34f, h * 0.64f),
            end = Offset(w * 0.60f, h * 0.39f),
            strokeWidth = w * 0.055f,
            cap = StrokeCap.Round
        )
    }
}

@Composable
fun HeroBotanicalBannerArtwork(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Sunlit forest gradient backdrop
        drawRoundRect(
            brush = Brush.linearGradient(
                colors = listOf(ForestGreenDark, ForestGreen, LeafGreen),
                start = Offset(0f, 0f),
                end = Offset(w, h)
            ),
            cornerRadius = CornerRadius(28f, 28f)
        )

        // Subtle atmospheric botanical glow circles
        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(MintGreen.copy(alpha = 0.28f), Color.Transparent),
                center = Offset(w * 0.84f, h * 0.22f),
                radius = w * 0.42f
            ),
            radius = w * 0.42f,
            center = Offset(w * 0.84f, h * 0.22f)
        )

        // Rolling crop terrace hills
        val backHill = Path().apply {
            moveTo(0f, h * 0.78f)
            quadraticTo(w * 0.32f, h * 0.58f, w * 0.68f, h * 0.74f)
            quadraticTo(w * 0.86f, h * 0.82f, w, h * 0.68f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(backHill, color = SageGreen.copy(alpha = 0.26f))

        val frontHill = Path().apply {
            moveTo(0f, h * 0.86f)
            quadraticTo(w * 0.45f, h * 0.72f, w, h * 0.88f)
            lineTo(w, h)
            lineTo(0f, h)
            close()
        }
        drawPath(frontHill, color = MintGreen.copy(alpha = 0.22f))

        // Stylized large botanical leaf on right with AI lens reticle
        val leafCx = w * 0.82f
        val leafCy = h * 0.42f
        val leafR = minOf(w, h) * 0.27f

        drawCircle(
            color = MintGreen.copy(alpha = 0.35f),
            radius = leafR * 1.15f,
            center = Offset(leafCx, leafCy),
            style = Stroke(width = 3f)
        )
        // Corner scan brackets
        val b = leafR * 1.32f
        val arm = leafR * 0.35f
        val bracketColor = MintGreen.copy(alpha = 0.8f)
        // Top-left bracket
        drawLine(bracketColor, Offset(leafCx - b, leafCy - b), Offset(leafCx - b + arm, leafCy - b), 3.5f, StrokeCap.Round)
        drawLine(bracketColor, Offset(leafCx - b, leafCy - b), Offset(leafCx - b, leafCy - b + arm), 3.5f, StrokeCap.Round)
        // Bottom-right bracket
        drawLine(bracketColor, Offset(leafCx + b, leafCy + b), Offset(leafCx + b - arm, leafCy + b), 3.5f, StrokeCap.Round)
        drawLine(bracketColor, Offset(leafCx + b, leafCy + b), Offset(leafCx + b, leafCy + b - arm), 3.5f, StrokeCap.Round)

        val heroLeaf = Path().apply {
            moveTo(leafCx - leafR * 0.65f, leafCy + leafR * 0.62f)
            cubicTo(
                leafCx - leafR * 0.65f, leafCy - leafR * 0.35f,
                leafCx - leafR * 0.1f, leafCy - leafR * 0.72f,
                leafCx + leafR * 0.72f, leafCy - leafR * 0.70f
            )
            cubicTo(
                leafCx + leafR * 0.68f, leafCy + leafR * 0.25f,
                leafCx + leafR * 0.15f, leafCy + leafR * 0.68f,
                leafCx - leafR * 0.65f, leafCy + leafR * 0.62f
            )
            close()
        }
        drawPath(heroLeaf, color = Color.White.copy(alpha = 0.92f))
        drawLine(
            color = ForestGreen,
            start = Offset(leafCx - leafR * 0.50f, leafCy + leafR * 0.48f),
            end = Offset(leafCx + leafR * 0.42f, leafCy - leafR * 0.42f),
            strokeWidth = 4f,
            cap = StrokeCap.Round
        )
    }
}

fun categoryIcon(category: CropCategory): ImageVector = when (category) {
    CropCategory.VEGETABLES -> Icons.Filled.Eco
    CropCategory.FRUITS -> Icons.Filled.LocalFlorist
    CropCategory.CEREALS -> Icons.Filled.Grass
    CropCategory.PULSES -> Icons.Filled.Spa
    CropCategory.CASH_CROPS -> Icons.Filled.Shield
}

@Composable
fun CropSupportDistributionChart(modifier: Modifier = Modifier) {
    val total = CropCatalogRepository.allCrops.size.coerceAtLeast(1)
    val categories = CropCategory.entries

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Supported Crop Coverage",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "$total curated crops across 5 agricultural categories",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = "$total Crops",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            categories.forEach { category ->
                val count = CropCatalogRepository.cropsByCategory(category).size
                val fraction = count.toFloat() / total.toFloat()
                Column(modifier = Modifier.padding(vertical = 5.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = categoryIcon(category),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = category.displayName,
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Text(
                            text = "$count crops",
                            style = MaterialTheme.typography.labelMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction.coerceIn(0.08f, 1f))
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(
                                    Brush.horizontalGradient(listOf(ForestGreen, SageGreen))
                                )
                        )
                    }
                }
            }
        }
    }
}

data class WorkflowStage(
    val stepNumber: Int,
    val title: String,
    val detail: String,
    val isImplemented: Boolean
)

val agriLensWorkflowStages = listOf(
    WorkflowStage(1, "Capture Image", "Smartphone camera or gallery upload", true),
    WorkflowStage(2, "Pre-processing", "Format, size check & resolution scaling", true),
    WorkflowStage(3, "Feature Extraction", "Planned OpenCV & convolutional layers", false),
    WorkflowStage(4, "AI Analysis", "Live Gemini multimodal leaf screening", true),
    WorkflowStage(5, "Disease Classification", "Planned trained CNN model inference", false),
    WorkflowStage(6, "Guidance", "Symptoms, prevention & KVK next steps", true)
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SystemWorkflowDiagram(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            agriLensWorkflowStages.forEach { stage ->
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = if (stage.isImplemented) {
                        MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.65f)
                    } else {
                        HarvestAmberContainer.copy(alpha = 0.7f)
                    },
                    border = BorderStroke(
                        1.dp,
                        if (stage.isImplemented) MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)
                        else HarvestAmber.copy(alpha = 0.35f)
                    ),
                    modifier = Modifier.width(164.dp)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Step ${stage.stepNumber}",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (stage.isImplemented) MaterialTheme.colorScheme.primary else HarvestAmberText
                            )
                            Surface(
                                shape = CircleShape,
                                color = if (stage.isImplemented) ForestGreen else HarvestAmber
                            ) {
                                Text(
                                    text = if (stage.isImplemented) "Live" else "Planned",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = Color.White,
                                    modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = stage.title,
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = stage.detail,
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DiagnosisResultView(
    assessment: DiagnosisAssessment,
    onAskExpert: () -> Unit,
    onAnalyzeAnother: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val isInconclusive = assessment.assessmentStatus.contains("Unknown", ignoreCase = true) ||
        assessment.assessmentStatus.contains("Inconclusive", ignoreCase = true)
    val isHealthy = assessment.assessmentStatus.contains("Healthy", ignoreCase = true)

    val statusContainerColor = when {
        isInconclusive -> HarvestAmberContainer
        isHealthy -> LightGreenSurface
        else -> MaterialTheme.colorScheme.primaryContainer
    }
    val statusContentColor = when {
        isInconclusive -> HarvestAmberText
        else -> ForestGreenDark
    }
    val statusIcon = when {
        isInconclusive -> Icons.Filled.HelpOutline
        isHealthy -> Icons.Filled.CheckCircle
        else -> Icons.Filled.WarningAmber
    }

    val formattedDate = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        .format(Date(assessment.timestamp))

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Header + Uploaded Leaf Photo + Preliminary Badge
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${assessment.cropName} (${assessment.cropCategory})",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = "Screened on $formattedDate",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(50),
                        color = statusContainerColor
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = statusIcon,
                                contentDescription = null,
                                tint = statusContentColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = assessment.assessmentStatus,
                                style = MaterialTheme.typography.labelMedium,
                                color = statusContentColor
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                if (assessment.imageFilePath.isNotBlank() && File(assessment.imageFilePath).exists()) {
                    AsyncImage(
                        model = File(assessment.imageFilePath),
                        contentDescription = "Uploaded ${assessment.cropName} leaf photograph",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(210.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                }

                Text(
                    text = "Possible Condition / Finding",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = assessment.possibleCondition,
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(10.dp))
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.55f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Image Clarity & AI Assessment Note",
                                style = MaterialTheme.typography.labelMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = assessment.imageClarityNote,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        // Structured Findings Cards
        ResultSectionCard(
            title = "Observed Visible Symptoms",
            icon = Icons.Filled.Eco,
            items = assessment.observedSymptoms,
            emptyText = "No specific foliar symptoms could be isolated from the image."
        )

        ResultSectionCard(
            title = "Possible Causes",
            icon = Icons.Outlined.Lightbulb,
            items = assessment.possibleCauses,
            emptyText = "Unable to narrow down specific causes without clearer leaf detail."
        )

        ResultSectionCard(
            title = "General Prevention Guidance",
            icon = Icons.Filled.Shield,
            items = assessment.preventionGuidance,
            emptyText = "Maintain clean field sanitation, balanced watering, and regular scouting."
        )

        ResultSectionCard(
            title = "Recommended Next Steps",
            icon = Icons.AutoMirrored.Filled.ArrowForward,
            items = assessment.recommendedNextSteps,
            emptyText = "Retake a sharp daylight photo of the leaf and consult your local KVK."
        )

        // Assessment Limitations & CNN Distinction Notice
        Surface(
            shape = MaterialTheme.shapes.medium,
            color = HarvestAmberContainer.copy(alpha = 0.65f),
            border = BorderStroke(1.dp, HarvestAmber.copy(alpha = 0.4f))
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp),
                verticalAlignment = Alignment.Top
            ) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = null,
                    tint = HarvestAmberText,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = "Assessment Limitations & Model Transparency",
                        style = MaterialTheme.typography.labelLarge,
                        color = HarvestAmberText
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = assessment.limitationsDisclaimer +
                            " Note: This screening uses Gemini multimodal image understanding—not a trained CNN classifier—and carries no fabricated confidence score. Verify uncertain cases with a local agronomist or KVK.",
                        style = MaterialTheme.typography.bodySmall,
                        color = HarvestAmberText
                    )
                }
            }
        }

        // Action Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = onAskExpert,
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .testTag("result_ask_expert_button"),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Filled.Psychology,
                    contentDescription = null,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ask AI Crop Expert")
            }

            if (onAnalyzeAnother != null) {
                OutlinedButton(
                    onClick = onAnalyzeAnother,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("analyze_another_leaf_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Analyse Another")
                }
            }
        }
    }
}

@Composable
private fun ResultSectionCard(
    title: String,
    icon: ImageVector,
    items: List<String>,
    emptyText: String
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(10.dp))
            if (items.isEmpty()) {
                Text(
                    text = emptyText,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    items.forEach { point ->
                        Row(verticalAlignment = Alignment.Top) {
                            Box(
                                modifier = Modifier
                                    .padding(top = 7.dp)
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(MaterialTheme.colorScheme.primary)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = point,
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
