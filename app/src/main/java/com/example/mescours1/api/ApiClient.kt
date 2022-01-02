package com.example.mescours1.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    //const val BASE_URL = "https://apicahierdetexte.herokuapp.com/"
    const val BASE_URL = "http://192.168.1.106:8000"

    fun makeRetrofitService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }
}