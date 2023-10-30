package com.bgralop.truthordare.presentation.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.FragmentPlayersBinding
import com.bgralop.truthordare.presentation.ViewModel.SharedViewModel
class PlayersFragment : Fragment() {
    private val binding: FragmentPlayersBinding by lazy {
        FragmentPlayersBinding.inflate(layoutInflater)
    }
    private lateinit var sharedViewModel: SharedViewModel

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
            if (isAnyEditTextEmpty()) {
                showAlert("Por favor, complete todos los nombres antes de jugar.")
            } else {
                findNavController().navigate(
                    PlayersFragmentDirections.actionPlayersFragmentToSelectTruthOrDareFragment()
                )
            }
        }

        binding.btnPlayersAddName.setOnClickListener {
            val firstEditText = binding.etPlayersNameIni
            val firstEditTextValue = firstEditText.text.toString()

            if (firstEditTextValue.isNotEmpty()) {
                addTextView(firstEditTextValue)
                sharedViewModel.nameList.add(firstEditTextValue)
                firstEditText.text.clear()
            } else {
                showAlert("No has introducido ningún nombre")
            }
        }
    }

    private fun addTextView(text: String) {
        val container = RelativeLayout(context)

        val textView = TextView(context)
        textView.text = text
        textView.textSize = 24f

        val customFont =
            context?.let { ResourcesCompat.getFont(it, R.font.custom_font) }
        textView.typeface = customFont

        val textViewParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_START)
        textViewParams.addRule(RelativeLayout.CENTER_VERTICAL)
        textView.layoutParams = textViewParams

        val deleteButton = Button(context)
        deleteButton.layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        deleteButton.setBackgroundResource(R.drawable.transparent_button_background)
        deleteButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_delete, 0)
        deleteButton.setOnClickListener {
            deleteTextView(textView)
        }

        val deleteButtonParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        deleteButtonParams.addRule(RelativeLayout.ALIGN_PARENT_END)
        deleteButtonParams.addRule(RelativeLayout.CENTER_VERTICAL)
        deleteButton.layoutParams = deleteButtonParams

        container.addView(textView)
        container.addView(deleteButton)

        binding.container.addView(container)
    }

    private fun deleteTextView(textView: TextView) {
        val parentContainer = textView.parent as? View
        parentContainer?.let {
            binding.container.removeView(parentContainer)
            val textToDelete = textView.text.toString()
            sharedViewModel.nameList.remove(textToDelete)
        }
    }

    private fun isAnyEditTextEmpty(): Boolean {
        val firstEditText = binding.etPlayersNameIni
        if (firstEditText.text.toString().isNotEmpty()) {
            return true
        }

        for (i in 0 until binding.container.childCount) {
            val childView = binding.container.getChildAt(i)
            if (childView is LinearLayout) {
                val textView = childView.getChildAt(0) as TextView
                if (textView.text.toString().isEmpty()) {
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
