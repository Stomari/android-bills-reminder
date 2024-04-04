package com.example.billsreminder.ui.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.billsreminder.R
import com.example.billsreminder.ui.components.TopBar

@Composable
fun ServiceFormScreen(modifier: Modifier = Modifier, navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            TopBar(
                title = "Add Service",
                useNavigateBack = true,
                navigateBack = navigateBack
            )
        },
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_small)),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Input(value = "", onValueChange = { /*TODO*/ }, label = "Name")
                Input(value = "", onValueChange = { /*TODO*/ }, label = "Description")
                Input(
                    value = "",
                    onValueChange = { /*TODO*/ },
                    label = "Price",
                    keyboardType = KeyboardType.Number
                )
            }
        }
    }
}

@Composable
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: () -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = { onValueChange() },
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )
}

@Preview
@Composable
private fun InputPreview() {
    Input(value = "", onValueChange = {}, label = "Name")
}