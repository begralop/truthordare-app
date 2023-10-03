package com.bgralop.truthordare.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.truthordarebot.xyz/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}