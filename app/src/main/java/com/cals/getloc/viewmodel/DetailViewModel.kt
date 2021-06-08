package com.cals.getloc.viewmodel

import androidx.lifecycle.ViewModel

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