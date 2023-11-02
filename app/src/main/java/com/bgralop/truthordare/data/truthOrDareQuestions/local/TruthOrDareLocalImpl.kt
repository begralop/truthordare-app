package com.bgralop.truthordare.data.truthOrDareQuestions.local

import com.bgralop.truthordare.data.local.MemoryCache
import com.bgralop.truthordare.model.NeverHaveIEverQuestion
import com.bgralop.truthordare.model.TruthOrDareQuestions

class TruthOrDareLocalImpl(
    private val memoryCache: MemoryCache
)
    {
        fun getTruthQuestion(): TruthOrDareQuestions? {
            return memoryCache.truthOrDareQuestions?.get(0)
        }

        fun getDareQuestions(): TruthOrDareQuestions? {
            return memoryCache.truthOrDareQuestions?.get(0)
        }

        fun getNeverHaveIEverQuestions(): NeverHaveIEverQuestion? {
            return memoryCache.neverIHaveEverQuestions?.get(0)
        }
}