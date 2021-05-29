package com.cals.getloc.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cals.getloc.R
import com.cals.getloc.fragment.HomeFragment
import com.cals.getloc.model.HomeModel
import com.cals.getloc.utils.OnItemClickCallback

class HomeAdapter(

    private val mContext: HomeFragment, private val items: List<HomeModel>) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


        private var onItemClickCallback: OnItemClickCallback? = null

        fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback?) {
            this.onItemClickCallback = onItemClickCallback
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = items[position]

            holder.tvName.text = data.namePlace
            holder.tvCity.text = data.city
            holder.cvListMain.setOnClickListener {
                onItemClickCallback?.onItemMainClicked(data)
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        //Class Holder
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var cvListMain: CardView
            var tvName: TextView
            var tvCity: TextView

            init {
                cvListMain = itemView.findViewById(R.id.card_item)
                tvName = itemView.findViewById(R.id.tvName)
                tvCity = itemView.findViewById(R.id.tvCity)

            }
        }
    }