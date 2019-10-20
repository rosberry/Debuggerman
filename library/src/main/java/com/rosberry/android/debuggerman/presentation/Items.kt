/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.presentation

import com.rosberry.abstractrecycler.AbstractItem
import com.rosberry.android.debuggerman.R

/**
 * @author Alexei Korshun on 2019-10-20.
 */
class TextDebugItem(val data: Debug) : AbstractItem(R.layout.i_text_debug)

class ToggleDebugItem(val data: ToggleDebug) : AbstractItem(R.layout.i_toggle_debug)

class ButtonDebugItem(val data: ButtonDebug) : AbstractItem(R.layout.i_button_debug)