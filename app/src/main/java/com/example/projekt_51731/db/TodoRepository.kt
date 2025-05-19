package com.example.projekt_51731.db

import androidx.lifecycle.LiveData
import com.example.projekt_51731.model.Todo

class TodoRepository(private val dao: TodoDao) {
    val allTodos: LiveData<List<Todo>> = dao.getAll()

    suspend fun add(todo: Todo) = dao.insert(todo)
    suspend fun update(todo: Todo) = dao.update(todo)
    suspend fun delete(todo: Todo) = dao.delete(todo)
}