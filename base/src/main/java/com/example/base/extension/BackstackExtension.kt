package com.example.base.extension

import com.zhuinden.simplestack.Backstack

fun Backstack.replaceHistory(direction: Int, vararg keys: Any) {
    this.setHistory(keys.toList(), direction)
}
