package com.example.ListaDeTarefas.ui.theme

import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val XpColor = Color(0xFF7E57C2)
val GoldColor = Color(0xFFFFA000)
val SuccessGreen = Color(0xFF388E3C)
val DangerRed = Color(0xFFD32F2F)
val BackgroundLight = Color(0xFFF7F9FC)
val CardSurfaceLight = Color.White
val TextColorLight = Color(0xFF1D1D1F)
val DividerColorLight = Color.Black.copy(alpha = 0.12f)

@Composable
fun getTextFieldColorsLight(): TextFieldColors = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = XpColor,
    unfocusedBorderColor = DividerColorLight,
    focusedLabelColor = XpColor,
    unfocusedLabelColor = Color.Gray,
    focusedLeadingIconColor = XpColor,
    unfocusedLeadingIconColor = Color.Gray,
    cursorColor = XpColor,
    focusedContainerColor = CardSurfaceLight,
    unfocusedContainerColor = CardSurfaceLight,
    focusedTextColor = TextColorLight,
    unfocusedTextColor = TextColorLight,
    disabledTextColor = TextColorLight.copy(alpha = 0.5f),
    disabledBorderColor = DividerColorLight.copy(alpha = 0.5f),
    disabledLabelColor = Color.Gray.copy(alpha = 0.5f),
    disabledLeadingIconColor = Color.Gray.copy(alpha = 0.5f)
)