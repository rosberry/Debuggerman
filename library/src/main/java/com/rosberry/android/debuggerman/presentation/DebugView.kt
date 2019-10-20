/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.presentation

import com.rosberry.abstractrecycler.AbstractItem

/**
 * @author Alexei Korshun on 2019-10-20.
 */
interface DebugView {

    fun setDebugItems(items: List<AbstractItem>)

    fun dismiss()
}