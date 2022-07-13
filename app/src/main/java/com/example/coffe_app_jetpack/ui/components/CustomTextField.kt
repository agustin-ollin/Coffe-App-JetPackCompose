package com.example.coffe_app_jetpack.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme

import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CustomTextField(
    value: String,
    placeHolder: String,
    enable: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    onGloballyPositioned: ((LayoutCoordinates) -> Unit)? = null,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = TextStyle(color = Color.Black),
        label = {
            Text(text = placeHolder, style = MaterialTheme.typography.caption)
        },
        modifier = Modifier.fillMaxWidth().onGloballyPositioned { cordinates ->
            if(onGloballyPositioned != null){
                onGloballyPositioned(cordinates)
            }
        },
        enabled = enable,
        keyboardActions = KeyboardActions(
            onDone = {focusManager.clearFocus()}
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Text
        ),
        trailingIcon = trailingIcon
    )
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    CustomTextField("", "TextField") {

    }
}