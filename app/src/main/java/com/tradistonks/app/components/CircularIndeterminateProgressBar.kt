package com.tradistonks.app.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CircularIndeterminateProgressBar(isDisplayed: Boolean, modifier: Modifier, color: Color) {
    if (isDisplayed) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
                modifier = modifier,
                horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                    color = color
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
