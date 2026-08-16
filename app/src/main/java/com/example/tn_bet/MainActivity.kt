package com.example.tn_bet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.tn_bet.ui.TitansScreen
import com.example.tn_bet.ui.theme.TN_BetTheme

class MainActivity : ComponentActivity() {

    enum class AppDestinations(
        val label: String,
        val icon: Int,
    ) {
        HOME("Home", R.drawable.ic_home),
        TITANS("Titans", R.drawable.ic_favorite), // Using heart for now, or I can create a new icon
        FAVORITES("Favorites", R.drawable.ic_favorite),
        PROFILE("Profile", R.drawable.ic_account_box),
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setOnExitAnimationListener { splashScreenView ->
            splashScreenView.view.animate()
                .alpha(0f)
                .scaleX(0.5f)
                .scaleY(0.5f)
                .setDuration(500L)
                .withEndAction { splashScreenView.remove() }
                .start()
        }

        enableEdgeToEdge()
        setContent {
            TN_BetTheme {
                TN_BetApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun TN_BetApp() {
    var currentDestination by rememberSaveable { mutableStateOf(MainActivity.AppDestinations.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            MainActivity.AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            painterResource(it.icon),
                            contentDescription = it.label
                        )
                    },
                    label = { Text(it.label) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }
    ) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentDestination) {
                    MainActivity.AppDestinations.TITANS -> TitansScreen()
                    else -> Greeting(
                        name = "Tennessee",
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello from $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TN_BetTheme {
        Greeting("TN_Bet")
    }
}
