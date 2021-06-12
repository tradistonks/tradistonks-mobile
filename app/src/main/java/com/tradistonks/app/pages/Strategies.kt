package com.tradistonks.app.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.ui.theme.textColor

@Composable
fun Strategies(openDrawer: () -> Unit) {
    Page(openDrawer, stringResource(R.string.title_page_strategies), { pageStrategies() })
}

@Composable
fun pageStrategies(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Strategies.",
            style = MaterialTheme.typography.h1,
            color = textColor
        )
    }
}