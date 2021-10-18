package com.tradistonks.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.tradistonks.app.components.charts.line.Point
import com.tradistonks.app.models.Order
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.ProfilePreferences
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import com.tradistonks.app.web.helper.LineChartHelper
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.web.services.language.LanguageController
import com.tradistonks.app.web.services.strategy.StrategyController
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

var TOKEN = "yamete_senpai=s%3A5sAQkshwog65AuZxBqZuT214pmi82IL7.g92fGk5%2FQqkzc%2BI9FM5AWqDtJCgLWk626m9Winorsdk"
var ORDER_LIST: List<Order> = listOf<Order>(
    Order(type =  "Buy", symbol = "AAPL",quantity = 2.0, Date().time),
    Order(type =  "Buy", symbol = "BB",quantity = 2.0, Date().time),
    Order(type =  "Buy", symbol = "AAPL",quantity = 2.0, Date().time),
    Order(type =  "Sell", symbol = "BB",quantity = 2.0, Date().time),
    Order(type =  "Sell", symbol = "AAPL",quantity = 2.0, Date().time),
    Order(type =  "Sell", symbol = "AAPL",quantity = 2.0, Date().time),
    Order(type =  "Buy", symbol = "AAPL",quantity = 2.0, Date().time)
)

var PREFERENCES: ProfilePreferences? = null
var ACCESS_TOKEN = PREFERENCES?.getToken().toString()

class MainActivity : ComponentActivity() {
    val languageController: LanguageController = LanguageController()
    val strategyController: StrategyController = StrategyController(languageController)
    val authentificationController: AuthentificationController =
        AuthentificationController(strategyController)

    override fun onCreate(savedInstanceState: Bundle?) {
        PREFERENCES = ProfilePreferences(this)
        super.onCreate(savedInstanceState)
        setContent {
            displayMenu(authentificationController)
        }
    }

    @Composable
    fun displayMenu(
        authController: AuthentificationController
    ) {
        setContent {
            TradistonksAndroidTheme {
                MainMenu(authController)
            }
        }
    }


}
