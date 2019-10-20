/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.DiffUtil
import com.rosberry.abstractrecycler.AbstractItem
import com.rosberry.abstractrecycler.AbstractRecyclerAdapter
import com.rosberry.abstractrecycler.AbstractViewHolder
import com.rosberry.abstractrecycler.DefaultDiffCallback
import com.rosberry.android.debuggerman.R
import com.rosberry.android.debuggerman.presentation.ButtonDebugItem
import com.rosberry.android.debuggerman.presentation.TextDebugItem
import com.rosberry.android.debuggerman.presentation.ToggleDebugItem
import kotlinx.android.synthetic.main.i_toggle_debug.view.*

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

    private class ToggleViewHolder(
            view: View
    ) : AbstractViewHolder<ToggleDebugItem>(view) {

        private lateinit var item: ToggleDebugItem

        init {
            itemView.switchValue.setOnCheckedChangeListener { _, isChecked -> item.data.action.invoke(isChecked) }
        }

        override fun bind(item: ToggleDebugItem) {
            super.bind(item)
            this.item = item
            itemView.findViewById<AppCompatTextView>(R.id.textDebugTitle)
                .text = item.data.title

            itemView.findViewById<SwitchCompat>(R.id.switchValue)
                .isChecked = item.data.getValue.invoke()
        }
    }

    private class TextViewHolder(
            view: View
    ) : AbstractViewHolder<TextDebugItem>(view) {

        private lateinit var item: TextDebugItem

        override fun bind(item: TextDebugItem) {
            super.bind(item)
            this.item = item
            itemView.findViewById<AppCompatTextView>(R.id.textDebugTitle)
                .text = item.data.title
        }
    }

    private class ButtonViewHolder(
            view: View
    ) : AbstractViewHolder<ButtonDebugItem>(view) {

        private lateinit var item: ButtonDebugItem

        init {
            itemView.setOnClickListener { item.data.action.invoke() }
        }

        override fun bind(item: ButtonDebugItem) {
            super.bind(item)
            this.item = item
            itemView.findViewById<AppCompatTextView>(R.id.textDebugTitle)
                .text = item.data.title
        }
    }
}