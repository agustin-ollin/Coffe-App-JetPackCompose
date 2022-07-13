package com.example.coffe_app_jetpack.ui.screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffe_app_jetpack.R
import com.example.coffe_app_jetpack.ui.components.*
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme
import com.example.coffe_app_jetpack.utilities.MockDataProvider

@Composable
fun FeedScreen(navController: NavController) {
    val list = MockDataProvider.listsProducts()

    CoffeAppJetPackTheme {
        Scaffold(
            topBar = { CustomAppBar("Coffe for Coders") },
            content = {
                Surface(color = MaterialTheme.colors.background) {
                    Image(
                        painter = painterResource(id = R.drawable.fondo2),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.FillBounds
                    )
                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        item {
                            Column(
                                modifier = Modifier.padding(8.dp)
                            ) {
                                TitleText("Bienvenido")
                                BodyText("Aplicación para comprar café")
                            }
                        }
                        items(list) { product ->
                            ProductCard(product) {
                                navController.navigate("detail/${product.id}") {
                                    launchSingleTop = true
                                }
                            }
                        }
                    }
                }
            }
        )

    }
}

@Preview(showSystemUi = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun FeedScreenPreview() {
    val navController = rememberNavController()
    FeedScreen(navController)
}