package com.example.projekt_51731.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projekt_51731.R
import com.example.projekt_51731.model.Todo
import com.example.projekt_51731.utilis.Routes
import com.example.projekt_51731.vievmodel.TodoViewModel
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListPage(viewModel: TodoViewModel, navController: NavController, activity: MainActivity) {
    val db = Firebase.firestore

    var title by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var editingTask by remember { mutableStateOf<Todo?>(null) }
    var tasks by remember { mutableStateOf(listOf<Todo>()) }

    DisposableEffect(Unit) {
        val registration: ListenerRegistration = db.collection("tasks")
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("TodoListPage", "Listen failed.", error)
                    return@addSnapshotListener
                }
                tasks = snapshot?.documents
                    ?.mapNotNull { it.toObject(Todo::class.java)?.copy(id = it.id) }
                    ?: emptyList()
            }
        onDispose { registration.remove() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
        text = "Powróć do ekranu home",
        color = colorResource(id = R.color.black),
        modifier = Modifier
            .clickable { navController.navigate(Routes.homepage) },
    )
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Tyuł", color = colorResource(R.color.black)) },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.purple_69),
                    unfocusedBorderColor = colorResource(id = R.color.pink_69))
            )
            Spacer(modifier = Modifier.height(1.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text(text = "Opis", color = colorResource(R.color.black)) },
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = colorResource(id = R.color.purple_69),
            unfocusedBorderColor = colorResource(id = R.color.pink_69))
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (editingTask != null) {
                    // update existing
                    db.collection("tasks").document(editingTask!!.id.toString())
                        .set(hashMapOf(
                            "title" to title,
                            "description" to desc,
                            "modifiedAt" to System.currentTimeMillis()
                        ))
                    editingTask = null
                } else {
                    // add new
                    db.collection("tasks").add(
                        hashMapOf(
                            "title" to title,
                            "description" to desc,
                            "modifiedAt" to System.currentTimeMillis()
                        )
                    )
                }
                title = ""; desc = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(if (editingTask == null) "Add" else "Save")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .padding(12.dp)
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = SimpleDateFormat("HH:mm aa, dd/MM/yyyy", Locale.ENGLISH)
                                .format(task.modifiedAt),
                            fontSize = 12.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = task.title,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                        Text(
                            text = task.description,
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    IconButton(onClick = { editingTask = task }) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
                    }
                    IconButton(onClick = { db.collection("tasks").document(task.id.toString()).delete() }) {
                        Icon(Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}
