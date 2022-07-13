package com.example.coffe_app_jetpack.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.toSize

typealias onChangeValue = (String) -> Unit

@Composable
fun DropDownTextField(
    suggestions: List<String>,
    value: String,
    placheHolder: String,
    onChangeValue: onChangeValue
) {
    var expanded by remember { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var icon = Icons.Filled.ArrowDropDown

    Column {
        CustomTextField(
            value = value,
            placeHolder = placheHolder,
            onValueChange = onChangeValue,
            enable = false,
            trailingIcon = {
                Icon(icon, contentDescription = null, modifier = Modifier.clickable {
                    expanded = !expanded
                })
            },
            onGloballyPositioned = {coordinate ->
                textFieldSize = coordinate.size.toSize()
            }
        )
        DropdownMenu(expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.width(with(LocalDensity.current) {
                textFieldSize.width.toDp()
            })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    onChangeValue(label)
                }) {
                    Text(label, style = MaterialTheme.typography.body2)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownTextFieldPreview() {
    val cities = listOf(
        "Ciudad de México, México",
        "La Habana, Cuba",
        "Lima, Perú",
        "Medellín, Colombia"
    )
    DropDownTextField(cities, "", "Ciudad"){

    }
}