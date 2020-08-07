package com.rosberry.debuggerman.sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rosberry.android.debuggerman.DebugAgent
import com.rosberry.android.debuggerman.extensions.ifDebug
import com.rosberry.android.debuggerman.presentation.ButtonDebug
import com.rosberry.debuggerman.sample.R
import com.rosberry.debuggerman.sample.data.PrefManager

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ifDebug { setupDebugAgent() }

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

        ifDebug {  DebugAgent.onNewIntent(intent, supportFragmentManager) }
    }

    override fun onDestroy() {
        super.onDestroy()

        ifDebug {
            // todo handle system of actions' tag
            DebugAgent.remove("MainActivity_reset_onboarding")
            DebugAgent.stop()
        }
    }

    fun closeOnBoarding() {
        prefs.showOnboarding = false

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }

    private fun setupDebugAgent() {
        DebugAgent.start(this)
        DebugAgent.place(ButtonDebug(
                tag = "MainActivity_reset_onboarding",
                title = "Reset onboarding",
                toastMessage = "Preferences cleared"
        ) { prefs.clearAll() })
    }
}
