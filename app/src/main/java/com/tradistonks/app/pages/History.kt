package com.tradistonks.app.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.ORDER_LIST
import com.tradistonks.app.R
import com.tradistonks.app.models.Order
import com.tradistonks.app.components.Page
import com.tradistonks.app.ui.theme.colorFont
import com.tradistonks.app.ui.theme.colorYellow
import com.tradistonks.app.ui.theme.textColor
import com.tradistonks.app.web.services.auth.AuthentificationController


@Composable
fun History(authController: AuthentificationController, openDrawer: () -> Unit) {
    Page(authController, openDrawer, stringResource(R.string.title_page_history), { pageHistory() })
}

@Composable
fun pageHistory(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        LiveDataComponentOrderList(ORDER_LIST)
    }
}

@Composable
fun LiveDataComponentOrderList(orderList: List<Order>) {
    LazyColumn(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp),) {
        items(items = orderList, itemContent = { order ->
            Card(
                border = BorderStroke(1.dp, colorYellow),
                shape = RoundedCornerShape(4.dp),
                backgroundColor = colorFont,
                modifier = Modifier
                    .fillParentMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)){
                    Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = order.type.uppercase(),
                            style = MaterialTheme.typography.h2,
                            color = colorYellow
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Row() {
                        Box{
                            Column(modifier = Modifier){
                                Text(
                                    text = "Symbol: ${order.symbol}",
                                    style = MaterialTheme.typography.body1,
                                    color = textColor
                                )
                                Text(
                                    text = "Date: ${order.created_date}",
                                    style = MaterialTheme.typography.body1,
                                    color = textColor
                                )
                            }
                        }
                        Column(
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = "Price : ${order.price}",
                                style = MaterialTheme.typography.body1,
                                color = textColor
                            )
                            Text(
                                text = "Quantity : ${order.quantity}",
                                style = MaterialTheme.typography.body1,
                                color = textColor
                            )
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(modifier = Modifier.align(Alignment.CenterHorizontally)) {
                        Text(
                            text = "Total : ${order.quantity * order.price}",
                            style = MaterialTheme.typography.h2,
                            color = colorYellow
                        )
                    }
                }
            }
        })
    }
}