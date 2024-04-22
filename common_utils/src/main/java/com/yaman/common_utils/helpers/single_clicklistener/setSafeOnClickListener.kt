package com.yaman.common_utils.helpers.single_clicklistener

import android.view.View

fun View.setSingleOnClickListener(onSingleClick: (View) -> Unit) {
    val safeClickListener = SafeClickListener {
        onSingleClick(it)
    }
    setOnClickListener(safeClickListener)
}