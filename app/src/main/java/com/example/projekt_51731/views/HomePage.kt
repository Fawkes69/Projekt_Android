package com.example.projekt_51731.views

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.core.net.toUri

@SuppressLint("QueryPermissionsNeeded")
@Composable
fun HomePage(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val szerGeo = 51.11432917831542
    val dluGeo = 16.991911913584264

    Column(
        Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Witam na stronie głównej")
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {navController.navigate(Routes.databasePage)}, modifier = Modifier.fillMaxWidth())  {
            Text("Przejdź do bazy danych")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            val gmmIntentUri =
                "geo:$szerGeo,$dluGeo?q=$szerGeo,$dluGeo(Lokalizacja)".toUri()
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")

            if (mapIntent.resolveActivity(context.packageManager) != null) {
                context.startActivity(mapIntent)
            } else {
                Toast.makeText(context, "Google Maps nie jest zainstalowane", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Otwórz Google Maps")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            scope.launch {
                UserPreferences.logout(context)
                navController.navigate(Routes.loginPage) {
                    popUpTo(0) { inclusive = true }
                }
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Wylogowanie")
        }
    }
}
