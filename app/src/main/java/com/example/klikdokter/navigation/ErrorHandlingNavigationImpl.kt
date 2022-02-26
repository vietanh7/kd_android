package com.example.klikdokter.navigation

import android.content.Context
import com.example.base.error.ErrorHandlingNavigation
import com.example.klikdokter.BaseApplication
import com.example.klikdokter.MainActivity
import com.zhuinden.simplestack.StateChange
import javax.inject.Inject

class ErrorHandlingNavigationImpl @Inject constructor(val context: Context) :
    ErrorHandlingNavigation {

    override fun onUserTokenInvalid() {
        val mainActivity = (context as? BaseApplication)?.topActivity as? MainActivity
        mainActivity?.replaceContent(HomeKey(), StateChange.BACKWARD)
    }
}
