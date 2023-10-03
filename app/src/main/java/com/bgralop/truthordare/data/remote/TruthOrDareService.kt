package com.bgralop.truthordare.data.remote

import com.bgralop.truthordare.model.TruthOrDareQuestions
import com.bgralop.truthordare.model.TruthOrDareResponse
import retrofit2.http.GET

interface TruthOrDareService {
    @GET("truth")
    suspend fun getTruth(): TruthOrDareQuestions
    @GET("dare")
    suspend fun getDare(): TruthOrDareQuestions
    //@GET("nhie")
    //suspend fun getNeverIHaveNever(): NeverIHaveNeverResponse
}