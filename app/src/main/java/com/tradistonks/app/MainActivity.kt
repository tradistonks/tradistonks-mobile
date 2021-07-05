package com.tradistonks.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.tradistonks.app.components.Order
import com.tradistonks.app.components.Page
import com.tradistonks.app.components.Strategy
import com.tradistonks.app.components.User
import com.tradistonks.app.pages.pageConnexion
import com.tradistonks.app.ui.theme.TradistonksAndroidTheme
import okhttp3.OkHttpClient
import org.jetbrains.hub.oauth2.client.AccessToken
import org.jetbrains.hub.oauth2.client.jersey.oauth2Client
import java.net.URI
import java.net.URL
import java.util.*

var GLOBAL_USER: User? = User("Test","test@live.frrr", Date("12/04/2021"))
var STRATEGIES_LIST: List<Strategy> = listOf<Strategy>(
    Strategy("1", "Test", "Sell Actions", "Go", Date(), Date(),Date(), Date()),
    Strategy("2", "Test", "Sell", "Java", Date(), Date(),Date(), Date()),
    Strategy("3", "Test", "Buy Actions", "Kotlin", Date(), Date(),Date(), Date()),
    Strategy("4", "Test", "Buy", "Rust", Date(), Date(),Date(), Date())
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
    private var accessToken: AccessToken? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (accessToken != null) {
            accessToken = oauth2Client().codeFlow(
                tokenEndpoint = URI(System.getenv("oauth2_url_token")),
                code = "sOMec0de",
                redirectURI = URI(System.getenv("oauth2_client_redirect_url")),
                clientID = System.getenv("oauth2_client_id"),
                clientSecret = System.getenv("oauth2_client_secret")
            )
            do {
                setContent {
                    TradistonksAndroidTheme {
                        MainMenu()
                    }
                }
            } while (!accessToken!!.isExpired)
        } else {
            setContent {
                TradistonksAndroidTheme {
                    NonConnectedMainMenu()
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
}