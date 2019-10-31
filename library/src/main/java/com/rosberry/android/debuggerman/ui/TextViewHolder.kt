/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.ui

import android.view.View
import com.rosberry.abstractrecycler.AbstractViewHolder
import com.rosberry.android.debuggerman.presentation.TextDebugItem
import kotlinx.android.synthetic.main.i_text_debug.view.*

/**
 * @author Alexei Korshun on 2019-10-25.
 */
class TextViewHolder(view: View) : AbstractViewHolder<TextDebugItem>(view) {

    private lateinit var item: TextDebugItem

    override fun bind(item: TextDebugItem) {
        super.bind(item)
        this.item = item
        itemView.textDebugTitle
            .text = item.data.title
    }
}