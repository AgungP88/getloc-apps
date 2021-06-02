package com.cals.getloc.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.cals.getloc.model.DataTravel

class BundleAdapter : RecyclerView.Adapter<BundleAdapter.MyViewHolder>()  {

    private val limit = 3
    private val list = ArrayList<DataTravel>()

    fun setLists(dataTravel: ArrayList<DataTravel>){
        list.clear()
        list.addAll(dataTravel)
        notifyDataSetChanged()
    }

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

    override fun getItemCount(): Int {
        return if(list.size > limit){
            limit
        } else {
            list.size
        }
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
    }


}