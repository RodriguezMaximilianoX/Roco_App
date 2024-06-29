package com.rmxdev.rocoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rmxdev.rocoapp.ui.content.home.HomeScreen
import com.rmxdev.rocoapp.ui.theme.RocoAppTheme
import com.rmxdev.rocoapp.ui.content.vaccination.VaccinationCardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RocoAppTheme {

                val navController = rememberNavController()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { }
                ) { paddingValues ->
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen(modifier = Modifier.padding(paddingValues)){
                                navController.navigate("vaccination card")
                            }
                        }
                        composable("vaccination card"){
                            VaccinationCardScreen(modifier = Modifier.padding(paddingValues))
                        }
                    }
                }
            }
        }
    }
}
