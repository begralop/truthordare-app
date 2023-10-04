package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentSelectTruthOrDareBinding
import com.bgralop.truthordare.databinding.FragmentWelcomeBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SelectTruthOrDareFragment : Fragment() {

    private val binding: FragmentSelectTruthOrDareBinding by lazy {
        FragmentSelectTruthOrDareBinding.inflate(layoutInflater)
    }

    private var truthOrDare: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFragmentSelectTruth.setOnClickListener {
            truthOrDare = 0
            findNavController().navigate(
                SelectTruthOrDareFragmentDirections.actionTruthOrDareFragmentToQuestionFragment(truthOrDare)
            )
        }

        binding.btnFragmentSelectDare.setOnClickListener {
            truthOrDare = 1
            findNavController().navigate(
                SelectTruthOrDareFragmentDirections.actionTruthOrDareFragmentToQuestionFragment(truthOrDare)
            )
        }
    }
}