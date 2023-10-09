package com.bgralop.truthordare.presentation.fragment

import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentPlayersBinding
import com.bgralop.truthordare.databinding.FragmentWelcomeBinding
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.presentation.ViewModel.SharedViewModel
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareQuestionsState
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class PlayersFragment: Fragment() {

    private val binding: FragmentPlayersBinding by lazy {
        FragmentPlayersBinding.inflate(layoutInflater)
    }
    private var editTextCount = 1 // Contador para llevar un seguimiento de los EditText
    private lateinit var sharedViewModel: SharedViewModel
    private val handler = Handler()
    private val delayMillis = 1000
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        sharedViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFragmentPlayersPlay.setOnClickListener {
            findNavController().navigate(
                PlayersFragmentDirections.actionPlayersFragmentToSelectTruthOrDareFragment()
            )
        }

        binding.addButton.setOnClickListener {
            addEditText()
        }
    }
    private fun addEditText() {

        val firstEditText = binding.editText1
        val firstEditTextValue = firstEditText.text.toString()
        sharedViewModel.nameList.add(firstEditTextValue)

        val editText = EditText(context)
        val params = LinearLayout.LayoutParams(
            resources.getDimensionPixelSize(R.dimen.editTextWidth), // Ancho de 30dp
            LinearLayout.LayoutParams.WRAP_CONTENT,
        )

        editText.layoutParams = params

        val deleteButton = Button(context)
        deleteButton.setBackgroundResource(R.drawable.custom_button_background)
        deleteButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_delete, 0, 0, 0)
        deleteButton.setOnClickListener {
            deleteEditText(editText, deleteButton)
        }

        val container = LinearLayout(context)
        container.orientation = LinearLayout.HORIZONTAL
        container.gravity = Gravity.CENTER_HORIZONTAL
        container.addView(editText)

        // Agregar el botón de eliminar solo a los EditText adicionales
        if (editTextCount >= 1) {
            container.addView(deleteButton)
        }

        binding.container.addView(container)
        editTextCount++

        // Agregar un TextWatcher al EditText para detectar cambios y agregar a la lista
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val nombre = s.toString()
                // Si ya hay un temporizador en marcha, cancela el temporizador anterior
                handler.removeCallbacksAndMessages(null)

                // Inicia un nuevo temporizador para guardar los nombres después de 'delayMillis'
                handler.postDelayed({
                    sharedViewModel.nameList.add(nombre)
                    if (nombre.isNotEmpty()) {
                        Log.d("Nombre", sharedViewModel.nameList.toString())
                    }
                }, delayMillis.toLong())
            }
        })
    }

    private fun deleteEditText(editText: EditText, deleteButton: Button) {
        val parentContainer = editText.parent as? View
        parentContainer?.let {
            binding.container.removeView(parentContainer)
            editTextCount--
        }
    }
}