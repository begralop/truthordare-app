package com.bgralop.truthordare.domain.usecase

import com.bgralop.truthordare.domain.TruthOrDareRepository
import com.bgralop.truthordare.model.TruthOrDareQuestions

class GetDareQuestionsUseCase(
    private val truthOrDareRepository: TruthOrDareRepository
) {
    suspend fun execute(): TruthOrDareQuestions {
        return truthOrDareRepository.getDareQuestion()
    }
}