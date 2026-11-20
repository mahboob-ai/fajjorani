package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Architecture
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PresentToAll
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.WarningAmber
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.ui.components.AgriLensLogoMark
import com.example.ui.components.SystemWorkflowDiagram
import com.example.ui.theme.ForestGreen
import com.example.ui.theme.HarvestAmber
import com.example.ui.theme.HarvestAmberContainer
import com.example.ui.theme.HarvestAmberText

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AboutScreen(
    onOpenPresentationMode: () -> Unit,
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
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Project Header + Launch Presentation CTA
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
                            AgriLensLogoMark(size = 42.dp)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "AgriLens AI",
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                Text(
                                    text = "Team BioVision • SDG 2: Zero Hunger",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Mission: Early crop disease awareness through accessible AI assistance.",
                            style = MaterialTheme.typography.titleMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "AgriLens AI bridges smartphone photography, multimodal Gemini screening, and practical agricultural knowledge so farmers and students can recognize possible leaf stress early without needing specialized lab gear.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                        Spacer(modifier = Modifier.height(14.dp))
                        Button(
                            onClick = onOpenPresentationMode,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .testTag("launch_presentation_mode_button")
                        ) {
                            Icon(
                                imageVector = Icons.Filled.PresentToAll,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Launch College Presentation Mode (8 Slides)")
                        }
                    }
                }
            }

            // 2. Problem & Proposed Solution
            item {
                AboutSectionCard(
                    title = "The Problem We Address",
                    icon = Icons.Filled.WarningAmber
                ) {
                    AboutBulletList(
                        listOf(
                            "Early leaf symptoms are subtle and difficult to tell apart from nutrient or weather stress.",
                            "Trained agricultural specialists and Krishi Vigyan Kendra (KVK) labs may not be readily accessible in rural areas.",
                            "Delayed identification allows fungal, bacterial, or viral issues to spread and multiply crop damage.",
                            "Farmers need accessible, plain-language first-level awareness before problems escalate."
                        )
                    )
                }
            }

            item {
                AboutSectionCard(
                    title = "Proposed Solution",
                    icon = Icons.Filled.Lightbulb
                ) {
                    AboutBulletList(
                        listOf(
                            "Smartphone leaf photography via gallery upload or mobile camera capture.",
                            "Client-side image validation and pre-processing (format, size, and resolution scaling).",
                            "AI-assisted symptom screening and contextual disease education.",
                            "Practical prevention guidance and a bilingual (English & Hindi) AI Crop Expert."
                        )
                    )
                }
            }

            // 3. System Workflow
            item {
                AboutSectionCard(
                    title = "System Workflow",
                    icon = Icons.Filled.Architecture
                ) {
                    Text(
                        text = "Capture Image → Pre-processing → Feature Extraction → AI Analysis → Disease Classification → Guidance",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    SystemWorkflowDiagram()
                }
            }

            // 4. CNN Architecture Explanation (Explicitly marked as Planned Work)
            item {
                AboutSectionCard(
                    title = "Convolutional Neural Network (CNN) — Planned Work",
                    icon = Icons.Filled.Layers,
                    badgeText = "Planned Integration",
                    badgeIsPlanned = true
                ) {
                    Text(
                        text = "How a CNN fits into AgriLens AI's roadmap:",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    AboutBulletList(
                        listOf(
                            "Convolutional Neural Networks learn hierarchical visual features—leaf edges, spot textures, color halos, and lesion shapes.",
                            "Training requires large, diverse, accurately labeled crop leaf datasets collected under real field lighting.",
                            "Once trained and validated, the model makes rapid local pattern predictions on unseen leaf photos.",
                            "Important Transparency: No trained CNN model is integrated in this version yet. Current live screening uses Gemini multimodal image understanding via secure backend attestation."
                        )
                    )
                }
            }

            // 5. Technology Stack (Implemented vs Planned)
            item {
                AboutSectionCard(
                    title = "Technology Stack: Implemented vs. Planned",
                    icon = Icons.Filled.Build
                ) {
                    Text(
                        text = "Implemented in This Application",
                        style = MaterialTheme.typography.titleSmall,
                        color = ForestGreen
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TechChip("Kotlin + Jetpack Compose", true)
                        TechChip("Gemini API (Firebase AI Logic)", true)
                        TechChip("Room Local Database", true)
                        TechChip("DataStore Preferences", true)
                        TechChip("Android Photo Picker & Camera", true)
                    }

                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = "Planned Edge & Web Research Stack",
                        style = MaterialTheme.typography.titleSmall,
                        color = HarvestAmberText
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        TechChip("Python + OpenCV", false)
                        TechChip("TensorFlow / PyTorch CNN", false)
                        TechChip("TensorFlow Lite (Offline Edge)", false)
                        TechChip("Open-Source Crop Datasets", false)
                        TechChip("React + Node.js Web Companion", false)
                    }
                }
            }

            // 6. Limitations & Future Scope
            item {
                AboutSectionCard(
                    title = "Project Limitations",
                    icon = Icons.Filled.WarningAmber
                ) {
                    AboutBulletList(
                        listOf(
                            "Photograph lighting, blur, background clutter, and camera angle affect AI assessment.",
                            "Future CNN model accuracy depends directly on training dataset quality and regional diversity.",
                            "AI screening results may be uncertain or inconclusive and must not replace laboratory or agronomist confirmation.",
                            "Crop and disease coverage requires ongoing validation across local varieties.",
                            "True offline edge diagnosis is not yet implemented; live AI features require internet access."
                        )
                    )
                }
            }

            item {
                AboutSectionCard(
                    title = "Future Scope",
                    icon = Icons.Filled.RocketLaunch
                ) {
                    AboutBulletList(
                        listOf(
                            "Train a dedicated CNN model on labeled regional crop disease datasets.",
                            "Quantize and convert the trained model to TensorFlow Lite for offline mobile inference.",
                            "Deliver fully offline field diagnosis in low-connectivity rural areas.",
                            "Add regional language voice assistance for hands-free farmer interaction.",
                            "Expand regional crop datasets, seasonal crop health monitoring, and local KVK expert consultation links."
                        )
                    )
                }
            }

            // 7. SDG Impact
            item {
                AboutSectionCard(
                    title = "Sustainable Development Goals (SDG) Impact",
                    icon = Icons.Filled.Public
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        SdgBlock(
                            code = "SDG 2",
                            title = "Zero Hunger",
                            detail = "Early disease awareness helps farmers protect standing crops and reduce avoidable food loss."
                        )
                        SdgBlock(
                            code = "SDG 8",
                            title = "Decent Work & Economic Growth",
                            detail = "Safeguards smallholder farm income and productivity by catching stress early."
                        )
                        SdgBlock(
                            code = "SDG 12",
                            title = "Responsible Consumption & Production",
                            detail = "Encourages targeted preventive care and reduces blind overuse of chemical inputs."
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AboutSectionCard(
    title: String,
    icon: ImageVector,
    badgeText: String? = null,
    badgeIsPlanned: Boolean = false,
    content: @Composable () -> Unit
) {
    Card(
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
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
                if (badgeText != null) {
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
            }
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun AboutBulletList(items: List<String>) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items.forEach { line ->
            Row(verticalAlignment = Alignment.Top) {
                Text(
                    text = "•",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = line,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

@Composable
private fun TechChip(label: String, isImplemented: Boolean) {
    Surface(
        shape = CircleShape,
        color = if (isImplemented) {
            MaterialTheme.colorScheme.primaryContainer
        } else {
            HarvestAmberContainer.copy(alpha = 0.7f)
        },
        border = BorderStroke(
            1.dp,
            if (isImplemented) MaterialTheme.colorScheme.primary.copy(alpha = 0.3f)
            else HarvestAmber.copy(alpha = 0.4f)
        )
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = if (isImplemented) MaterialTheme.colorScheme.onPrimaryContainer else HarvestAmberText,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

@Composable
private fun SdgBlock(code: String, title: String, detail: String) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                shape = MaterialTheme.shapes.small,
                color = ForestGreen
            ) {
                Text(
                    text = code,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = detail,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
