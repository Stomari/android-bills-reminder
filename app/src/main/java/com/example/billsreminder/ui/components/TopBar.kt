package com.example.billsreminder.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    modifier: Modifier = Modifier,
    useNavigateBack: Boolean = false,
    navigateBack: (() -> Unit)? = {}
) {
    TopAppBar(
        title = { Text(text = title) },
        modifier = modifier,
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        navigationIcon = {
            if (useNavigateBack && navigateBack !== null) {
                IconButton(onClick = { navigateBack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
                }
            }
        }
    )
}

@Preview
@Composable
private fun TopBarPreview() {
    TopBar(title = "Name", useNavigateBack = true)
}