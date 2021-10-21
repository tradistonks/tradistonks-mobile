package com.tradistonks.app

import android.content.Context
import android.os.Bundle
import android.os.storage.StorageManager
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
        val strategyController: StrategyController = StrategyController(languageController, this.applicationContext)
        val authentificationController: AuthentificationController =
            AuthentificationController(strategyController, db.userDao(), this.applicationContext)

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
