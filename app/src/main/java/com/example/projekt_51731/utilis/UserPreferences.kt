package com.example.projekt_51731.utilis

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object UserPreferences {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")

    private val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

    suspend fun setLoggedIn(context: Context, isLoggedIn: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[IS_LOGGED_IN] = isLoggedIn
        }
    }

    suspend fun isLoggedIn(context: Context): Boolean {
        val prefs = context.dataStore.data.first()
        return prefs[IS_LOGGED_IN] ?: false
    }

    suspend fun logout(context: Context) {
        setLoggedIn(context, false)
    }
}