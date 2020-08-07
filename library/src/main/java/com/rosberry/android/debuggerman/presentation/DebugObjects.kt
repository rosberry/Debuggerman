/*
 *
 *  * Copyright (c) 2018 Rosberry. All rights reserved.
 *
 */

package com.rosberry.android.debuggerman.presentation

/**
 * @author Alexei Korshun on 2019-10-20.
 */
sealed class Debug(val tag: String, val title: String)

class ToggleDebug(tag: String, title: String, val initialValue: () -> Boolean, val action: (Boolean) -> Unit) : Debug(tag, title)

class ButtonDebug(tag: String, title: String, val toastMessage: String? = null, val action: () -> Unit) : Debug(tag, title)

class TextDebug(tag: String, title: String): Debug(tag, title)