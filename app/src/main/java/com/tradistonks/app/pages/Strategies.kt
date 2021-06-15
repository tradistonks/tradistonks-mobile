package com.tradistonks.app.pages

import android.graphics.Paint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.tradistonks.app.R
import com.tradistonks.app.components.Page
import com.tradistonks.app.ui.theme.textColor
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Send
import androidx.compose.ui.unit.dp
import com.tradistonks.app.STRATEGIES_LIST
import com.tradistonks.app.components.Strategy
import com.tradistonks.app.ui.theme.colorFont

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
        Text(text = stringResource(id = R.string.title_page_strategies),
            style = MaterialTheme.typography.h1,
            color = textColor
        )
        LiveDataComponentList(STRATEGIES_LIST)
    }
}

@Composable
fun LiveDataComponentList(strategyList: List<Strategy>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = strategyList, itemContent = { strategy ->
            Card(
                shape = RoundedCornerShape(4.dp),
                backgroundColor = colorFont,
                modifier = Modifier
                    .fillParentMaxWidth()
                    .padding(8.dp)
            ) {
                Row(modifier = Modifier) {
                    Box{
                        Column(modifier = Modifier){
                            Text(
                                text = strategy.name,
                                style = MaterialTheme.typography.h2,
                                color = textColor
                            )
                            Text(
                                text = "language: ${strategy.language}",
                                style = MaterialTheme.typography.body1,
                                color = textColor
                            )
                            Text(
                                text = "last update: ${strategy.updated_date}",
                                style = MaterialTheme.typography.body1,
                                color = textColor
                            )
                        }

                    }
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Icon(
                            Icons.Outlined.Send,
                            contentDescription = stringResource(id = R.string.run),
                        )
                    }
                }
            }
        })
    }
}
