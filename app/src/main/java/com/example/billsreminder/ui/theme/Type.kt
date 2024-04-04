package com.example.billsreminder.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.billsreminder.R

val Roboto = FontFamily(
    Font(R.font.roboto_regular),
    Font(R.font.roboto_bold, FontWeight.Bold)
)

private val typography = androidx.compose.material3.Typography()

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = typography.bodyLarge.copy(fontFamily = Roboto),
    bodyMedium = typography.bodyMedium.copy(fontFamily = Roboto),
    bodySmall = typography.bodySmall.copy(fontFamily = Roboto),

    displayLarge = typography.displayLarge.copy(fontFamily = Roboto),
    displayMedium = typography.displayMedium.copy(fontFamily = Roboto),
    displaySmall = typography.displaySmall.copy(fontFamily = Roboto)
)