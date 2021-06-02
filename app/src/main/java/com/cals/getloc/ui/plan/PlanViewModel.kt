package com.cals.getloc.ui.plan

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cals.getloc.api.RetrofitClient
import com.cals.getloc.model.DataTravel
import com.cals.getloc.model.HomeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlanViewModel : ViewModel() {

    val listWisata = MutableLiveData<ArrayList<DataTravel>>()

    fun setSearchWisata(query: String){
        RetrofitClient.instance
            .getSearchWisatabyName(query)
            .enqueue(object : Callback<HomeModel> {
                override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                    if (response.isSuccessful) {
                        listWisata.postValue(response.body()?.data)
                    }
                }

                override fun onFailure(call: Call<HomeModel>, t: Throwable) {
                    Log.d("onFailure: ", t.message.toString())
                }

            })

    }


    fun getSearchWisata(): LiveData<ArrayList<DataTravel>>{
        return  listWisata
    }
}