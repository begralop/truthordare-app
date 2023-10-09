package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentPlayersBinding
import com.bgralop.truthordare.databinding.FragmentWelcomeBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class PlayersFragment: Fragment() {

    private val binding: FragmentPlayersBinding by lazy {
        FragmentPlayersBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFragmentPlayersPlay.setOnClickListener {
            findNavController().navigate(
                R.id.action_playersFragment_to_selectTruthOrDareFragment
            )
        }/*
        binding.buttonDelete.setOnClickListener {
            // Elimina el campo de texto actual
            binding.editTextContainer.removeView(binding.editTextName)
            binding.editTextContainer.removeView(binding.buttonDelete)
        }

        binding.buttonAdd.setOnClickListener {
            // Crea un nuevo campo de texto y un botón de eliminación
            val newEditText = EditText(this.context)
            newEditText.layoutParams = binding.editTextName.layoutParams
            newEditText.hint = "Nombre"
            val newDeleteButton = Button(this.context)
            newDeleteButton.text = "Eliminar"

            // Agrega el nuevo campo de texto y el nuevo botón al contenedor
            binding.editTextContainer.addView(newEditText)
            binding.editTextContainer.addView(newDeleteButton)

            newDeleteButton.setOnClickListener {
                // Elimina el campo de texto y el botón de eliminación
                binding.editTextContainer.removeView(newEditText)
                binding.editTextContainer.removeView(newDeleteButton)
            }

        }*/
    }
}