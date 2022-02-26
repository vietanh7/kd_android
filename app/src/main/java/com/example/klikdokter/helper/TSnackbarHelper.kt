package com.example.klikdokter.helper

import android.graphics.Color
import android.view.View
import com.androidadvance.topsnackbar.TSnackbar

object TSnackbarHelper {

    fun error(view: View, message: String) {
        val snackBar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.RED)
        snackBar.show()
    }

    fun success(view: View, message: String) {
        val snackBar = TSnackbar.make(view, message, TSnackbar.LENGTH_LONG)
        val snackBarView = snackBar.view
        snackBarView.setBackgroundColor(Color.GREEN)
        snackBar.show()
    }
}