package com.example.projekt_51731.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projekt_51731.utilis.Routes
import com.example.projekt_51731.utilis.UserPreferences
import kotlinx.coroutines.launch

@Composable
fun HomePage(navController: NavController, context: MainActivity) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Welcome to Home Page!")


        Button(onClick = {
            scope.launch {
                UserPreferences.logout(context)
                navController.navigate(Routes.loginPage) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Log out")
        }
    }
}