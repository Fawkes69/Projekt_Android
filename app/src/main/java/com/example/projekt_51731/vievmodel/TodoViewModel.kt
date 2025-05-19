package com.example.projekt_51731.vievmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.projekt_51731.db.TodoDatabase
import com.example.projekt_51731.db.TodoRepository
import com.example.projekt_51731.model.Todo
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application) {
    private val repo: TodoRepository
    val todoList: LiveData<List<Todo>>

    init {
        val dao = TodoDatabase.getDatabase(application).todoDao()
        repo = TodoRepository(dao)
        todoList = repo.allTodos
    }

    fun addTodo(title: String, description: String) = viewModelScope.launch {
        if (title.isNotBlank() || description.isNotBlank()) {
            repo.add(Todo(id = "", title = title, description = description))
        }
    }

    fun updateTodo(todo: Todo) = viewModelScope.launch {
        repo.update(todo.copy(modifiedAt = System.currentTimeMillis()))
    }

    fun deleteTodo(todo: Todo) = viewModelScope.launch {
        repo.delete(todo)
    }
}
