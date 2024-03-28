import android.content.Context
import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class SharedPreferenceService (private val context: Context){
    companion object{
    private const val PREF_NAME = "UserPreferences"
    private val KEY_TOKEN = stringPreferencesKey("token")
    private val KEY_NAME = stringPreferencesKey("username")
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREF_NAME)

    }

//    private const val KEY_NAME = "username"

//    private var sharedPreferences: SharedPreferences? = null

    // At the top level of your kotlin file:
//    suspend fun saveExampleCounter(context: Context) {
//        val EXAMPLE_COUNTER = intPreferencesKey("token")
//        val exampleCounterFlow: Flow<String> = context.dataStore.data.map { preferences ->
//            // No type safety.
//            preferences[EXAMPLE_COUNTER] ?: 0
//        }
//
//    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[KEY_TOKEN] = token
        }
    }

//    suspend fun getToken(context: Context): String? {
//        val preferences = context.dataStore.data.first()
//        return preferences[KEY_TOKEN]
//    }

    fun getToken(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_TOKEN]
        }
    }

    suspend fun saveUsername(username: String) {
        context.dataStore.edit {
            it[KEY_NAME] = username
        }
        println("saved name")
    }
    fun getUsername(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_NAME]
        }
    }

//    suspend fun getUsername(context: Context): String? {
//        val preferences = context.dataStore.data.first()
//        return preferences[KEY_NAME]
//    }

//    fun initialize(context: Context) {
//        if (sharedPreferences == null) {
//            // At the top level of your kotlin file:
////            sharedPreferences=Context.s DataStore<Preferences> by preferencesDataStore(name = "settings")
//            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        }
//    }

//    fun saveToken(token: String) {
//        sharedPreferences?.edit()?.putString(KEY_TOKEN, token)?.apply()
//    }

//    fun saveUsername(token: String) {
//        sharedPreferences?.edit()?.putString(KEY_NAME, token)?.apply()
//    }
//
//    fun getToken(): String? {
//        return sharedPreferences?.getString(KEY_TOKEN, null)
//    }
//
//    fun getUsername(): String? {
//        return sharedPreferences?.getString(KEY_NAME, null)
//    }
//
//    fun deleteToken() {
//        sharedPreferences?.edit()?.remove(KEY_TOKEN)?.apply()
//    }
//
//    fun hasToken(): Boolean {
//        return getToken() != null
//    }

    //    fun clearSharedPreferences() {
////        val sharedPreferences = // Get reference to your SharedPreferences instance
//        val editor = sharedPreferences?.edit()
//        editor?.clear()
//        editor?.apply()
//    }
    suspend fun hasToken(context: Context): Boolean {
        val preferences = context.dataStore.data.first()
        return preferences.contains(KEY_TOKEN)
    }

//    fun hasToken(context: Context): Flow<Boolean?> {
//        return context.dataStore.data.map {
//            it.contains(KEY_TOKEN)
//        }
//    }

    suspend fun clearDataStore(context: Context) {
        context.dataStore.edit {
            it.clear()
        }
    }
}
