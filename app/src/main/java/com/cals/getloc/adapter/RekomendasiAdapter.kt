package com.cals.getloc.adapter

import android.annotation.SuppressLint
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
import com.cals.getloc.model.PaketTravel

class RekomendasiAdapter : RecyclerView.Adapter<RekomendasiAdapter.RekomendasiViewModel>() {

    private val list = ArrayList<PaketTravel>()

    fun setList(paketTravel: ArrayList<PaketTravel>){
        list.clear()
        list.addAll(paketTravel)
        notifyDataSetChanged()
    }

    inner class RekomendasiViewModel(val view: View): RecyclerView.ViewHolder(view){
        @SuppressLint("SetTextI18n")
        fun bind(paketTravel: PaketTravel){
            val name = view.findViewById<TextView>(R.id.tvNameWisata)
            val city = view.findViewById<TextView>(R.id.tvCity)
            val plan = view.findViewById<TextView>(R.id.tvPlan)
            val imgUser = view.findViewById<ImageView>(R.id.img_user)

            city.text = paketTravel.city
            name.text = "Rekomendasi 5 Tempat Wisata di "+paketTravel.city
            plan.text = "Plan "+ paketTravel.id

            Glide.with(itemView.context)
                .load("https://picsum.photos/200?random=20")
                .transform(RoundedCorners(20))
                .apply(
                    RequestOptions.placeholderOf(R.drawable.ic_loader)
                        .error(R.drawable.ic_error)
                )
                .into(imgUser)

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