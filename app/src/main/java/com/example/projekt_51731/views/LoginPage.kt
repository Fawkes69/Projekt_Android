package com.example.projekt_51731.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projekt_51731.R
import com.example.projekt_51731.utilis.Routes
import com.example.projekt_51731.utilis.UserPreferences
import kotlinx.coroutines.launch


@Composable
fun LoginPage(navController: NavController, context: MainActivity){
    val image = painterResource(R.drawable.logo)
    Box(modifier = Modifier.fillMaxWidth(),
    ) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(24.dp)
                .size(129.dp)
                .align(Alignment.TopCenter)

        )
    }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        )
    {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var errorMessage by remember { mutableStateOf<String?>(null) }
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        Text("Sign In",
            fontSize = 15.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold,

            color = colorResource(id= R.color.purple_69),

            )
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Email or User Name") }
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }

        )
        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }

        Button(
            onClick = {
                if (username == "1" && password == "2") {
                    scope.launch {
                        UserPreferences.setLoggedIn(context, true)
                        navController.navigate(Routes.homepage) {
                            popUpTo(Routes.loginPage) { inclusive = true }
                        }
                    }
                } else {
                    errorMessage = "Nieprawidłowy login lub hasło"
                }
            },

        ) {
            Text("Sign In")
        }
        Text(
            modifier = Modifier,
            textAlign = TextAlign.Center,
            text = "Or Sign in with:",

        )
    }
}