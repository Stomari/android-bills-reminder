package com.example.billsreminder.ui.screens.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billsreminder.R
import com.example.billsreminder.ui.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun ServiceFormScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    viewModel: ServiceFormScreenViewModel = viewModel(factory = ServiceFormScreenViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopBar(
                title = "Add Service", useNavigateBack = true, navigateBack = navigateBack
            )
        }, modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .weight(1F)
                    .padding(dimensionResource(id = R.dimen.padding_big)),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Input(
                    value = uiState.name,
                    onValueChange = { viewModel.updateServiceFormState(uiState.copy(name = it)) },
                    label = "Name"
                )
                Input(
                    value = uiState.description,
                    onValueChange = { viewModel.updateServiceFormState(uiState.copy(description = it)) },
                    label = "Description"
                )
                Input(
                    value = uiState.price,
                    onValueChange = { viewModel.updateServiceFormState(uiState.copy(price = it)) },
                    label = "Price",
                    keyboardType = KeyboardType.Number,
                    prefix = { Text(text = "R$") }
                )
            }
            Buttons(
                modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.padding_big)),
                onCancelClick = { navigateBack() },
                onSaveClick = {
                    coroutineScope.launch {
                        viewModel.addService()
                        navigateBack()
                    }
                }
            )
        }
    }
}

@Composable
fun Input(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    prefix: @Composable () -> Unit = {}
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label) },
        modifier = modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        prefix = prefix,
        singleLine = true
    )
}

@Composable
fun Buttons(modifier: Modifier = Modifier, onCancelClick: () -> Unit, onSaveClick: () -> Unit) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        OutlinedButton(onClick = { onCancelClick() }, modifier = Modifier.width(100.dp)) {
            Text(text = "Cancel")
        }
        Button(onClick = { onSaveClick() }, modifier = Modifier.width(100.dp)) {
            Text(text = "Save")
        }
    }
}

@Preview
@Composable
private fun ButtonsPreview() {
    Buttons(onCancelClick = {}, onSaveClick = {})
}

@Preview
@Composable
private fun InputPreview() {
    Input(value = "", onValueChange = {}, label = "Name")
}