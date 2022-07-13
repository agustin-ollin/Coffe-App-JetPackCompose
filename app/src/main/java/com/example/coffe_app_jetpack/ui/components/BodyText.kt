package com.example.coffe_app_jetpack.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme


@Composable
fun BodyText(body: String) {
    Text(
        body, style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Justify
    )
}

@Preview(showBackground = true)
@Composable
fun BodyTextPreview() {
    CoffeAppJetPackTheme {
        BodyText("Contenido del componente de texto para el cuerpo o texto con descripción larga")
    }
}