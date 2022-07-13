package com.example.coffe_app_jetpack.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coffe_app_jetpack.R
import com.example.coffe_app_jetpack.models.Product
import com.example.coffe_app_jetpack.ui.theme.CoffeAppJetPackTheme
import com.example.coffe_app_jetpack.ui.theme.proBlue
import com.example.coffe_app_jetpack.ui.theme.proGreen
import com.example.coffe_app_jetpack.utilities.MockDataProvider

enum class CountryISO(val iso: String) {
    CRI("CRI"),
    ETP("ETP"),
    MXN("MXN"),
    JAV("JAV");

    fun getBackgroundImage(): Int = when (this) {
        CRI -> R.drawable.costarica
        ETP -> R.drawable.etiopia
        MXN -> R.drawable.mexico
        JAV -> R.drawable.java
        else -> {
            0
        }
    }

    fun getCountryFlag(): Int = when (this) {
        CRI -> R.drawable.banderacostarica
        ETP -> R.drawable.banderaetiopia
        MXN -> R.drawable.banderamexico
        JAV -> R.drawable.banderajava
        else -> {
            0
        }
    }

    fun getSurfaceColor(): Color = when (this) {
        CRI, ETP -> proBlue
        MXN, JAV -> proGreen
    }
}

typealias SelectionAction = () -> Unit

@Composable
fun ProductCard(
    product: Product,
    selected: SelectionAction
) {
    var country = CountryISO.valueOf(product.countryISO) ?: CountryISO.MXN

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                selected()
            }
            .size(300.dp),
        elevation = 10.dp,
        shape = MaterialTheme.shapes.medium
    ) {
        Image(
            painter = painterResource(id = country.getBackgroundImage()),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = country.getSurfaceColor().copy(alpha = 0.2f)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(product.name, style = MaterialTheme.typography.h4)
                Text(product.summary, style = MaterialTheme.typography.body1)
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Row {
                        Image(
                            painter = painterResource(id = country.getCountryFlag()),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp, 32.dp)
                        )
                        Text(
                            "$ ${product.pruce} ${product.currency}",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            style = MaterialTheme.typography.h4
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductCardPreview() {
    val product: Product = MockDataProvider.productById(1)!!
    
    if (product != null){
        CoffeAppJetPackTheme {
            ProductCard(product){}
        }
    } else {
        Text(text = "Error en busqueda de productos")
    }
}