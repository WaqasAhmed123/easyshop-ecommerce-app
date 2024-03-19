import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceService {

    private const val PREF_NAME = "MyAppPreferences"
    private const val KEY_TOKEN = "token"
    private const val KEY_NAME = "username"

    private var sharedPreferences: SharedPreferences? = null

    fun initialize(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        }
    }

    fun saveToken(token: String) {
        sharedPreferences?.edit()?.putString(KEY_TOKEN, token)?.apply()
    }
    fun saveUsername(token: String) {
        sharedPreferences?.edit()?.putString(KEY_NAME, token)?.apply()
    }

    fun getToken(): String? {
        return sharedPreferences?.getString(KEY_TOKEN, null)
    }
    fun getUsername(): String? {
        return sharedPreferences?.getString(KEY_NAME, null)
    }

    fun deleteToken() {
        sharedPreferences?.edit()?.remove(KEY_TOKEN)?.apply()
    }

    fun hasToken(): Boolean {
        return getToken() != null
    }

    fun clearSharedPreferences() {
//        val sharedPreferences = // Get reference to your SharedPreferences instance
        val editor = sharedPreferences?.edit()
        editor?.clear()
        editor?.apply()
    }
}
