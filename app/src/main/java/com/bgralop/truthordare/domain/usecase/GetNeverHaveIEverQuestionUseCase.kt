package com.bgralop.truthordare.domain.usecase

import com.bgralop.truthordare.domain.TruthOrDareRepository
import com.bgralop.truthordare.model.NeverHaveIEverQuestion

class GetNeverHaveIEverQuestionUseCase(
    private val truthOrDareRepository: TruthOrDareRepository
) {

    suspend fun execute(count: Int): List<NeverHaveIEverQuestion> {
        val questions = mutableListOf<NeverHaveIEverQuestion>()
        repeat(count) {
            val question = truthOrDareRepository.getNeverHaveIEverQuestion()
            questions.add(question)
        }
        return questions
    }
}
