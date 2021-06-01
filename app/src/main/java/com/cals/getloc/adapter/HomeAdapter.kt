package com.cals.getloc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.cals.getloc.R
import com.cals.getloc.model.DataTravel
import kotlinx.android.synthetic.main.card_item_two.view.*

class HomeAdapter(private val data: ArrayList<DataTravel>) : RecyclerView.Adapter<HomeAdapter.MyViewHolder>()  {

    private val limit = 3

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(home: DataTravel){
            with(view){
                val name = view.findViewById<TextView>(R.id.tvNameWisata)
                val city = view.findViewById<TextView>(R.id.tvCity)
                val img = view.findViewById<ImageView>(R.id.img_user)

                name.text = home.name
                city.text = home.city

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item_two, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int {
        if(data.size > limit){
            return limit;
        }
        else
        {
            return data.size
        }
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }


}