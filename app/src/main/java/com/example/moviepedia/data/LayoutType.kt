package com.example.moviepedia.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LayoutType (private val context: Context){
    companion object{
        private val Context.datastore : DataStore<Preferences> by preferencesDataStore("layout_type")
        val CURRENT_LAYOUT_TYPE = intPreferencesKey("current_layout")
    }

    // the value 0 represents GRID and the value 1 represents LIST
    val currentLayoutType : Flow<Int> = context.datastore.data.map {
        it[CURRENT_LAYOUT_TYPE] ?: 0
    }

    suspend fun flipCurrentLayout(){
        context.datastore.edit {
            it[CURRENT_LAYOUT_TYPE] = 1 - (it[CURRENT_LAYOUT_TYPE] ?: 0)
        }
    }
}