package com.tradistonks.app


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.tradistonks.app.components.User
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import java.util.*

var GLOBAL_USER = User("Marion","test@live.frrr", Date("12/04/2021"))


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