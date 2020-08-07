/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.view.View
import android.widget.Toast
import com.rosberry.abstractrecycler.AbstractViewHolder
import com.rosberry.android.debuggerman.presentation.ButtonDebugItem
import kotlinx.android.synthetic.main.i_button_debug.view.*

/**
 * @author Alexei Korshun on 2019-10-25.
 */
internal class ButtonViewHolder(view: View) : AbstractViewHolder<ButtonDebugItem>(view) {

    private lateinit var item: ButtonDebugItem

    init {
        itemView.setOnClickListener {
            with(item.data) {
                action.invoke()
                toastMessage?.let { message -> Toast.makeText(itemView.context, message, Toast.LENGTH_SHORT).show() }
            }
        }
    }

    override fun bind(item: ButtonDebugItem) {
        super.bind(item)
        this.item = item
        itemView.textDebugTitle
            .text = item.data.title
    }
}