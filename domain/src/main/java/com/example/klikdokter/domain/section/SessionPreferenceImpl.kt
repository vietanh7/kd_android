package com.example.klikdokter.domain.section

import android.content.Context
import com.google.gson.Gson
import javax.inject.Inject

class SessionPreferenceImpl @Inject constructor(val context: Context, val gson: Gson) :
    SessionPreference {

    private val shared = SharedHelper(context)

    override fun setToken(token: String) = shared.put(SharedHelper.token, token)

    override fun getToken(): String = shared.valueFrom(SharedHelper.token, "").orEmpty()
}
