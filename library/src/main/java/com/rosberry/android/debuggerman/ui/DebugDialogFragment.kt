/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.rosberry.abstractrecycler.AbstractItem
import com.rosberry.android.debuggerman.DebugAgent
import com.rosberry.android.debuggerman.R
import com.rosberry.android.debuggerman.presentation.DebugPresenter
import com.rosberry.android.debuggerman.presentation.DebugView
import kotlinx.android.synthetic.main.df_debug.*
import kotlinx.android.synthetic.main.df_debug.view.*

/**
 * @author Alexei Korshun on 2019-10-20.
 */
class DebugDialogFragment : DialogFragment(), DebugView {

    companion object {
        private const val TAG = "DebugDialogFragment"

        fun onNewIntent(i: Intent?, supportFragmentManager: FragmentManager) {
            if (i != null && i.action == DebugAgent.ACTION_OPEN) {
                DebugDialogFragment().show(supportFragmentManager, TAG)
            }
        }
    }

    private val presenter: DebugPresenter = DebugPresenter()

    private val adapter: DebugAdapter by lazy { DebugAdapter(emptyList()) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        setStyle(STYLE_NO_TITLE, R.style.DebugDialogStyle)
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, parent: ViewGroup?, state: Bundle?): View? {
        return inflater.inflate(R.layout.df_debug, parent, false)
            .apply {
                this.buttonApply.setOnClickListener { presenter.clickApply() }
                this.buttonCancel.setOnClickListener { presenter.clickDismiss() }
            }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerDebug.setHasFixedSize(true)
        recyclerDebug.addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        recyclerDebug.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        presenter.onAttach(this)
    }

    override fun onPause() {
        presenter.onDetach()
        super.onPause()
    }

    override fun setDebugItems(items: List<AbstractItem>) {
        adapter.showItems(items)
    }

}