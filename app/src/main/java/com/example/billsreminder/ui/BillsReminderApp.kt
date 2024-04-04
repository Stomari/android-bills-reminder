package com.example.billsreminder.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@Composable
fun BillsReminderApp() {
    val navController = rememberNavController()

    Scaffold() { innerPadding ->
        BillsReminderNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}