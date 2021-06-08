package com.cals.getloc.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cals.getloc.api.RetrofitClient
import com.cals.getloc.model.PaketResponse
import com.cals.getloc.model.PaketTravel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RekomendasiViewModel : ViewModel() {

    val listWisata = MutableLiveData<ArrayList<PaketTravel>>()

    fun setAllWisataRekomendasi() {
        RetrofitClient.instance
            .getAllPaket()
            .enqueue(object : Callback<PaketResponse> {
                override fun onResponse(call: Call<PaketResponse>, response: Response<PaketResponse>) {
                    if (response.isSuccessful) {
                        listWisata.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<PaketResponse>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }

            })

    }

    fun getAllWisataRekomendasi(): LiveData<ArrayList<PaketTravel>> {
        return  listWisata
    }
}