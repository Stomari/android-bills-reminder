package com.example.billsreminder.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.billsreminder.R
import com.example.billsreminder.data.Service

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onAddServiceButtonClick: () -> Unit,
    viewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory)
) {
    Scaffold(
        floatingActionButton = { AddServiceButton(onClick = { onAddServiceButtonClick() }) },
        floatingActionButtonPosition = FabPosition.End,
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Resume(billsAmount = 10, billsLeftToPay = 7, modifier = Modifier.fillMaxWidth())
            ServicesList(services = listOf())
        }
    }
}

@Composable
fun Resume(modifier: Modifier = Modifier, billsAmount: Int, billsLeftToPay: Int) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.primary)) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
        ) {
            Text(
                text = "$billsAmount total bills",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                text = "$billsLeftToPay left to pay",
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun ServicesList(modifier: Modifier = Modifier, services: List<Service>) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        items(services) { service ->
            Card(
                shape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 50.dp),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(dimensionResource(id = R.dimen.padding_small))
                        .height(80.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = service.name, style = MaterialTheme.typography.titleMedium)
                    if (!service.description.isNullOrEmpty()) {
                        Text(text = service.description)
                    }
                    if (service.price !== null) {
                        Text(text = "R$ ${service.price.toString()}")
                    }
                }
            }
        }
    }
}

@Composable
fun AddServiceButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    LargeFloatingActionButton(onClick = { onClick() }, modifier = modifier, shape = CircleShape) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "Add service")
    }
}

@Preview
@Composable
private fun ServicesListPreview() {
    val services = listOf(
        Service(id = 1, name = "Netflix", description = "Subscription", price = 35.0),
        Service(id = 2, name = "Luz", price = 100.0),
        Service(id = 3, name = "Cartão de crédito", description = "Extras")
    )
    ServicesList(services = services)
}

@Preview
@Composable
private fun ResumePreview() {
    Resume(billsAmount = 10, billsLeftToPay = 6, modifier = Modifier.fillMaxWidth())
}

@Preview()
@Composable
private fun AddServiceButtonPreview() {
    AddServiceButton(onClick = {})
}