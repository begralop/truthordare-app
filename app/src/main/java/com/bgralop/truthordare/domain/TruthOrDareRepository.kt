package com.bgralop.truthordare.domain

import com.bgralop.truthordare.model.NeverHaveIEverQuestion
import com.bgralop.truthordare.model.TruthOrDareQuestions

interface TruthOrDareRepository {
    suspend fun getTruthQuestion(): TruthOrDareQuestions

    suspend fun getDareQuestion(): TruthOrDareQuestions

    suspend fun getNeverHaveIEverQuestion(): NeverHaveIEverQuestion
}