package com.arniodev.translator.utils

import android.content.Context

fun Int.dp2px(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density + 0.5f).toInt()
}