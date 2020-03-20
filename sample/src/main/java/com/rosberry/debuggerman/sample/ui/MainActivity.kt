package com.rosberry.debuggerman.sample.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rosberry.debuggerman.sample.R
import com.rosberry.debuggerman.sample.data.PrefManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        prefs = PrefManager(this)

        val fragment = if (prefs.showOnboarding) {
            OnboardingFragment()
        } else HomeFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, fragment)
            .commit()
    }

    fun closeOnBoarding() {
        prefs.showOnboarding = false

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
    }
}
