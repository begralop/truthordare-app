package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentQuestionBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.presentation.ViewModel.SharedViewModel
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
class QuestionFragment : Fragment() {

    private val binding: FragmentQuestionBinding by lazy {
        FragmentQuestionBinding.inflate(layoutInflater)
    }

    private lateinit var sharedViewModel: SharedViewModel

    private val truthOrDareViewModel: TruthOrDareViewModel by activityViewModel()
    private val args: QuestionFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        if(args.truthOrDare == 0){
            truthOrDareViewModel.fetchTruthQuestions()
        }
        if(args.truthOrDare == 1){
            truthOrDareViewModel.fetchDareQuestions()
        }
        binding.btnFragmentQuestionNextPlayer.setOnClickListener{
            findNavController().navigate(
                QuestionFragmentDirections.actionQuestionFragmentToTruthOrDareFragment()
            )
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner){
            findNavController().navigate(R.id.action_QuestionFragment_to_WelcomeFragment)
            sharedViewModel.clearNameList()
        }
    }

    private fun initViewModel() {
        truthOrDareViewModel.getTruthQuestionsLiveData().observe(viewLifecycleOwner) { state ->
            getTruthOrDareHandle(state)
        }
    }

    private fun getTruthOrDareHandle(state: TruthOrDareQuestionsState) {
        when (state) {
            is ResourceState.Loading -> {
                binding.pbQuestion.visibility = View.VISIBLE
            }

            is ResourceState.Success -> {
                binding.pbQuestion.visibility = View.GONE
                initUI(state.result)
            }

            is ResourceState.Error -> {
                binding.pbQuestion.visibility = View.GONE
            }
        }
    }

    private fun initUI(question: TruthOrDareQuestions) {
        val idiomaDeseado = "es"

        if (question.translations.containsKey(idiomaDeseado)) {
            val traduccion = question.translations[idiomaDeseado]
            binding.tvActivityMainWelcome.text = traduccion
        } else {
            binding.tvActivityMainWelcome.text = question.question
        }
    }
}