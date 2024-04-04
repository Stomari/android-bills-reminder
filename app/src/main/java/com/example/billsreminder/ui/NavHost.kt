package com.example.billsreminder.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.billsreminder.ui.screens.form.ServiceFormScreen
import com.example.billsreminder.ui.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object ServiceForm: Screen("serviceForm")
}

@Composable
fun BillsReminderNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onAddServiceButtonClick = {
                    navController.navigate(Screen.ServiceForm.route)
                }
            )
        }
        composable(route = Screen.ServiceForm.route) {
            ServiceFormScreen(
                navigateBack =  {
                    navController.popBackStack()
                }
            )
        }
    }
}