package com.cals.getloc.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient{
    private const val BASE_URL = "https://getloc-314510.et.r.appspot.com"

    private const val PREDICT_URL = "http://34.101.230.116/getloc-api-recomendation"

    val instance: ApiEndpoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiEndpoint::class.java)
    }


    val predict: ApiEndpoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(PREDICT_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiEndpoint::class.java)
    }



}