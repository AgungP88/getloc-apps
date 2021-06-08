package com.cals.getloc.api

import com.cals.getloc.model.HomeModel
import com.cals.getloc.model.PaketResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("/wisata")
    fun getAllWisata(): Call<HomeModel>

    @GET("/paket")
    fun getAllPaket(): Call<PaketResponse>

    @GET("/wisata/search/name")
    fun getSearchWisatabyName(
        @Query("nama") query: String
    ): Call<HomeModel>


}