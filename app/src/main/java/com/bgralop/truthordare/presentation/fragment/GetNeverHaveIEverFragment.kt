package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bgralop.truthordare.databinding.FragmentGetNeverHaveIeverBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.presentation.ViewModel.NeverHaveIEverQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.NeverHaveIEverViewModel
import com.bgralop.truthordare.presentation.adapter.NeverHaveIEverAdapter
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class GetNeverHaveIEverFragment : Fragment() {

    private val binding: FragmentGetNeverHaveIeverBinding by lazy {
        FragmentGetNeverHaveIeverBinding.inflate(layoutInflater)
    }

    private val neverHaveIEverAdapter = NeverHaveIEverAdapter()

    private val neverHaveIEverViewModel: NeverHaveIEverViewModel by activityViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()

        initRecyclerView()

        binding.btnBack.setOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    private fun initRecyclerView() {

        binding.rvNhieQuestions.adapter = neverHaveIEverAdapter
        binding.rvNhieQuestions.layoutManager = LinearLayoutManager(requireContext())

        val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                neverHaveIEverAdapter.neverHaveIEverList[position]
            }
        })

        itemTouchHelper.attachToRecyclerView(binding.rvNhieQuestions)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun initViewModel() {

        neverHaveIEverViewModel.getneverHaveIEverQuestionsLiveData().observe(viewLifecycleOwner) { state ->
            handleNeverHaveIEverListState(state)
        }

        neverHaveIEverViewModel.fetchneverHaveIEverQuestions()
    }

    private fun handleNeverHaveIEverListState(state: NeverHaveIEverQuestionsState) {
        when(state) {
            is ResourceState.Loading -> {
                binding.pbQuestion.visibility = View.VISIBLE
            }
            is ResourceState.Success -> {
                binding.pbQuestion.visibility = View.GONE
                neverHaveIEverAdapter.submitList((state.result))
            }
            is ResourceState.Error -> {
                binding.pbQuestion.visibility = View.GONE
                Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
            }
        }
    }
}