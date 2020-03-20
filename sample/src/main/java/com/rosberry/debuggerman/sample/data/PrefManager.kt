package com.rosberry.debuggerman.sample.data

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

/**
 * @author mmikhailov on 27.02.2020.
 */
class PrefManager(context: Context) {
    internal val preferences: SharedPreferences by lazy { PreferenceManager(context).sharedPreferences }

    var showOnboarding by PrefDelegate(true)

    fun clearAll() {
        preferences.edit().clear().apply()
    }
}