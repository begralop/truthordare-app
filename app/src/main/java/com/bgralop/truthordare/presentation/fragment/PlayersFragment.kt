package com.bgralop.truthordare.presentation.fragment

import android.app.AlertDialog
import android.os.Bundle
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
import com.bgralop.truthordare.presentation.ViewModel.SharedViewModel

class PlayersFragment: Fragment() {

    private val binding: FragmentPlayersBinding by lazy {
        FragmentPlayersBinding.inflate(layoutInflater)
    }
    private var editTextCount = 1
    private lateinit var sharedViewModel: SharedViewModel
    private var prevEditText: EditText? = null
    private var prevEditTextValue2: String? = null

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
            val firstEditText = binding.etPlayersNameIni
            val firstEditTextValue = firstEditText.text.toString()
            sharedViewModel.nameList.add(firstEditTextValue)

            if(isAnyEditTextEmpty()) {
                showAlert("Porfavor, complete todos los nombres antes de jugar")
            } else {
                if(prevEditText != null) {
                    val prevEditTextValue = prevEditText!!.text.toString()
                    if(prevEditTextValue2 != prevEditTextValue) {
                        sharedViewModel.nameList.add(prevEditTextValue)
                        prevEditTextValue2 = prevEditTextValue
                    }
                }
                findNavController().navigate(
                    PlayersFragmentDirections.actionPlayersFragmentToSelectTruthOrDareFragment()
                )
            }
        }
        binding.btnPlayersAddName.setOnClickListener {
            if(isAnyEditTextEmpty()) {
                showAlert("Porfavor, complete todos los nombres antes de jugar")
            }else{
                val firstEditText = binding.etPlayersNameIni
                val firstEditTextValue = firstEditText.text.toString()
                addEditText(firstEditTextValue)
            }
        }
    }


    private fun addEditText(firstEditTextValue: String) {

        if(prevEditText != null) {
            val prevEditTextValue = prevEditText!!.text.toString()
            sharedViewModel.nameList.add(prevEditTextValue)
        }

        if(firstEditTextValue.isNotEmpty()) {
            val editText = EditText(context)
            val params = LinearLayout.LayoutParams(
                resources.getDimensionPixelSize(R.dimen.editTextWidth),
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

            if (editTextCount >= 1) {
                container.addView(deleteButton)
            }

            prevEditText = editText

            binding.container.addView(container)
            editTextCount++
        }
    }

    private fun deleteEditText(editText: EditText, deleteButton: Button) {
        val parentContainer = editText.parent as? View
        parentContainer?.let {
            val index = binding.container.indexOfChild(parentContainer)
            if(index >= 0) {
                binding.container.removeView(parentContainer)
                editTextCount--
                // sharedViewModel.nameList.removeAt(index)
            }
        }
    }

    private fun isAnyEditTextEmpty(): Boolean {
        val firstEditText = binding.etPlayersNameIni
        if (firstEditText.text.toString().isEmpty()) {
            return true
        }

        for (i in 0 until binding.container.childCount) {
            val childView = binding.container.getChildAt(i)
            if (childView is LinearLayout) {
                val editText = childView.getChildAt(0) as EditText
                if (editText.text.toString().isEmpty()) {
                    return true
                }
            }
        }
        return false
    }
    private fun showAlert(message: String) {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Alerta")
        builder.setMessage(message)
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val alertDialog = builder.create()
        alertDialog.show()
    }
}