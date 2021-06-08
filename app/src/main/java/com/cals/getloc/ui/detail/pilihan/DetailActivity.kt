package com.cals.getloc.ui.detail.pilihan

import android.os.Bundle
import android.view.Menu
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.*
import com.cals.getloc.R
import com.cals.getloc.databinding.ActivityDetailBinding
import com.cals.getloc.databinding.ContentDetailBinding

class DetailActivity : AppCompatActivity() {


    companion object {
        const val EXTRA_PLACE_ID = "extra_place_id"
    }

    private var menu: Menu? = null
    private lateinit var binding: ActivityDetailBinding
    private lateinit var contentDetailBinding: ContentDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val btn_back: ImageView = findViewById(R.id.backarrowHome)

        btn_back.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        this.menu = menu
        val viewModel = ViewModelProvider(this@DetailActivity, NewInstanceFactory())[DetailViewModel::class.java]
        val extras = intent.extras
        val placeId = extras?.getString(EXTRA_PLACE_ID)


        if (placeId != null) {

            viewModel.setWisataDetail(placeId)
            viewModel.getDetailDetail().observe(this, {
                contentDetailBinding.tvNameDetail.text = it.toString()
                contentDetailBinding.tvDesc.text = it.toString()
                contentDetailBinding.tvRate.text = it.toString()

            })
        }
        return true
    }



}