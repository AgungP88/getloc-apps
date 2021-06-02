package com.cals.getloc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.cals.getloc.model.DataTravel

class RekomendasiAdapter : RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewModel>() {

    private val list = ArrayList<DataTravel>()

    fun setList(dataTravel: ArrayList<DataTravel>){
        list.clear()
        list.addAll(dataTravel)
        notifyDataSetChanged()
    }

    inner class RekomendasiViewModel(val view: View): RecyclerView.ViewHolder(view){
        fun bind(dataTravel: DataTravel){
            val name = view.findViewById<TextView>(R.id.tvNameWisata)
            val city = view.findViewById<TextView>(R.id.tvCity)

            name.text = dataTravel.name
            city.text = dataTravel.city

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekomendasiViewModel {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        return RekomendasiViewModel(v)
    }

    override fun onBindViewHolder(holder: RekomendasiViewModel, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}