package com.cals.getloc.ui.home

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
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPhotoRequest
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.PlacesClient
import java.util.*
import kotlin.collections.ArrayList

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.MyViewHolder>()  {

    private val limit = 3
    private val list = ArrayList<DataTravel>()
    var placeClient: PlacesClient? = null



    fun setList(dataTravel: ArrayList<DataTravel>){
        list.clear()
        list.addAll(dataTravel)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        fun bind(home: DataTravel) {
            val name = view.findViewById<TextView>(R.id.tvNameWisata)
            val city = view.findViewById<TextView>(R.id.tvCity)
            val imgUser = view.findViewById<ImageView>(R.id.img_user)


            name.text = home.name
            city.text = home.city

                Glide.with(itemView.context)
                    .load("https://picsum.photos/200?random=30")
                    .transform(RoundedCorners(20))
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loader)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgUser)


        }


        fun getPhoto(placeId: String) {
            val place_id = view.findViewById<ImageView>(R.id.img_user)
            val placePict = FetchPlaceRequest.builder(
                placeId,
                Arrays.asList(
                    Place.Field.PHOTO_METADATAS
                )
            ).build()

            placeClient?.fetchPlace(placePict)
                ?.addOnSuccessListener {
                    val place = it.place
                    val photoMetadata = place.photoMetadatas!![0]
                    val photoRequest = FetchPhotoRequest.builder(photoMetadata).build()
                    placeClient?.fetchPhoto(photoRequest)
                        ?.addOnSuccessListener { fetch ->
                            val bitmap = fetch.bitmap
                            place_id.setImageBitmap(bitmap)
                        }
                }

        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_item_two, parent, false)
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