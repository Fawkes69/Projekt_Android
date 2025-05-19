package com.example.projekt_51731.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projekt_51731.ui.theme.TodoAppTheme
import com.example.projekt_51731.utilis.Routes
import com.example.projekt_51731.utilis.UserPreferences
import com.example.projekt_51731.vievmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        setContent {
            TodoAppTheme {
                val navController = rememberNavController()
                var isLoggedIn by remember { mutableStateOf<Boolean?>(null) }

                LaunchedEffect(Unit) {
                    isLoggedIn = UserPreferences.isLoggedIn(this@MainActivity)
                }

                if (isLoggedIn != null) {
                    NavHost(
                        navController = navController,
                        startDestination = if (isLoggedIn == true) Routes.homepage else Routes.loginPage
                    ) {
                        composable(route = Routes.loginPage) {
                            LoginPage(navController, this@MainActivity)
                        }
                        composable(route = Routes.registerPage) {
                            RegisterPage(navController, this@MainActivity)
                        }
                        composable(route = Routes.homepage) {
                            HomePage(navController, this@MainActivity)
                        }
                        composable(route = Routes.databasePage) {
                            TodoListPage(todoViewModel, navController, this@MainActivity)
                        }
                    }
                } else {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}