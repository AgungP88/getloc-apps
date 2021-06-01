package com.cals.getloc.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.cals.getloc.R
import com.cals.getloc.model.onBoardingData

class onBoardingViewPagerAdapter(private var context: Context, private var onBoardingList: List<onBoardingData>): PagerAdapter() {
    override fun getCount(): Int {
        return onBoardingList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view  == `object`
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    @SuppressLint("InflateParams")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context).inflate( R.layout.activity_on_boarding_one, null)

        val imageView: ImageView = view.findViewById(R.id.img_one)
        val title: TextView = view.findViewById(R.id.tv_title_one)
        val desc: TextView = view.findViewById(R.id.tv_desc_one)


        imageView.setImageResource(onBoardingList[position].imageUrl)
        title.text = onBoardingList[position].title
        desc.text = onBoardingList[position].desc

        container.addView(view)
        return view
    }
}