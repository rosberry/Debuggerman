/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.presentation

/**
 * @author Alexei Korshun on 2019-10-20.
 */
sealed class Debug(val title: String)

class ToggleDebug(title: String, val initialValue: () -> Boolean, val action: (Boolean) -> Unit) : Debug(title)

class ButtonDebug(title: String, val action: () -> Unit) : Debug(title)

class TextDebug(title: String): Debug(title)