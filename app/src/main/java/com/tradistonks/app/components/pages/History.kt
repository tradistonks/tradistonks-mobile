package com.tradistonks.app.components.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Center
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.tradistonks.app.R
import com.tradistonks.app.models.Order
import com.tradistonks.app.components.Page
import com.tradistonks.app.models.DataTablePagination
import com.tradistonks.app.ui.theme.*
import com.tradistonks.app.web.services.auth.AuthentificationController
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun History(authController: AuthentificationController, openDrawer: () -> Unit) {
    Page(authController, openDrawer, stringResource(R.string.title_page_history), { pageHistory(authController) })
}

@Composable
fun pageHistory(authController: AuthentificationController){

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        val stratController = authController.stratController
        val orders = stratController.getAllOrdersFromStrategies()
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        orders.forEach {
            order ->
            order.quantity = df.format(order.quantity).toDouble()
        }
        if(orders.size > 0){
            LiveDataComponentOrderList(orders)
        }else{
            printErrorHistoryPage()
        }

    }
}

@Composable
fun dataTablePagination(
    initialPage: Int = 0,
    initialRowsPerPage: Int,
    availableRowsPerPage: List<Int>
): DataTablePagination {
    val page = remember { mutableStateOf(initialPage) }
    val rowsPerPage = remember {mutableStateOf(initialRowsPerPage) }
    return DataTablePagination(
        page = page.value,
        rowsPerPage = rowsPerPage.value,
        availableRowsPerPage = availableRowsPerPage,
        onPageChange = { page.value = it },
        onRowsPerPageChange = { rowsPerPage.value = it }
    )
}

fun <T> SnapshotStateList<T>.swapList(newList: List<T>){
    clear()
    addAll(newList)
}

@Composable
fun LiveDataComponentOrderList(orderList: List<Order>) {
    val pagination = dataTablePagination(
        initialRowsPerPage = 4,
        availableRowsPerPage = orderList.chunked(5).map { it.size }
    )
    val rows = orderList
    var data = rows.drop(pagination.rowsPerPage * pagination.page).take(pagination.rowsPerPage)
    val myList = remember { mutableStateListOf<Order>()  }
    myList.swapList(data)
    val onUpdateClick = {
        myList.swapList(rows.drop(pagination.rowsPerPage * pagination.page).take(pagination.rowsPerPage))
    }
    Spacer(modifier = Modifier.height(16.dp))

    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            myList.forEach { order ->
                cardDataOrder(order)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        item {
            Column(modifier = Modifier.fillMaxWidth()) {
                val pages = (orderList.size - 1) / pagination.rowsPerPage + 1
                val startRow = pagination.rowsPerPage * pagination.page
                val endRow = (startRow + pagination.rowsPerPage).coerceAtMost(orderList.size)

                Text(
                    text = "Rows per page: ${pagination.rowsPerPage}",
                    style = MaterialTheme.typography.subtitle1,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${startRow + 1}-$endRow of ${orderList.size}",
                    style = MaterialTheme.typography.subtitle1,
                    color = textColor
                )

                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End){
                    Button(
                        onClick = {
                            val newPage = pagination.page - 1
                            if (newPage >= 0)
                                pagination.onPageChange.invoke(newPage)
                            onUpdateClick()
                        },
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
                    ) {
                        androidx.compose.material.Text(
                            text = "Prev"
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))
                    Button(
                        onClick = {
                            val newPage = pagination.page + 1
                            if (newPage < pages){
                                pagination.onPageChange.invoke(newPage)
                                onUpdateClick()
                            }

                        },
                        modifier = Modifier
                            .padding(vertical = 16.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = colorPink)
                    ) {
                        androidx.compose.material.Text(
                            text = "Next"
                        )
                    }
                }
            }
        }
    }

}

@Composable
fun cardDataOrder(order: Order){
    val sdf = SimpleDateFormat("dd/MM/yyyy")
    Card(
        border = BorderStroke(1.dp, colorYellow),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = colorFont,
        modifier = Modifier.fillMaxSize()
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
                    Column(modifier = Modifier.width(160.dp)){
                        Text(
                            text = "Currency: ${order.symbol} ",
                            style = MaterialTheme.typography.h2,
                            color = textColor
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = "Quantity : ${order.quantity}",
                        style = MaterialTheme.typography.body1,
                        color = textColor
                    )
                    Text(
                        text = "Date: ${sdf.format(Date(order.timestamp * 1000))}",
                        style = MaterialTheme.typography.body1,
                        color = textColor
                    )
                }
            }
        }
    }
}

@Composable
fun printErrorHistoryPage(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(
            text = "Launch a strategy to have data",
            style = MaterialTheme.typography.h1,
            color = colorYellow
        )
    }
}

