package com.example.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "agrilens_prefs")

class PreferencesRepository(private val context: Context) {

    private object Keys {
        val EXPERT_LANGUAGE = stringPreferencesKey("expert_language")
        val SHOW_SPEAKER_NOTES = booleanPreferencesKey("show_speaker_notes")
    }

    val expertLanguage: Flow<String> = context.dataStore.data.map { prefs ->
        prefs[Keys.EXPERT_LANGUAGE] ?: "English"
    }

    val showSpeakerNotes: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[Keys.SHOW_SPEAKER_NOTES] ?: true
    }

    suspend fun setExpertLanguage(language: String) {
        context.dataStore.edit { prefs ->
            prefs[Keys.EXPERT_LANGUAGE] = language
        }
    }

    suspend fun setShowSpeakerNotes(show: Boolean) {
        context.dataStore.edit { prefs ->
            prefs[Keys.SHOW_SPEAKER_NOTES] = show
        }
    }
}
