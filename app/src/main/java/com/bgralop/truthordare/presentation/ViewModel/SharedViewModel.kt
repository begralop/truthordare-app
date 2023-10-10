package com.bgralop.truthordare.presentation.ViewModel

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val nameList = mutableListOf<String>()

    fun clearNameList() {
        nameList.clear()
    }
}