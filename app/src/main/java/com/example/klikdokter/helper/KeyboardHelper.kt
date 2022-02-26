package com.example.klikdokter.helper

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.base.extension.contentView

object KeyboardHelper {

    fun hideKeyboard(activity: Activity) {
        try {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity.contentView().windowToken, 0)
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }
    }

    fun showKeyboard(editText: View) {
        try {
            val imm = editText.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(editText, 0)
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }
    }

    fun showKeyboard(context: Context?) {
        val inputMethodManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}
