/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.presentation

import com.rosberry.abstractrecycler.AbstractItem
import com.rosberry.android.debuggerman.DebugAgent

/**
 * @author Alexei Korshun on 2019-10-20.
 */
class DebugPresenter {

    private var view: DebugView? = null

    fun onAttach(view: DebugView) {
        this.view = view
        view.setDebugItems(DebugAgent.supportedItems.map { data -> convertDataToItem(data) })
    }

    fun onDetach() {
        this.view = null
    }

    fun clickDismiss() {
        view?.dismiss()
    }

    private fun convertDataToItem(data: Debug): AbstractItem {
        return when (data) {
            is ButtonDebug -> ButtonDebugItem(data)
            is ToggleDebug -> ToggleDebugItem(data)
            is TextDebug -> TextDebugItem(data)
        }
    }
}