package com.example.klikdokter

import android.os.Bundle
import android.view.LayoutInflater
import com.example.base.extension.backstack
import com.example.base.extension.replaceHistory
import com.example.base.view.BaseVbActivity
import com.example.klikdokter.databinding.ActivityMainBinding
import com.example.klikdokter.domain.section.SessionPreference
import com.example.klikdokter.helper.KeyboardHelper
import com.example.klikdokter.navigation.*
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange
import com.zhuinden.simplestack.StateChanger
import com.zhuinden.simplestack.navigator.Navigator
import javax.inject.Inject

class MainActivity : BaseVbActivity<ActivityMainBinding>(),
    StateChanger {

    @Inject
    lateinit var sessionPreference: SessionPreference

    private lateinit var fragmentStateChanger: FragmentStateChanger

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentStateChanger = FragmentStateChanger(supportFragmentManager, R.id.conMain)
        val fragmentKey =
            if (sessionPreference.getToken().isEmpty())
                HomeKey()
            else
                ProductListKey()

        Navigator.configure()
            .setStateChanger(this)
            .install(this, binding.conMain, History.single(fragmentKey))
    }

    override fun handleStateChange(
        stateChange: StateChange,
        completionCallback: StateChanger.Callback
    ) {
        if (stateChange.isTopNewKeyEqualToPrevious) {
            completionCallback.stateChangeComplete()
            return
        }
        fragmentStateChanger.handleStateChange(stateChange)
        completionCallback.stateChangeComplete()
    }

    fun replaceContent(fragmentKey: BaseKey?, direction: Int) {
        KeyboardHelper.hideKeyboard(this)
        fragmentKey?.run {
            backstack.replaceHistory(direction, this)
        }
    }
}