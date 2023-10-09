package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentSelectTruthOrDareBinding
import com.bgralop.truthordare.databinding.FragmentWelcomeBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.presentation.ViewModel.SharedViewModel
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class SelectTruthOrDareFragment : Fragment() {

    private val binding: FragmentSelectTruthOrDareBinding by lazy {
        FragmentSelectTruthOrDareBinding.inflate(layoutInflater)
    }

    private lateinit var sharedViewModel: SharedViewModel

    private var truthOrDare: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Resto del código para inflar la vista y mostrar los nombres

        val nameList = sharedViewModel.nameList

        val random = java.util.Random()
        if (nameList.isNotEmpty()) {
            val randomIndex = random.nextInt(nameList.size)
            val randomName = nameList[randomIndex]
            binding.tvFragmentSelectName.text = randomName
        }

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