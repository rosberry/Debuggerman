package com.rosberry.debuggerman.sample.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rosberry.debuggerman.sample.R
import kotlinx.android.synthetic.main.f_onbarding.*

class OnboardingFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.f_onbarding, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_complete.setOnClickListener {
            (requireActivity() as MainActivity).closeOnBoarding()
        }
    }
}
