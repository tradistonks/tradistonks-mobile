package com.tradistonks.app.components

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Modifier

@Composable
 fun TitleFormat(title: String = "", modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.h1,
        textColor = MaterialTheme.colors.textColor
    )
}

@Composable
fun BodyFormat(body: String = "", modifier: Modifier = Modifier) {
    Text(
        text = body,
        style = MaterialTheme.typography.body1,
        textColor = MaterialTheme.colors.textColor
    )
}

@Composable
fun SubtitleFormat(subtitle: String = "", modifier: Modifier = Modifier) {
    Text(
        text = subtitle,
        style = MaterialTheme.typography.subtitle1,
        textColor = MaterialTheme.colors.textColor
    )
}