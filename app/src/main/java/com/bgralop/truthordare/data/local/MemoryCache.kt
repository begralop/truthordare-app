package com.bgralop.truthordare.data.local

import com.bgralop.truthordare.model.NeverHaveIEverQuestion
import com.bgralop.truthordare.model.TruthOrDareQuestions

class MemoryCache {

    var truthOrDareQuestions: List<TruthOrDareQuestions>? = null

    var neverIHaveEverQuestions: List <NeverHaveIEverQuestion>? = null
    fun clearAll() {
        truthOrDareQuestions = null
        neverIHaveEverQuestions = null
    }
}