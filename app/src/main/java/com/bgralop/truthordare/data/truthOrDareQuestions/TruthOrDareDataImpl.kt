package com.bgralop.truthordare.data.truthOrDareQuestions

import com.bgralop.truthordare.data.truthOrDareQuestions.local.TruthOrDareLocalImpl
import com.bgralop.truthordare.data.truthOrDareQuestions.remote.TruthOrDareRemoteImpl
import com.bgralop.truthordare.domain.TruthOrDareRepository
import com.bgralop.truthordare.model.NeverHaveIEverQuestion
import com.bgralop.truthordare.model.TruthOrDareQuestions

class TruthOrDareDataImpl(
    private val truthOrDareLocalImpl: TruthOrDareLocalImpl,
    private val truthOrDareRemoteImpl: TruthOrDareRemoteImpl,
) : TruthOrDareRepository {
    override suspend fun getTruthQuestion(): TruthOrDareQuestions {
        return truthOrDareRemoteImpl.getTruthQuestion()
    }
    override suspend fun getDareQuestion(): TruthOrDareQuestions {
        return truthOrDareRemoteImpl.getDareQuestion()

    }
    override suspend fun getNeverHaveIEverQuestion(): NeverHaveIEverQuestion {
        return truthOrDareRemoteImpl.getNeverHaveIEverQuestion()
    }

}