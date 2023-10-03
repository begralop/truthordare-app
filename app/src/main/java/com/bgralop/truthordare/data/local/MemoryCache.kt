package com.bgralop.truthordare.data.local

import com.bgralop.truthordare.model.TruthOrDareQuestions

class MemoryCache {

    var truthOrDareQuestions: List<TruthOrDareQuestions>? = null
    fun clearAll() {
        truthOrDareQuestions = null
    }
}