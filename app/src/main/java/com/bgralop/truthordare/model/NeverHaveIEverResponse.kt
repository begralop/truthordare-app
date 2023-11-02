package com.bgralop.truthordare.model

import com.google.gson.annotations.SerializedName

data class NeverHaveIEverResponse (
    @SerializedName("results-nhie") val neverHaveIEverQuestion: List<NeverHaveIEverQuestion>
)

data class NeverHaveIEverQuestion (
    val id: String,
    val type: String,
    val rating: String,
    val question: String,
    val translations: Map<String, String>
)

data class Translations (
    val bn: String,
    val de: String,
    val es: String,
    val fr: String,
    val hi: String,
    val tl: String
)
