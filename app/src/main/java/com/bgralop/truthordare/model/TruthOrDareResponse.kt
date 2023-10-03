package com.bgralop.truthordare.model

import com.google.gson.annotations.SerializedName

data class TruthOrDareResponse (
    @SerializedName("results") val truthOrDareQuestions: List<TruthOrDareQuestions>
)

data class TruthOrDareQuestions(
    val id: String,
    val type: String,
    val rating: String,
    val question: String,
    val translations: Map<String, String>
)