package com.cals.getloc.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cals.getloc.R
import com.cals.getloc.adapter.RekomendasiAdapter
import com.cals.getloc.viewmodel.RekomendasiViewModel
import kotlinx.android.synthetic.main.activity_rekomendasi.*

class RekomendasiActivity : AppCompatActivity() {

    private lateinit var viewModel: RekomendasiViewModel
    private lateinit var adapter: RekomendasiAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rekomendasi)

        adapter = RekomendasiAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(RekomendasiViewModel::class.java)


        rv_rekomendasiAct.setHasFixedSize(true)
        rv_rekomendasiAct.layoutManager = LinearLayoutManager(this)
        rv_rekomendasiAct.adapter = adapter

        val btn_back : ImageView = findViewById(R.id.backarrow)

        btn_back.setOnClickListener {
            onBackPressed()
        }
        viewModel.setAllWisataRekomendasi()

        viewModel.getAllWisataRekomendasi().observe(this, {
            if (it!=null){
                adapter.setList(it)
            }
        })

    }
}