package com.example.base.view.widget.dialog

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.view.Window
import com.example.base.R

class ProgressViewDialog {

    private var dialog: Dialog? = null

    fun showAlert(context: Context?) {
        if (context == null) return
        if (dialog == null){
            dialog = Dialog(context, R.style.Progress_Dialog)
            dialog?.apply {
                requestWindowFeature(Window.FEATURE_NO_TITLE)
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                window?.apply {
                    setCancelable(false)
                    setContentView(R.layout.progress_view)
                    show()
                }
            }
        } else if (dialog?.isShowing == false){
            dialog?.show()
        }
    }

    fun dismiss() {
        dialog?.dismiss()
    }

}