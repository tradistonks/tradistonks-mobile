package com.tradistonks.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.tradistonks.app.models.Order
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.ProfilePreferences
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.web.services.language.LanguageController
import com.tradistonks.app.web.services.strategy.StrategyController
import java.util.*

var TOKEN = "yamete_senpai=s%3A5sAQkshwog65AuZxBqZuT214pmi82IL7.g92fGk5%2FQqkzc%2BI9FM5AWqDtJCgLWk626m9Winorsdk"
var ORDER_LIST: List<Order> = listOf<Order>(
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date()),
    Order(type =  "buy", symbol = "AAPL",price = 129.64f,quantity = 2, Date())
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
            displayMenu(authentificationController, strategyController)
        }
    }

    @Composable
    fun displayMenu(
        authController: AuthentificationController,
        stratController: StrategyController
    ) {
        setContent {
            TradistonksAndroidTheme {
                MainMenu(authController, stratController)
            }
        }
    }


}
