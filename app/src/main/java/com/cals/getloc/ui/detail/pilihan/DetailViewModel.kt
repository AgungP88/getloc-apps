package com.cals.getloc.ui.detail.pilihan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cals.getloc.api.RetrofitClient
import com.cals.getloc.model.DataDetail
import com.cals.getloc.model.DetailWisata
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel: ViewModel() {
    val detail = MutableLiveData<ArrayList<DataDetail>>()


    fun setWisataDetail(placeId: String) {
        RetrofitClient.instance
            .getWisataDetail(placeId)
            .enqueue(object : Callback<DetailWisata> {
                override fun onResponse(
                    call: Call<DetailWisata>,
                    response: Response<DetailWisata>
                ) {
                    if (response.isSuccessful) {
                        detail.postValue(response.body()?.detail)
                    }
                }

                override fun onFailure(call: Call<DetailWisata>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }

            })
    }

    fun getDetailDetail(): LiveData<ArrayList<DataDetail>> {
        return detail
    }

}