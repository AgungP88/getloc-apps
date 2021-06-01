package com.cals.getloc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.cals.getloc.model.DataTravel

class RekomendasiAdapter (private val data: ArrayList<DataTravel>) : RecyclerView.Adapter<RekomendasiAdapter.MyViewHolder>()  {


    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(home: DataTravel){
            val name = view.findViewById<TextView>(R.id.tvNameWisata)
            val city = view.findViewById<TextView>(R.id.tvCity)
            val plan = view.findViewById<TextView>(R.id.tvPlan)

            name.text = home.name
            city.text = home.city
            plan.text = home.category
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int = data.size



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }


}