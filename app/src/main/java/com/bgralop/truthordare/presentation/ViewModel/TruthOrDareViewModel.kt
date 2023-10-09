package com.bgralop.truthordare.presentation.ViewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bgralop.truthordare.domain.usecase.GetDareQuestionsUseCase
import com.bgralop.truthordare.domain.usecase.GetTruthQuestionUseCase
import com.bgralop.truthordare.model.ResourceState
import com.bgralop.truthordare.model.TruthOrDareQuestions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias TruthOrDareQuestionsState = ResourceState<TruthOrDareQuestions>
class TruthOrDareViewModel(
    private val getTruthQuestionUseCase: GetTruthQuestionUseCase,
    private val getDareQuestionsUseCase: GetDareQuestionsUseCase
): ViewModel() {

    private val truthOrDareQuestionsMutableLiveData = MutableLiveData<TruthOrDareQuestionsState>()

    fun getTruthQuestionsLiveData(): LiveData<TruthOrDareQuestionsState> {
        return truthOrDareQuestionsMutableLiveData
    }

    fun getDareQuestionsLiveData(): LiveData<TruthOrDareQuestionsState> {
        return truthOrDareQuestionsMutableLiveData
    }

    @SuppressLint("SuspiciousIndentation")
    fun fetchTruthQuestions() {
        truthOrDareQuestionsMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getTruthQuestionUseCase.execute()
                    withContext(Dispatchers.Main) {
                        truthOrDareQuestionsMutableLiveData.value = ResourceState.Success(data)
                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    truthOrDareQuestionsMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
    fun fetchDareQuestions() {
        truthOrDareQuestionsMutableLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = getDareQuestionsUseCase.execute()

                withContext(Dispatchers.Main) {
                    truthOrDareQuestionsMutableLiveData.value = ResourceState.Success(data)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    truthOrDareQuestionsMutableLiveData.value =
                        ResourceState.Error(e.localizedMessage.orEmpty())
                }
            }
        }
    }
}