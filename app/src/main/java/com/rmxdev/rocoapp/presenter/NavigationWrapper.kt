package com.rmxdev.rocoapp.presenter

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmxdev.rocoapp.presenter.initial.InitialScreen
import com.rmxdev.rocoapp.presenter.login.LoginScreen

@Composable
fun NavigationWrapper(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "initial"){
        composable("initial"){
            InitialScreen(

                modifier = modifier,
                navigateToLogin = { navController.navigate("login") },
                navigateToSignUp = { navController.navigate("signup") }
            )
        }
        composable("login"){
            LoginScreen(
                modifier = modifier,
                onLoginSuccess = { navController.navigate("home")},
                resetPassword = {}
            )
        }
        composable("signup"){
            //SignUpScreen(modifier = modifier)
        }
    }
}
