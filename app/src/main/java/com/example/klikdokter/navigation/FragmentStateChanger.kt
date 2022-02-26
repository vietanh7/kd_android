package com.example.klikdokter.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.klikdokter.R
import com.zhuinden.simplestack.History
import com.zhuinden.simplestack.StateChange

class FragmentStateChanger(
        private val fragmentManager: FragmentManager,
        private val containerId: Int
) {
    fun handleStateChange(stateChange: StateChange) {
        val fragmentTransaction = fragmentManager.beginTransaction().apply {
            when (stateChange.direction) {
                StateChange.FORWARD -> {
                    setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right, R.anim.exit_to_left)
                }
                StateChange.BACKWARD -> {
                    setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_left, R.anim.exit_to_right)
                }
            }

            val newState = stateChange.getNewKeys<BaseKey>()
            removeOrDetachFragment(newState, stateChange, this)
            handleTopNewKey(newState, stateChange, this)

        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    private fun handleTopNewKey(newState: History<BaseKey>, stateChange: StateChange, fragmentTransaction: FragmentTransaction) {
        for (newKey in newState) {
            var fragment: Fragment? = fragmentManager.findFragmentByTag(newKey.fragmentTag)
            if (newKey == stateChange.topNewKey<Any>()) {
                if (fragment != null) {
                    if (fragment.isRemoving) { // Fragments are quirky, they die asynchronously. Ignore if they're still there.
                        fragment = newKey.newFragment()
                        fragmentTransaction.replace(containerId, fragment, newKey.fragmentTag)
                    } else if (fragment.isDetached) {
                        fragmentTransaction.attach(fragment)
                    }
                } else {
                    fragment = newKey.newFragment()
                    fragmentTransaction.add(containerId, fragment, newKey.fragmentTag)
                }
            } else {
                detachFragment(fragment, fragmentTransaction)
            }
        }
    }

    private fun detachFragment(fragment: Fragment?, fragmentTransaction: FragmentTransaction) {
        if (fragment != null && !fragment.isDetached) {
            fragmentTransaction.detach(fragment)
        }
    }

    private fun removeOrDetachFragment(newState: History<BaseKey>, stateChange: StateChange, fragmentTransaction: FragmentTransaction) {
        val previousState = stateChange.getPreviousKeys<BaseKey>()
        for (oldKey in previousState) {
            val fragment = fragmentManager.findFragmentByTag(oldKey.fragmentTag)
            if (fragment != null) {
                if (!newState.contains(oldKey)) {
                    fragmentTransaction.remove(fragment)
                } else if (!fragment.isDetached) {
                    fragmentTransaction.detach(fragment)
                }
            }
        }
    }
}