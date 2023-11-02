package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentWelcomeBinding

class WelcomeFragment: Fragment() {

    private val binding: FragmentWelcomeBinding by lazy {
        FragmentWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWelcomePlay.setOnClickListener {
            findNavController().navigate(
                R.id.action_welcomeFragment_to_playersFragment
            )
        }

        binding.btnWelcomePlayNhie.setOnClickListener {
            findNavController().navigate(
                R.id.action_welcomeFragment_to_GetNeverHaveIEverFragment
            )
        }
    }
}