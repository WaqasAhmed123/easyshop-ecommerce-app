package com.example.easyshop.util

import android.content.Context
import android.widget.Toast
import java.util.regex.Matcher
import java.util.regex.Pattern


object EmailAndPasswordValidator {
    fun isValidPassword(password: String?, context: Context): Boolean {
        return password?.let {
            val passwordPattern =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
            val passwordMatcher = Regex(passwordPattern)

            if (!passwordMatcher.matches(password)) {
                Toast.makeText(
                    context,
                    "Password must contain an uppercase, a lowercase, a special character, and have a minimum length of 6",
                    Toast.LENGTH_LONG
                ).show()
                return false
            }

            true
        } ?: false
    }

//    fun isValidPassword(password: String?,context:Context): Boolean {
//        password?.let {
//            val passwordPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$"
//            val passwordMatcher = Regex(passwordPattern)
//
//            return passwordMatcher.find(password) != null
//        } ?: return false
//}
//        if(!matcher.matches()){
//            Toast.makeText(
//                context, "Password must contain an uppercase, a lowercase, a special character and min of length 6 ", Toast.LENGTH_SHORT
//            ).show()
//        }
}