package com.tradistonks.app.models

import android.content.Context
import androidx.compose.runtime.collectAsState
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.tradistonks.app.components.Order
import com.tradistonks.app.components.Strategy
import com.tradistonks.app.components.User
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*

class ProfilePreferences {
    var datastore: DataStore<Preferences>? = null
    var token = preferencesKey<String>("token")

    fun getToken(): String {
        return datastore?.getValueFlow(preferencesKey<String>("token"), "").toString()
    }

    fun setToken(tokenString: String) {
        this.token = preferencesKey<String>("token")
        GlobalScope.launch {
            datastore?.setValue(token, tokenString)
        }
    }

    constructor(datastore: DataStore<Preferences>?, token: Preferences.Key<String>) {
        this.datastore = datastore
        this.token = token
    }

    constructor(context: Context) {
        this.datastore = context.createDataStore(name = "profile")
        this.token = preferencesKey<String>("token")
        GlobalScope.launch {
            datastore?.setValue(token, "")
        }
    }

    fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T>? {
        return this.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

    suspend fun <T> DataStore<Preferences>.setValue(key: Preferences.Key<T>, value: T) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }

    suspend fun <T> Flow<List<T>>.flattenToList() =
        flatMapConcat { it.asFlow() }.toList()
}