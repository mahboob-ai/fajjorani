package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

private val AgriLensLightColorScheme = lightColorScheme(
    primary = ForestGreen,
    onPrimary = CrispWhite,
    primaryContainer = LightGreenSurface,
    onPrimaryContainer = ForestGreenDark,
    secondary = LeafGreen,
    onSecondary = CrispWhite,
    secondaryContainer = ColorTokens.SecondaryMintContainer,
    onSecondaryContainer = ForestGreenDark,
    tertiary = HarvestAmber,
    onTertiary = CrispWhite,
    tertiaryContainer = HarvestAmberContainer,
    onTertiaryContainer = HarvestAmberText,
    background = SoftBotanicalBg,
    onBackground = DeepInk,
    surface = CrispWhite,
    onSurface = DeepInk,
    surfaceVariant = LightGreenSurface,
    onSurfaceVariant = MutedMossText,
    outline = SoftBorderGreen,
    outlineVariant = ColorTokens.SubtleDivider,
    error = AlertRed,
    onError = CrispWhite,
    errorContainer = AlertRedContainer,
    onErrorContainer = AlertRedText
)

private val AgriLensDarkColorScheme = darkColorScheme(
    primary = DarkMintPrimary,
    onPrimary = ForestGreenDark,
    primaryContainer = DarkForestContainer,
    onPrimaryContainer = LightGreenSurface,
    secondary = MintGreen,
    onSecondary = ForestGreenDark,
    secondaryContainer = DarkSurfaceVariant,
    onSecondaryContainer = DarkInk,
    tertiary = HarvestAmber,
    onTertiary = CrispWhite,
    tertiaryContainer = HarvestAmberText,
    onTertiaryContainer = HarvestAmberContainer,
    background = DarkBg,
    onBackground = DarkInk,
    surface = DarkSurface,
    onSurface = DarkInk,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkMutedInk,
    outline = ColorTokens.DarkBorder,
    outlineVariant = DarkSurfaceVariant,
    error = AlertRedContainer,
    onError = AlertRedText,
    errorContainer = AlertRed,
    onErrorContainer = AlertRedContainer
)

private object ColorTokens {
    val SecondaryMintContainer = androidx.compose.ui.graphics.Color(0xFFD8EFE0)
    val SubtleDivider = androidx.compose.ui.graphics.Color(0xFFE3ECE4)
    val DarkBorder = androidx.compose.ui.graphics.Color(0xFF2D4632)
}

val AgriLensShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun AgriLensTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) AgriLensDarkColorScheme else AgriLensLightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AgriLensTypography,
        shapes = AgriLensShapes,
        content = content
    )
}
