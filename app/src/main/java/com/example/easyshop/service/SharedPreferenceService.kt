////package com.example.auth.service
////
////import android.content.Context
////import android.content.SharedPreferences
////import androidx.datastore.core.DataStore
////import androidx.datastore.preferences.core.Preferences
////import androidx.datastore.preferences.core.booleanPreferencesKey
////import androidx.datastore.preferences.core.edit
////import androidx.datastore.preferences.core.intPreferencesKey
////import androidx.datastore.preferences.preferencesDataStore
////
//////class SharedPreferenceService(context: Context) {
////class SharedPreferenceService() {
////    companion object{
//////        val dataStore: DataStore<Preferences> = context.createDataStore(name = "my_preferences")
////        // At the top level of your kotlin file:
////        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")
//////        private val sharedPreferences: SharedPreferences =
//////        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
////
////        suspend fun incrementCounter(key: String,value:String) {
////            Context.dataStore.edit { preferences ->
//////                val currentCounterValue = settings[key] ?: 0
////                preferences[value] =value
////            }
////        }
//////        suspend fun saveData(key: String, value: String) {
//////        dataStore.edit { preferences ->
//////            preferences[booleanPreferencesKey(key)] = value
//////            preferences[intPreferencesKey("userScore")] = 100
//////        }
//////    }
//////        fun saveData(key: String, value: String) {
//////        val editor = sharedPreferences.edit()
//////        editor.putString(key, value)
//////        editor.apply()
//////    }
////
////    fun getData(key: String, defaultValue: String): String {
////        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
////    }
////
////    }
////}
//
//
//import android.content.Context
//import androidx.datastore.core.DataStore
//import androidx.datastore.preferences.core.Preferences
//import androidx.datastore.preferences.core.edit
//import androidx.datastore.preferences.core.stringPreferencesKey
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.flow.map
//
//class SharedPreferenceService {
//    private val dataStore: DataStore<Preferences> = createDataStore(name = "preferences")
//    companion object {
//        suspend fun incrementCounter(context: Context, key: String, value: String) {
//            val dataStoreKey = stringPreferencesKey(key)
//            context.dataStore.edit { preferences ->
//                val currentValue = preferences[dataStoreKey] ?: "0"
//                preferences[dataStoreKey] = (currentValue.toInt() + value.toInt()).toString()
//            }
//        }
//
//        suspend fun getData(context: Context, key: String, defaultValue: String): String {
//            val dataStoreKey = stringPreferencesKey(key)
//            val preferences = context.dataStore.data.first()
//            return preferences[dataStoreKey] ?: defaultValue
//        }
//    }
//}
