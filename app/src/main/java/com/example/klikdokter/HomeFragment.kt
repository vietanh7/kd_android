package com.example.klikdokter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.base.view.BaseVbFragment
import com.example.klikdokter.databinding.FragmentHomeBinding
import com.example.klikdokter.navigation.LoginKey
import com.example.klikdokter.navigation.RegisterKey
import com.zhuinden.simplestack.StateChange

class HomeFragment: BaseVbFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding = FragmentHomeBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener {
            (activity as? MainActivity)?.replaceContent(RegisterKey(), StateChange.FORWARD)
        }

        binding.btnLogin.setOnClickListener {
            (activity as? MainActivity)?.replaceContent(LoginKey(), StateChange.FORWARD)
        }
    }
}