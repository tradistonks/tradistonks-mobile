package com.tradistonks.app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tradistonks.app.components.Order
import com.tradistonks.app.components.Strategy
import com.tradistonks.app.components.User
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import java.util.*

var GLOBAL_USER:User = User("Test","test@live.frrr", Date("12/04/2021"))
var STRATEGIES_LIST: List<Strategy> = listOf<Strategy>(
    Strategy("1", "Test", "Sell Actions", "Go", Date(), Date()),
    Strategy("2", "Test", "Sell", "Java", Date(), Date()),
    Strategy("3", "Test", "Buy Actions", "Kotlin", Date(), Date()),
    Strategy("4", "Test", "Buy", "Rust", Date(), Date())
)

var ORDER_LIST: List<Order> = listOf<Order>(
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date())
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TradistonksAndroidTheme {
                MainMenu()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TradistonksAndroidTheme {
        MainMenu()
    }
}