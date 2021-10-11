package com.tradistonks.app.pages

import androidx.compose.foundation.BorderStroke
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
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.responses.TokenResponse
import com.tradistonks.app.ui.theme.colorFont
import com.tradistonks.app.ui.theme.colorGreen
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.web.services.strategy.StrategyController

@Composable
fun Strategies(openDrawer: () -> Unit, authController: AuthentificationController) {
    Page(authController, openDrawer, stringResource(R.string.title_page_strategies), { pageStrategies(authController) })
}

@Composable
fun pageStrategies(authController: AuthentificationController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController
        stratController.strategies?.let { LiveDataComponentList(it, authController) }
    }
}

@Composable
fun LiveDataComponentList(strategyList: List<Strategy>, authController: AuthentificationController) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(items = strategyList, itemContent = { strategy ->
            Card(
                border = BorderStroke(1.dp, colorGreen),
                shape = RoundedCornerShape(4.dp),
                backgroundColor = colorFont,
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = strategy.name.uppercase(),
                            style = MaterialTheme.typography.h2,
                            color = colorGreen
                        )
                        Text(
                            text = "",
                            style = MaterialTheme.typography.body1,
                            color = colorGreen
                        )
                    }
                    Row() {
                        Box{
                            Column(modifier = Modifier){
                                Text(
                                    text = "Language: ${strategy.language}",
                                    style = MaterialTheme.typography.body1,
                                    color = textColor
                                )
                                Text(
                                    text = "Last update: ${strategy.updated_date}",
                                    style = MaterialTheme.typography.body1,
                                    color = textColor
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.End
                        ) {
                            Button(
                                onClick = {
                                    val stratController = authController.stratController
                                    stratController.runStrategyById(TokenResponse("", ""), strategy._id)
                                },
                                colors = ButtonDefaults.buttonColors(backgroundColor = colorGreen),
                            ) {
                                Icon(
                                    Icons.Outlined.Send,
                                    contentDescription = stringResource(id = R.string.run),
                                )
                            }
                        }
                    }
                }
            }
        })
    }
}
