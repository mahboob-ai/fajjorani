package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.example.data.model.CropCatalogRepository
import com.example.data.model.CropCategory
import com.example.data.model.CropInfo
import com.example.ui.LibraryUiState
import com.example.ui.components.categoryIcon

@Composable
fun LibraryScreen(
    uiState: LibraryUiState,
    onUpdateSearch: (String) -> Unit,
    onSelectCategory: (CropCategory?) -> Unit,
    onSelectCrop: (CropInfo?) -> Unit,
    onDiagnoseCrop: (CropInfo) -> Unit,
    onAskExpertAboutCrop: (CropInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    val filteredCrops = remember(uiState.searchQuery, uiState.selectedCategory) {
        CropCatalogRepository.searchCrops(
            query = uiState.searchQuery,
            category = uiState.selectedCategory
        )
    }

    BoxWithConstraints(modifier = modifier.fillMaxSize()) {
        val isWide = maxWidth >= 720.dp

        if (isWide) {
            // Canonical Tablet List-Detail Layout
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CropListPane(
                    crops = filteredCrops,
                    uiState = uiState,
                    onUpdateSearch = onUpdateSearch,
                    onSelectCategory = onSelectCategory,
                    onSelectCrop = onSelectCrop,
                    modifier = Modifier
                        .weight(0.45f)
                        .fillMaxHeight()
                )

                val detailCrop = uiState.selectedCrop ?: filteredCrops.firstOrNull()
                if (detailCrop != null) {
                    CropDetailPane(
                        crop = detailCrop,
                        showBackButton = false,
                        onBack = {},
                        onDiagnoseCrop = { onDiagnoseCrop(detailCrop) },
                        onAskExpertAboutCrop = { onAskExpertAboutCrop(detailCrop) },
                        modifier = Modifier
                            .weight(0.55f)
                            .fillMaxHeight()
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .weight(0.55f)
                            .fillMaxHeight(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Select a crop to view educational care & disease notes.")
                    }
                }
            }
        } else {
            // Compact Phone Flow: List or Detail with BackHandler
            val activeCrop = uiState.selectedCrop
            if (activeCrop != null) {
                BackHandler { onSelectCrop(null) }
                CropDetailPane(
                    crop = activeCrop,
                    showBackButton = true,
                    onBack = { onSelectCrop(null) },
                    onDiagnoseCrop = { onDiagnoseCrop(activeCrop) },
                    onAskExpertAboutCrop = { onAskExpertAboutCrop(activeCrop) },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            } else {
                CropListPane(
                    crops = filteredCrops,
                    uiState = uiState,
                    onUpdateSearch = onUpdateSearch,
                    onSelectCategory = onSelectCategory,
                    onSelectCrop = onSelectCrop,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }
        }
    }
}

@Composable
private fun CropListPane(
    crops: List<CropInfo>,
    uiState: LibraryUiState,
    onUpdateSearch: (String) -> Unit,
    onSelectCategory: (CropCategory?) -> Unit,
    onSelectCrop: (CropInfo) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Crop Information Library",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = "Educational guide covering 42 crops across 5 categories",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = onUpdateSearch,
            placeholder = { Text("Search crops or diseases...") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            trailingIcon = {
                if (uiState.searchQuery.isNotEmpty()) {
                    IconButton(onClick = { onUpdateSearch("") }) {
                        Icon(Icons.Filled.Close, contentDescription = "Clear search")
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier
                .fillMaxWidth()
                .testTag("library_search_input")
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                FilterChip(
                    selected = uiState.selectedCategory == null,
                    onClick = { onSelectCategory(null) },
                    label = { Text("All (${CropCatalogRepository.allCrops.size})") },
                    modifier = Modifier.testTag("lib_cat_all")
                )
            }
            items(CropCategory.entries) { cat ->
                val count = CropCatalogRepository.cropsByCategory(cat).size
                FilterChip(
                    selected = uiState.selectedCategory == cat,
                    onClick = { onSelectCategory(cat) },
                    label = { Text("${cat.shortTitle} ($count)") },
                    leadingIcon = {
                        Icon(
                            imageVector = categoryIcon(cat),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    modifier = Modifier.testTag("lib_cat_${cat.name.lowercase()}")
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        if (crops.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No matching crops found for \"${uiState.searchQuery}\".",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(crops, key = { it.id }) { crop ->
                    val isSelected = uiState.selectedCrop?.id == crop.id
                    Card(
                        shape = MaterialTheme.shapes.medium,
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) {
                                MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                            } else {
                                MaterialTheme.colorScheme.surface
                            }
                        ),
                        border = BorderStroke(
                            1.dp,
                            if (isSelected) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectCrop(crop) }
                            .testTag("library_crop_card_${crop.id}")
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                shape = CircleShape,
                                color = MaterialTheme.colorScheme.primaryContainer
                            ) {
                                Icon(
                                    imageVector = categoryIcon(crop.category),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(12.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = crop.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "${crop.category.displayName} • ${crop.scientificName}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "${crop.commonDiseases.size} common diseases documented",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Open ${crop.name} details",
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CropDetailPane(
    crop: CropInfo,
    showBackButton: Boolean,
    onBack: () -> Unit,
    onDiagnoseCrop: () -> Unit,
    onAskExpertAboutCrop: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.widthIn(max = 760.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 24.dp)
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (showBackButton) {
                    IconButton(
                        onClick = onBack,
                        modifier = Modifier.testTag("library_detail_back")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back to Crop Library"
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = crop.name,
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "${crop.category.displayName} • ${crop.scientificName}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Button(
                    onClick = onDiagnoseCrop,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("library_diagnose_crop_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.CameraAlt,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Screen Leaf")
                }

                OutlinedButton(
                    onClick = onAskExpertAboutCrop,
                    modifier = Modifier
                        .weight(1f)
                        .height(48.dp)
                        .testTag("library_ask_expert_button")
                ) {
                    Icon(
                        imageVector = Icons.Filled.Psychology,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Ask Expert")
                }
            }
        }

        item {
            LibraryInfoCard(
                title = "Suitable Growing Conditions",
                icon = Icons.Filled.WbSunny
            ) {
                Text(
                    text = crop.suitableGrowingConditions,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }

        item {
            LibraryInfoCard(
                title = "Common Visible Disease Symptoms",
                icon = Icons.Filled.Visibility
            ) {
                BulletList(crop.commonVisibleSymptoms)
            }
        }

        item {
            LibraryInfoCard(
                title = "Common Diseases",
                icon = Icons.Filled.BugReport
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    crop.commonDiseases.forEach { disease ->
                        Surface(
                            shape = MaterialTheme.shapes.small,
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.45f)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(
                                        text = disease.name,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                    Text(
                                        text = disease.causalType,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                }
                                Spacer(modifier = Modifier.height(3.dp))
                                Text(
                                    text = disease.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            LibraryInfoCard(
                title = "General Prevention Methods",
                icon = Icons.Filled.Shield
            ) {
                BulletList(crop.generalPreventionMethods)
            }
        }

        item {
            LibraryInfoCard(
                title = "Crop Care Tips",
                icon = Icons.Filled.Spa
            ) {
                BulletList(crop.cropCareTips)
            }
        }
    }
}

@Composable
private fun LibraryInfoCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    content: @Composable () -> Unit
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
            content()
        }
    }
}

@Composable
private fun BulletList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { text ->
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "•",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}
