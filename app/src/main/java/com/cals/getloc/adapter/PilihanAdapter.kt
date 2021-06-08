package com.cals.getloc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cals.getloc.R
import com.cals.getloc.model.DataTravel

class PilihanAdapter: RecyclerView.Adapter<PilihanAdapter.PilihanViewModel>() {

    private val list = ArrayList<DataTravel>()

    fun setList(dataTravel: ArrayList<DataTravel>){
        list.clear()
        list.addAll(dataTravel)
        notifyDataSetChanged()
    }

    inner class PilihanViewModel(val view: View): RecyclerView.ViewHolder(view){
        fun bind(dataTravel: DataTravel){
            val name = view.findViewById<TextView>(R.id.tvNameWisata)
            val city = view.findViewById<TextView>(R.id.tvCity)
            val imgUser = view.findViewById<ImageView>(R.id.img_user)

            name.text = dataTravel.name
            city.text = dataTravel.city

            Glide.with(itemView.context)
                .load("https://picsum.photos/200?random=30")
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loader)
                        .error(R.drawable.ic_error)
                )
                .into(imgUser)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PilihanViewModel {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item_two, parent, false)
        return PilihanViewModel(v)
    }

    override fun onBindViewHolder(holder: PilihanViewModel, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}