package com.example.billsreminder.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.billsreminder.ui.screens.form.ServiceFormScreen
import com.example.billsreminder.ui.screens.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object ServiceForm : Screen("service_form")
    object EditServiceForm : Screen("edit_service_from")
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
                },
                navigateToEditScreen = {
                    navController.navigate("${Screen.EditServiceForm.route}/$it")
                }
            )
        }
        composable(route = Screen.ServiceForm.route) {
            ServiceFormScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
        composable(
            route = "${Screen.EditServiceForm.route}/{serviceId}",
            arguments = listOf(navArgument("serviceId") { type = NavType.IntType })
        ) {
            ServiceFormScreen(
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}