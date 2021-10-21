package com.tradistonks.app

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.tradistonks.app.components.charts.line.Point
import com.tradistonks.app.BuildConfig.TOKEN
import com.tradistonks.app.models.Order
import com.tradistonks.app.models.Strategy
import com.tradistonks.app.models.ProfilePreferences
import com.tradistonks.app.models.responses.strategy.RunResultDto
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import com.tradistonks.app.web.helper.LineChartHelper
import com.tradistonks.app.web.repository.room.AppDatabase
import com.tradistonks.app.web.services.auth.AuthentificationController
import com.tradistonks.app.web.services.language.LanguageController
import com.tradistonks.app.web.services.strategy.StrategyController
import java.io.File
import java.io.FileInputStream
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

var PREFERENCES: ProfilePreferences? = null
var ACCESS_TOKEN = PREFERENCES?.getToken().toString()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val db = AppDatabase.getInstance(this)
        val languageController: LanguageController = LanguageController()
        val strategyController: StrategyController = StrategyController(languageController, db.strategyDao())
        val authentificationController: AuthentificationController =
            AuthentificationController(strategyController, db.userDao())


        val filename = "strategies"
        val fileContents = "Hello world!"
        this.openFileOutput(filename, Context.MODE_PRIVATE).use {
            it.write(fileContents.toByteArray())
        }

        PREFERENCES = ProfilePreferences(this)
        super.onCreate(savedInstanceState)
        /*strategyController.getAllStrategiesFromLocalBdd()
        print(strategyController.strategies?.get(1))
        val results: ArrayList<RunResultDto>? =
            strategyController.strategies?.stream()?.map(Strategy::results)?.collect(Collectors.toList()) as ArrayList<RunResultDto>
        val orders = results?.stream()?.map(RunResultDto::orders)?.collect(Collectors.toList())
        print(orders)*/
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
