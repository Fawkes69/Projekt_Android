package com.example.projekt_51731.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projekt_51731.R
import com.example.projekt_51731.utilis.Routes
import com.example.projekt_51731.utilis.UserPreferences
import kotlinx.coroutines.launch


@Composable
fun LoginPage(navController: NavController){
    val imageLog = painterResource(R.drawable.logo)
    val xImage = painterResource(R.drawable.x)
    val inImage = painterResource(R.drawable.`in`)
    val gogImage = painterResource(R.drawable.google)
    val fbImage = painterResource(R.drawable.facebook)

    var passwordVisible by remember { mutableStateOf(false) }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.Start,
        )
    {

        Box(modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = imageLog,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(24.dp)
                    .size(129.dp)
                    .align(Alignment.TopCenter)

            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text("Sign In",
            fontSize = 21.sp,
            modifier = Modifier,
            fontWeight = FontWeight.Bold,
            color = colorResource(id= R.color.purple_69),

            )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = username,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            shape = MaterialTheme.shapes.medium,
            onValueChange = { username = it },
            label = { Text("Email or User Name") },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Person,
                    contentDescription = null,
                    tint = colorResource(id = R.color.purple_69)
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.pink_69),
                unfocusedBorderColor = colorResource(id = R.color.purple_69))
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Confirm Password") },
            leadingIcon = {
                Icon(
                    Icons.Outlined.Lock,
                    contentDescription = null,
                    tint = colorResource(id = R.color.purple_69)
                )
            },
            trailingIcon = {
                val image = if (passwordVisible) Icons.Outlined.VisibilityOff else Icons.Outlined.Visibility
                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(
                        imageVector = image,
                        contentDescription = null,
                        tint = colorResource(id = R.color.purple_69)
                    )
                }
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorResource(id = R.color.pink_69),
                unfocusedBorderColor = colorResource(id = R.color.purple_69)
            ),
            shape = MaterialTheme.shapes.medium
        )
        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }
        Spacer(modifier = Modifier.height(15.dp))

        Text(modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
            color = colorResource(R.color.purple_69),
            text = "Forgot password?"
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.pink_69)),
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
            Text("Sign Up")
        }
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "Or Sign in with:"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(painter = gogImage,
                contentDescription = null)
            Image(painter = fbImage,
                contentDescription = null)
            Image(painter = xImage,
                contentDescription = null)
            Image(painter = inImage,
                contentDescription = null)
        }

        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Don't have account? ", color = Color.Gray)
            Text(
                text = " Sing Up",
                color = colorResource(id = R.color.purple_69),
                modifier = Modifier
                    .clickable { navController.navigate(Routes.registerPage) },

                )



            }
    }
}