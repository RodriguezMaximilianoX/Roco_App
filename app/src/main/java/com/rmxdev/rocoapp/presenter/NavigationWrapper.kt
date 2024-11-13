package com.rmxdev.rocoapp.presenter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmxdev.rocoapp.presenter.home.HomeScreen
import com.rmxdev.rocoapp.presenter.initial.InitialScreen
import com.rmxdev.rocoapp.presenter.login.LoginScreen
import com.rmxdev.rocoapp.presenter.resetpassword.ResetPasswordScreen

@Composable
fun NavigationWrapper(
    modifier: Modifier = Modifier,
    startDestination: String,
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("initial") {
            InitialScreen(
                modifier = modifier,
                navigateToLogin = { navController.navigate("login") },
                navigateToSignUp = { navController.navigate("signup") }
            )
        }
        composable("login") {
            LoginScreen(
                modifier = modifier,
                onLoginSuccess = { navController.navigate("home") },
                resetPassword = {navController.navigate("resetpassword")}
            )
        }
        composable("signup") {
            //SignUpScreen(modifier = modifier)
        }
        composable("home") {
            HomeScreen(modifier = modifier)
        }
        composable("resetpassword") {
            ResetPasswordScreen(
                modifier = modifier,
                onResetPasswordSuccess = {
                    navController.navigate("login")
                }
            )
        }
    }
}
