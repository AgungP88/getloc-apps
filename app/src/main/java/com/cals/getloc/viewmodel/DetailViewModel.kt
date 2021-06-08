package com.cals.getloc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cals.getloc.api.RetrofitClient
import com.cals.getloc.model.DetailDataTravel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {

//    val listWisata = MutableLiveData<DetailDataTravel>()
//
//    fun setWisataPilihanDetail(id: String) {
//        RetrofitClient.instance
//            .get
//            .enqueue(object : Callback<DetailDataTravel> {
//                override fun onResponse(call: Call<DetailDataTravel>, response: Response<DetailDataTravel>) {
//                    if (response.isSuccessful) {
//                        listWisata.postValue(response.body())
//                    }
//                }
//
//                override fun onFailure(call: Call<DetailDataTravel>, t: Throwable) {
//                    Log.d("onFailure: ", t.message.toString())
//                }
//
//            })
//    }
//
//    fun getWisataPilihanDetail(): LiveData<DetailDataTravel> {
//        return  listWisata
//    }
}