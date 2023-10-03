package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentWelcomeBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class WelcomeFragment: Fragment() {

    private val binding: FragmentWelcomeBinding by lazy {
        FragmentWelcomeBinding.inflate(layoutInflater)
    }

    private val charactersViewModel: TruthOrDareViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        charactersViewModel.fetchTruthQuestions()

        binding.btnWelcomePlay.setOnClickListener {
            findNavController().navigate(
                R.id.action_welcomeFragment_to_playersFragment
            )
        }
    }

    private fun initViewModel() {
        charactersViewModel.getTruthQuestionsLiveData().observe(viewLifecycleOwner) { state ->
            handleCharacterListState(state)
        }
    }

    private fun handleCharacterListState(state: TruthOrDareQuestionsState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbCharacterList.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbCharacterList.visibility = View.GONE
                initUI(state.result)
            }

            is ResourceState.Error -> {
                binding.pbCharacterList.visibility = View.GONE
            }
        }
    }

    private fun initUI(question: TruthOrDareQuestions) {
        val idiomaDeseado = "es"  // Cambia esto al idioma que desees obtener

        if (question.translations.containsKey(idiomaDeseado)) {
            val traduccion = question.translations[idiomaDeseado]
            binding.tvActivityMainWelcome.text = traduccion
        } else {
            binding.tvActivityMainWelcome.text = question.question
        }
    }
}