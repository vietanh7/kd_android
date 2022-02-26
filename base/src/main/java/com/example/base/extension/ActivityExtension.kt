package com.example.base.extension

import android.app.Activity
import android.view.View
import com.zhuinden.simplestack.Backstack
import com.zhuinden.simplestack.navigator.Navigator

fun Activity.contentView(): View = this.findViewById(android.R.id.content)

val Activity.backstack: Backstack get() = Navigator.getBackstack(this)