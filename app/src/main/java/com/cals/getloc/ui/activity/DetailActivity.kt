package com.cals.getloc.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.cals.getloc.R
import com.cals.getloc.ui.home.HomeViewModel
import com.cals.getloc.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_ID = "extra_id"
    }

    private lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val place_id = intent.getStringExtra(EXTRA_ID)

        val bundle = Bundle()
        bundle.putString(EXTRA_ID, place_id)

        detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailViewModel::class.java)

        val btn_back: ImageView = findViewById(R.id.backarrowHome)

        btn_back.setOnClickListener {
            onBackPressed()
        }

//        detailViewModel.setWisataPilihanDetail(place_id.toString())
//        detailViewModel.getWisataPilihanDetail().observe(this, {
//            if (it != null) {
//                apply {
//                    tvNameDetail.text = it.name
//                    tvRate.text = it.rating
//                }
//            }
//        })

    }
}