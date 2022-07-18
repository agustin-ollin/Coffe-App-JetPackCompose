package com.example.coffe_app_jetpack

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffe_app_jetpack.ui.components.CountryISO
import com.example.coffe_app_jetpack.ui.screens.CheckoutScreen
import com.example.coffe_app_jetpack.ui.screens.DetailScreen
import com.example.coffe_app_jetpack.ui.screens.FeedScreen
import com.example.coffe_app_jetpack.ui.screens.SplashScreen
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme
import com.example.coffe_app_jetpack.utilities.MockDataProvider

/**
 * -> Ver el video 20 <-
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //onWindowFocusChanged(true)
        setContent {
            NavigationHost()
        }
    }


    /*override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }*/
}

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    CoffeAppJetPackTheme {
        Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = "splash"){
                composable(route = "splash"){
                    SplashScreen(navController)
                }

                composable(route = "feed"){
                    FeedScreen(navController)
                }

                composable(route = "detail/{productId}"){ backStackEntry ->
                    val productIdString = backStackEntry.arguments?.getString("productId") ?: "0";
                    val productId = productIdString.toInt()
                    val product = MockDataProvider.productById(productId)
                    DetailScreen(navController, product!!)
                }

                composable(route = "checkout/{productId}"){ backStackEntry ->
                    val productIdString = backStackEntry.arguments?.getString("productId") ?: "0";
                    val productId = productIdString.toInt()
                    val product = MockDataProvider.productById(productId)
                    CheckoutScreen(navController, product!!)
                }
            }
        }
    }
}
