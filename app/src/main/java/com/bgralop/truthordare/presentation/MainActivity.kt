package com.bgralop.truthordare.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bgralop.truthordare.R
import com.bgralop.truthordare.databinding.ActivityMainBinding
import com.bgralop.truthordare.presentation.ViewModel.TruthOrDareViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}