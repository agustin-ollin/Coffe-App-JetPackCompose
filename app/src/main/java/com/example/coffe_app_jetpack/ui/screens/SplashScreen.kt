package com.example.coffe_app_jetpack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.*
import com.example.coffe_app_jetpack.R
import com.example.coffe_app_jetpack.ui.theme.CoffeText
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController){
    LaunchedEffect(key1 = true){
        delay(7000)
        navController.popBackStack()
        navController.navigate("feed")
    }
    Splash()
}

@Composable
fun Splash(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.caffeine_rush))
    //val progress by animateLottieCompositionAsState(composition, iterations = LottieConstants.IterateForever)
    val progress by animateLottieCompositionAsState(composition)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(composition, progress, modifier = Modifier.size(150.dp, 150.dp))
        Text(
            "AppCoffee",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = CoffeText
        )

    }
}



@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    val navController = rememberNavController()
    SplashScreen(navController)
}