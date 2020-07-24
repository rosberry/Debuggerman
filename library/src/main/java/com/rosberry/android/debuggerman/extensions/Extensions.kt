package com.rosberry.android.debuggerman.extensions

import com.rosberry.android.debuggerman.BuildConfig

/**
 * @author mmikhailov on 24.07.2020.
 */
inline fun ifDebug(action: ()->Unit) {
    if (BuildConfig.DEBUG) action()
}