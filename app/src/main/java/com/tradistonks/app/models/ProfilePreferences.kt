package com.tradistonks.app.models

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.tradistonks.app.ACCESS_TOKEN
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.IOException

class ProfilePreferences {
    var datastore: DataStore<Preferences>? = null
    var token = preferencesKey<String>("token")

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
    ): Flow<T> {
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
}