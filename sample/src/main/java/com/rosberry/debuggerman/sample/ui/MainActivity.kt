package com.rosberry.debuggerman.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rosberry.android.debuggerman.DebugAgent
import com.rosberry.android.debuggerman.presentation.ButtonDebug
import com.rosberry.android.debuggerman.ui.DebugDialogFragment
import com.rosberry.debuggerman.sample.BuildConfig
import com.rosberry.debuggerman.sample.R
import com.rosberry.debuggerman.sample.data.PrefManager

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.DEBUG) setupDebugAgent()

        setContentView(R.layout.activity_main)

        prefs = PrefManager(this)

        val fragment = if (prefs.showOnboarding) {
            OnboardingFragment()
        } else HomeFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        if (BuildConfig.DEBUG) DebugDialogFragment.onNewIntent(intent, supportFragmentManager)
    }

    fun closeOnBoarding() {
        prefs.showOnboarding = false

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }

    private fun setupDebugAgent() {
        DebugAgent.start(this)
        DebugAgent.place(ButtonDebug("Reset onboarding", toastMessage = "Preferences cleared") { prefs.clearAll() })
    }
}
