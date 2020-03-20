package com.rosberry.debuggerman.sample.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rosberry.android.debuggerman.DebugAgent
import com.rosberry.android.debuggerman.presentation.ToggleDebug
import com.rosberry.debuggerman.sample.BuildConfig
import com.rosberry.debuggerman.sample.R
import kotlinx.android.synthetic.main.f_home.*

/**
 * @author mmikhailov on 20.03.2020.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (BuildConfig.DEBUG) setupDebugAgent()

        return inflater.inflate(R.layout.f_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rb_red.isChecked = true
        rg.setOnCheckedChangeListener { _, checkedId ->
            val color: Int = when (checkedId) {
                R.id.rb_green -> Color.GREEN
                R.id.rb_red -> Color.RED
                else -> Color.WHITE
            }

            square.setBackgroundColor(color)
        }
    }

    private fun setupDebugAgent() {
        DebugAgent.place(ToggleDebug("red square ?", initialValue = { rb_red.isChecked }, action = {
            rb_red.isChecked = it
            rb_green.isChecked = !it
        }))
    }
}
