package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Eco
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OfflineBolt
import androidx.compose.material.icons.filled.PresentToAll
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.TouchApp
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.CropCatalogRepository
import com.example.data.model.CropCategory
import com.example.ui.components.AgriLensLogoMark
import com.example.ui.components.CropSupportDistributionChart
import com.example.ui.components.HeroBotanicalBannerArtwork
import com.example.ui.components.SystemWorkflowDiagram
import com.example.ui.components.categoryIcon
import com.example.ui.theme.ForestGreen
import com.example.ui.theme.HarvestAmber
import com.example.ui.theme.HarvestAmberContainer
import com.example.ui.theme.HarvestAmberText
import com.example.ui.theme.MintGreen

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(
    onStartDiagnosis: () -> Unit,
    onAskExpert: () -> Unit,
    onOpenLibraryCategory: (CropCategory) -> Unit,
    onOpenAbout: () -> Unit,
    onOpenPresentation: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 880.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // 1. Hero Section
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("home_hero_card")
                ) {
                    HeroBotanicalBannerArtwork(
                        modifier = Modifier
                            .matchParentSize()
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(22.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            AgriLensLogoMark(size = 44.dp)
                            Column {
                                Text(
                                    text = "AgriLens AI",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(
                                    text = "Let the Leaf Tell the Story.",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = MintGreen
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Surface(
                            shape = CircleShape,
                            color = Color.White.copy(alpha = 0.16f)
                        ) {
                            Text(
                                text = "Team BioVision  •  SDG 2: Zero Hunger",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = "Early Crop Disease Screening & Agricultural Guidance",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Upload or capture a leaf photo to screen visible symptoms with multimodal AI, explore 42 crop care guides, and chat with the AgriLens AI Crop Expert in English or Hindi.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.90f)
                        )

                        Spacer(modifier = Modifier.height(18.dp))

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Button(
                                onClick = onStartDiagnosis,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White,
                                    contentColor = ForestGreen
                                ),
                                modifier = Modifier
                                    .height(48.dp)
                                    .testTag("home_start_diagnosis_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CameraAlt,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Start Diagnosis", fontWeight = FontWeight.Bold)
                            }

                            OutlinedButton(
                                onClick = onAskExpert,
                                border = BorderStroke(1.5.dp, Color.White),
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                                modifier = Modifier
                                    .height(48.dp)
                                    .testTag("home_ask_expert_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Psychology,
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Ask AI Crop Expert", fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }
                }
            }

            // 2. How Early Detection Helps Farmers
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "Why Early Leaf Detection Helps Farmers",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Many crop infections begin as faint spots, yellowing, or mosaic patterns long before a field shows widespread damage. When farmers notice symptoms early, they can isolate affected leaves, adjust irrigation or spacing, and seek timely guidance from local Krishi Vigyan Kendra (KVK) specialists—reducing preventable yield loss and avoiding unnecessary chemical sprays.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // 3. Key Platform Features (including planned offline distinction)
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Platform Capabilities",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        FeatureCard(
                            icon = Icons.Filled.Visibility,
                            title = "AI-Assisted Screening",
                            badgeText = "Live • Gemini",
                            badgeIsPlanned = false,
                            description = "Examines uploaded leaf photos for visible symptoms, possible causes, and honest clarity checks.",
                            modifier = Modifier.weight(1f, fill = false).widthIn(min = 160.dp, max = 420.dp)
                        )
                        FeatureCard(
                            icon = Icons.Filled.Psychology,
                            title = "AI Crop Guidance",
                            badgeText = "Live • EN / HI",
                            badgeIsPlanned = false,
                            description = "Interactive chat expert answering disease, watering, soil, and seasonal care questions.",
                            modifier = Modifier.weight(1f, fill = false).widthIn(min = 160.dp, max = 420.dp)
                        )
                        FeatureCard(
                            icon = Icons.Filled.TouchApp,
                            title = "Simple Farmer UI",
                            badgeText = "Live • No Login",
                            badgeIsPlanned = false,
                            description = "42 searchable crops, clear prevention checklists, and local on-device history.",
                            modifier = Modifier.weight(1f, fill = false).widthIn(min = 160.dp, max = 420.dp)
                        )
                        FeatureCard(
                            icon = Icons.Filled.OfflineBolt,
                            title = "Edge Offline CNN",
                            badgeText = "Future Vision",
                            badgeIsPlanned = true,
                            description = "Planned TensorFlow Lite CNN model for rural no-internet field inference.",
                            modifier = Modifier.weight(1f, fill = false).widthIn(min = 160.dp, max = 420.dp)
                        )
                    }
                }
            }

            // 4. Supported Crop Categories & Chart
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Supported Crop Categories",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        CropCategory.entries.forEach { category ->
                            val count = CropCatalogRepository.cropsByCategory(category).size
                            Surface(
                                shape = MaterialTheme.shapes.medium,
                                color = MaterialTheme.colorScheme.surface,
                                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                                modifier = Modifier
                                    .clickable { onOpenLibraryCategory(category) }
                                    .testTag("home_category_${category.name.lowercase()}")
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = categoryIcon(category),
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.primary,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = category.displayName,
                                            style = MaterialTheme.typography.labelLarge,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                        Text(
                                            text = "$count crops • Tap to explore",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                            }
                        }
                    }
                    CropSupportDistributionChart()
                }
            }

            // 5. How It Works Section
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(
                            text = "How AgriLens AI Works",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Transparent 6-stage architecture separating implemented AI screening from planned CNN training.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        SystemWorkflowDiagram()
                    }
                }
            }

            // 6. SDG 2 Zero Hunger & Impact
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.55f)
                    ),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.Public,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(22.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Project Impact & SDG 2: Zero Hunger",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Aligned with UN Sustainable Development Goal 2 (Zero Hunger), SDG 8 (Decent Work), and SDG 12 (Responsible Production), AgriLens AI aims to empower smallholder farmers with timely crop awareness, lower avoidable harvest losses, and promote preventive agricultural stewardship.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }

            // 7. Limitations & Future Vision
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = HarvestAmberContainer.copy(alpha = 0.45f)),
                    border = BorderStroke(1.dp, HarvestAmber.copy(alpha = 0.35f))
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Filled.WarningAmber,
                                contentDescription = null,
                                tint = HarvestAmberText,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Honest Limitations & Future Vision",
                                style = MaterialTheme.typography.titleMedium,
                                color = HarvestAmberText
                            )
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Current leaf screening is preliminary and powered by Gemini multimodal vision—not a trained CNN classifier. Image lighting, blur, and internet availability affect results, and expert verification (e.g., KVK) is recommended. Future milestones include training a labeled CNN dataset and exporting to TensorFlow Lite for full offline use.",
                            style = MaterialTheme.typography.bodySmall,
                            color = HarvestAmberText
                        )
                    }
                }
            }

            // 8. Footer with Team BioVision + About / Presentation Mode CTAs
            item {
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AgriLensLogoMark(size = 28.dp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "AgriLens AI • Team BioVision",
                                style = MaterialTheme.typography.titleMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "College Project • SDG 2 Zero Hunger Track • Let the Leaf Tell the Story.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            OutlinedButton(
                                onClick = onOpenAbout,
                                modifier = Modifier.height(48.dp).testTag("home_footer_about_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("About Project")
                            }
                            Button(
                                onClick = onOpenPresentation,
                                modifier = Modifier.height(48.dp).testTag("home_footer_presentation_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.PresentToAll,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Presentation")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FeatureCard(
    icon: ImageVector,
    title: String,
    badgeText: String,
    badgeIsPlanned: Boolean,
    description: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.primaryContainer
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(20.dp)
                    )
                }
                Surface(
                    shape = CircleShape,
                    color = if (badgeIsPlanned) HarvestAmberContainer else MaterialTheme.colorScheme.primaryContainer
                ) {
                    Text(
                        text = badgeText,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (badgeIsPlanned) HarvestAmberText else MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
