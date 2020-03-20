/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import com.rosberry.abstractrecycler.AbstractViewHolder
import com.rosberry.android.debuggerman.R
import com.rosberry.android.debuggerman.presentation.ToggleDebugItem
import kotlinx.android.synthetic.main.i_toggle_debug.view.*

/**
 * @author Alexei Korshun on 2019-10-25.
 */
internal class ToggleViewHolder(view: View) : AbstractViewHolder<ToggleDebugItem>(view) {

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
            .isChecked = item.data.initialValue.invoke()
    }
}