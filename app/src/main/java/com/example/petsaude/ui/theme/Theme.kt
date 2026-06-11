package com.example.petsaude.ui.theme

import androidx.compose.ui.graphics.Color as ComposeColor
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

private val PetSaudeColorScheme = lightColorScheme(
    primary              = Teal500,
    onPrimary            = White,
    primaryContainer     = Teal100,
    onPrimaryContainer   = Navy900,
    secondary            = Purple500,
    onSecondary          = White,
    secondaryContainer   = ComposeColor(0xFFEDE9FE),
    background           = GrayBg,
    surface              = White,
    onBackground         = Navy900,
    onSurface            = Navy900,
    onSurfaceVariant     = GrayText,
    outline              = GrayBorder,
    error                = RedExpired
)

@Composable
fun PetSaudeTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = PetSaudeColorScheme,
        typography  = Typography,
        content     = content
    )
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        fontSize = 11.sp,
        fontWeight = FontWeight.SemiBold,
        color = Teal500,
        letterSpacing = 1.sp
    )
}

@Composable
fun petFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Teal500,
    focusedLabelColor = Teal500,
    unfocusedBorderColor = GrayBorder
)

