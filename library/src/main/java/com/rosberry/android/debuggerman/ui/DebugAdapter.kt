/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.rosberry.abstractrecycler.AbstractItem
import com.rosberry.abstractrecycler.AbstractRecyclerAdapter
import com.rosberry.abstractrecycler.AbstractViewHolder
import com.rosberry.abstractrecycler.DefaultDiffCallback
import com.rosberry.android.debuggerman.R

/**
 * @author Alexei Korshun on 2019-10-20.
 */
class DebugAdapter(
        items: List<AbstractItem>
) : AbstractRecyclerAdapter(items) {

    override fun createHolder(view: View, viewType: Int): AbstractViewHolder<out AbstractItem> {
        return when (viewType) {
            R.layout.i_toggle_debug -> ToggleViewHolder(view)
            R.layout.i_button_debug -> ButtonViewHolder(view)
            R.layout.i_text_debug -> TextViewHolder(view)
            else -> throw IllegalStateException("Unknown view type")
        }
    }

    fun showItems(items: List<AbstractItem>) {
        val result = DiffUtil.calculateDiff(DefaultDiffCallback(this.items, items))
        this.items = items
        result.dispatchUpdatesTo(this)
    }
}