package com.cals.getloc.api

import com.cals.getloc.model.HomeModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiEndpoint {

    @GET("/wisata")
    fun getAllWisata(): Call<HomeModel>
}