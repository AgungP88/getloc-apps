package com.cals.getloc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.cals.getloc.model.DataTravel

class BundleAdapter (private val data: ArrayList<DataTravel>) : RecyclerView.Adapter<BundleAdapter.MyViewHolder>()  {

    private val limit = 3

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view){

        fun bind(home: DataTravel){
            with(view){
                val name = view.findViewById<TextView>(R.id.tvNameWisata)
                val city = view.findViewById<TextView>(R.id.tvCity)
                val plan = view.findViewById<TextView>(R.id.tvPlan)

                name.text = home.name
                city.text = home.city
                plan.text = home.category
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
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