package com.tradistonks.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.android.material.navigation.NavigationView
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView : NavigationView = findViewById(R.id.nav_view)
        val navControler = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navControler)
    }
}

@Composable
fun Greeting(name: String) {
    Row(
        Modifier.padding(5.dp).fillMaxWidth().fillMaxHeight()
    ) {
        Column {
            Text(text = "Hello $name!")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TradistonksAndroidTheme {
        Greeting("Android")
    }
}