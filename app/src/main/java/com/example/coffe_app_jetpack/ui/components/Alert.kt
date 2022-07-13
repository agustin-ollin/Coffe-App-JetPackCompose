package com.example.coffe_app_jetpack.ui.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme

@Composable
fun Alert(title: String, message: String?, onClose: () ->Unit){
    if (message != null){
        AlertDialog(
            onDismissRequest = { onClose() },
            title = {
            Text(title, style = TextStyle(color = Color.Black))
            }, 
            text = {
                Text(message)
            },
            confirmButton = {
                Button(onClick = { onClose() }) {
                    Text(text = "OK")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AlertPreview(){
    CoffeAppJetPackTheme {
        Alert("Mensaje de error", "Ingrese todos los datos!!"){

        }
    }
}