package com.cals.getloc.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.cals.getloc.R
import com.cals.getloc.adapter.onBoardingViewPagerAdapter
import com.cals.getloc.model.onBoardingData
import com.google.android.material.tabs.TabLayout

class onBoardingActivity : AppCompatActivity() {

    var onBoardingViewPagerAdapter: onBoardingViewPagerAdapter?= null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var lewati: TextView?= null
    var position = 0
    var sharePreference: SharedPreferences?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (restorePrefData()){
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
        setContentView(R.layout.activity_on_boarding)

        tabLayout = findViewById(R.id.tab_indicator)
        lewati = findViewById(R.id.tv_lewati)


        val onBoardingData:  MutableList<onBoardingData> = ArrayList()
        onBoardingData.add(onBoardingData(getString(R.string.destinasi_wisata_disekitarmu),  getString(
            R.string.desc_one
        ),
            R.drawable.asset_one
        ))
        onBoardingData.add(onBoardingData(getString(R.string.title_two),  getString(R.string.desc_two),
            R.drawable.asset_two
        ))
        onBoardingData.add(onBoardingData(getString(R.string.title_three), getString(R.string.desc_three),
            R.drawable.asset_three
        ))



        setOnBoardingViewPagerAdapter(onBoardingData)

        position = onBoardingViewPager!!.currentItem

        lewati?.setOnClickListener {
           if (position<onBoardingData.size){
               position++
               onBoardingViewPager!!.currentItem = position
           }
            if (position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, LoginActivity::class.java)
                startActivity(i)
            }

        }

        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                position = tab!!.position
                if (tab.position == onBoardingData.size - 1){
                    lewati!!.text = getString(R.string.mulai_sekarang)
                } else{
                    lewati!!.text = getString(R.string.lewati)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }


        })

    }


    private fun setOnBoardingViewPagerAdapter(onBoardingData: List<onBoardingData>){

        onBoardingViewPager = findViewById(R.id.viewpager)
        onBoardingViewPagerAdapter = onBoardingViewPagerAdapter(this, onBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)


    }

    private fun savePrefData(){
        sharePreference = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharePreference!!.edit()
        editor.putBoolean("isFirstTimeRun", true)
        editor.apply()

    }


    private fun restorePrefData(): Boolean{
        sharePreference = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
       return sharePreference!!.getBoolean("isFirstTimeRun", false)

    }
}