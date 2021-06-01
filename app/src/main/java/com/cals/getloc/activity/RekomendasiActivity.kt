package com.cals.getloc.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.cals.getloc.adapter.RekomendasiAdapter
import com.cals.getloc.api.RetrofitClient
import com.cals.getloc.model.DataTravel
import com.cals.getloc.model.HomeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RekomendasiActivity : AppCompatActivity() {

    private var list =  ArrayList<DataTravel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekomendasi)

        showData()

        val btn_back : ImageView = findViewById(R.id.backarrow)

        btn_back.setOnClickListener {
            onBackPressed()
        }



    }

    private fun showData(){
        val rv_rekomendasi : RecyclerView = findViewById(R.id.rv_rekomendasiAct)

        rv_rekomendasi .setHasFixedSize(true)
        rv_rekomendasi .layoutManager = LinearLayoutManager(this)
        RetrofitClient.instance.getAllWisata().enqueue(object : Callback<HomeModel> {
            override fun onResponse(call: Call<HomeModel>, response: Response<HomeModel>) {
                val listWisata = response.body()?.data
                listWisata?.let { list.addAll(it) }
                val adapter = RekomendasiAdapter(list)
                rv_rekomendasi .adapter = adapter
            }

            override fun onFailure(call: Call<HomeModel>, t: Throwable) {

            }

        })
    }
}