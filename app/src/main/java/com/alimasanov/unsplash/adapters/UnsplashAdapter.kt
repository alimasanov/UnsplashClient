package com.alimasanov.unsplash.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.server.POJO.Photo
import com.squareup.picasso.Picasso

class UnsplashAdapter(context: Context?, val photos: List<Photo>?):
    RecyclerView.Adapter<UnsplashAdapter.UnsplashViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = UnsplashViewHolder(LayoutInflater
                                .from(parent.context)
                                .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return photos!!.count()
    }

    @SuppressLint("WrongConstant")
    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        val photo: Photo = photos!![position]

        val cardImageLink: String? = photo.urls?.small
        Picasso.get()
            .load(cardImageLink)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(holder.card_image)

        val cardDesc: String? = photo.description
        if (cardDesc == null){
//            holder.card_desc.visibility = 0x00000004
            holder.ll_main.removeView(cardDesc)
        }
        else holder.card_desc.text = cardDesc

        val cardLoc: String? = "${photo.location?.country}, ${photo.location?.city}"
        if (photo.location?.country !== null && photo.location?.city !== null){
            val cardLoc: String? = "${photo.location?.country}, ${photo.location?.city}"
            holder.card_location.text = cardLoc
        } else if(photo.location?.country == null && photo.location?.city !== null){
            val cardLoc: String? = photo.location?.city
            holder.card_location.text = cardLoc
        } else if(photo.location?.country !== null && photo.location?.city == null){
            val cardLoc: String? = photo.location?.country
            holder.card_location.text = cardLoc
        } else {
//            holder.card_location.visibility = 0x00000004
//            holder.card_image_location.visibility = 0x00000004
            holder.ll.removeAllViews()
        }

    }

    class UnsplashViewHolder(itemView: View,
                             val card_image: ImageView = itemView.findViewById(R.id.card_image),
                             val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                             val card_location: TextView = itemView.findViewById(R.id.card_location),
                             val card_image_location: ImageView = itemView.findViewById(R.id.image_location),
                             val ll_main: LinearLayout = itemView.findViewById(R.id.ll_main),
                             val ll: LinearLayout = itemView.findViewById(R.id.ll)):
        RecyclerView.ViewHolder(itemView)
}