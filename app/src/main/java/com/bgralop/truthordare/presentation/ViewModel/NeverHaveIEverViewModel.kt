package com.bgralop.truthordare.presentation.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgralop.truthordare.domain.usecase.GetNeverHaveIEverQuestionUseCase
import com.bgralop.truthordare.model.NeverHaveIEverQuestion
import com.bgralop.truthordare.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias NeverHaveIEverQuestionsState = ResourceState<List<NeverHaveIEverQuestion>>

class NeverHaveIEverViewModel(
    private val neverHaveIEverQuestionUseCase: GetNeverHaveIEverQuestionUseCase
) : ViewModel() {

    private val neverHaveIEverQuestionsMutableLiveData = MutableLiveData<NeverHaveIEverQuestionsState>()

    fun getneverHaveIEverQuestionsLiveData(): LiveData<NeverHaveIEverQuestionsState> {
        return neverHaveIEverQuestionsMutableLiveData
    }
    @SuppressLint("SuspiciousIndentation")
    fun fetchneverHaveIEverQuestions() {

        neverHaveIEverQuestionsMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = neverHaveIEverQuestionUseCase.execute(5)
                withContext(Dispatchers.Main) {
                    neverHaveIEverQuestionsMutableLiveData.value = ResourceState.Success(data)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    neverHaveIEverQuestionsMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
}