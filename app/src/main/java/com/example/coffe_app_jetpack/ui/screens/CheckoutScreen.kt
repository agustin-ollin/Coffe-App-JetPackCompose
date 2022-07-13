package com.example.coffe_app_jetpack.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.coffe_app_jetpack.models.Product
import com.example.coffe_app_jetpack.ui.components.*
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme
import com.example.coffe_app_jetpack.utilities.MockDataProvider

@Composable
fun CheckoutScreen(navController: NavController, product: Product){
    val cities = listOf(
        "Ciudad de México, México",
        "La Habana, Cuba",
        "Lima, Perú",
        "Medellín, Colombia"
    )

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var city by remember { mutableStateOf("") }
    var message by remember { mutableStateOf<String?>(null) }
    var messageError by remember { mutableStateOf<String?>(null) }

    Scaffold(topBar = {
        CustomAppBar(navigationIcon = Icons.Filled.ArrowBack){
            navController.navigate("detail/${product.id}")
        }
    }, content = {
        Alert(title = "Error", message = messageError) {
            navController.navigate("checkout/${product.id}"){
                popUpTo("checkout/${product.id}")
            }
        }
        Alert(title = "Felicidades", message = message) {
            navController.navigate("feed") {
                launchSingleTop = true
                popUpTo("feed")
            }
        }
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            ProductCard(product) {}

            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                CustomTextField(value = name, placeHolder = "Nombre Completo"){
                    name = it
                }
                CustomTextField(value = email, placeHolder = "Correo Electrónico"){
                    email = it
                }
                CustomTextField(value = phone, placeHolder = "Número de Teléfono"){
                    phone = it
                }
                CustomTextField(value = address, placeHolder = "Dirección"){
                    address = it
                }
                DropDownTextField(suggestions = cities, value = city, placheHolder = "Ciudad"){
                    city = it
                }

                Column {
                    Row {
                        Text("Subtotal", style = MaterialTheme.typography.caption)
                        Text("$ 43.0 USD", style = MaterialTheme.typography.body2, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
                    }
                    Row {
                        Text("Envio", style = MaterialTheme.typography.caption)
                        Text("$ 10.0 USD", style = MaterialTheme.typography.body2, textAlign = TextAlign.End, modifier = Modifier.fillMaxWidth())
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        "43.0 USD",
                        style = MaterialTheme.typography.h5,
                        textAlign = TextAlign.Start
                    )
                    CustomButton(label = "Finalizar Pedido") {
                        if (name != "" && email != "" && phone != "" && address != "" && city != ""){
                            message = "Su compra se ha realizado con éxito!!"
                        } else {
                            messageError = "Ingrese todos los campos para proceder con su compra"
                        }
                    }
                }
            }
        }

    })
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview(){
    val navController = rememberNavController()
    val product: Product = MockDataProvider.productById(1)!!

    if (product != null){
        CoffeAppJetPackTheme() {
            CheckoutScreen(navController, product)
        }
    } else {
        Text(text = "Error en busqueda de productos")
    }
}