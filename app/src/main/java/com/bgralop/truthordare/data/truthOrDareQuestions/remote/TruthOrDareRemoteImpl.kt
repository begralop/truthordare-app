package com.bgralop.truthordare.data.truthOrDareQuestions.remote

import com.bgralop.truthordare.data.remote.TruthOrDareService
import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.model.TruthOrDareResponse

class TruthOrDareRemoteImpl(
    private val truthOrDareService: TruthOrDareService
) {

    suspend fun getTruthQuestion(): TruthOrDareQuestions {
        return truthOrDareService.getTruth()
    }

    suspend fun getDareQuestion(): TruthOrDareQuestions {
        return truthOrDareService.getDare()
    }
}