package com.example.coffe_app_jetpack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.coffe_app_jetpack.ui.components.CountryISO
import com.example.coffe_app_jetpack.ui.screens.CheckoutScreen
import com.example.coffe_app_jetpack.ui.screens.DetailScreen
import com.example.coffe_app_jetpack.ui.screens.FeedScreen
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme
import com.example.coffe_app_jetpack.utilities.MockDataProvider

/**
 * -> Ver el video 20 <-
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationHost()
        }
    }
}

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    CoffeAppJetPackTheme {
        Surface(color = MaterialTheme.colors.background) {
            NavHost(navController = navController, startDestination = "feed"){
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
