package com.luckgg.rmchars

import CharacterScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.luckgg.rmchars.presentation.screens.CharacterDetailsScreen
import com.luckgg.rmchars.presentation.screens.Screen
import com.luckgg.rmchars.presentation.ui.theme.RMCharsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RMCharsTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = Screen.CharacterListScreen.route) {
                        composable(
                            route = Screen.CharacterListScreen.route,
                        ) {
                            CharacterScreen(navController)
                        }
                        composable(
                            Screen.CharacterDetailScreen.route,
                        ) { _ ->
                            CharacterDetailsScreen(navController)
                        }
                    }
                }
            }
        }
    }
}
