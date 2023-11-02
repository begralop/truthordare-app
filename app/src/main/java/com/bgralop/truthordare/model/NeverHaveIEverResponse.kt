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