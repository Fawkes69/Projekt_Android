package com.example.projekt_51731.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.projekt_51731.R

@Composable
fun LoginPage(navController: NavController){
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment =  Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
        LogImage()
        SimpleInputText()
        Text(
            modifier = Modifier,
            text = "Sign In",
        )
        Button(

            onClick = { navController.navigate("register_page") }

        ) {
            Text(text = "Sign In")
        }
    }
}
@Composable
fun LogImage() {
    val image = painterResource(R.drawable.logo)
    Box(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(129.dp)
                .align(Alignment.TopStart)
        )
    }

}
@Composable
fun SimpleInputText() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text("Label") }
    )
}