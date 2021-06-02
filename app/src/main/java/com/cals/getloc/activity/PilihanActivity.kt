package com.cals.getloc.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cals.getloc.R
import com.cals.getloc.adapter.PilihanAdapter
import com.cals.getloc.adapter.RekomendasiAdapter
import com.cals.getloc.api.RetrofitClient
import com.cals.getloc.model.HomeModel
import com.cals.getloc.viewmodel.PilihanViewModel
import com.facebook.shimmer.ShimmerFrameLayout
import kotlinx.android.synthetic.main.activity_pilihan.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PilihanActivity : AppCompatActivity() {


    private lateinit var viewModel: PilihanViewModel
    private lateinit var adapter: PilihanAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilihan)

        adapter = PilihanAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(PilihanViewModel::class.java)


        rv_pilihanAct.setHasFixedSize(true)
        rv_pilihanAct.layoutManager = LinearLayoutManager(this)
        rv_pilihanAct .adapter = adapter

        val btn_back : ImageView = findViewById(R.id.backarrows)

        btn_back.setOnClickListener {
            onBackPressed()
        }
        viewModel.setAllWisataPilihan()

        viewModel.getAllWisataPilihan().observe(this, {
            if (it!=null){
                adapter.setList(it)
            }
        })

    }

}