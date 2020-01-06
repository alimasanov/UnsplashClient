package com.alimasanov.unsplash.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.server.POJO.Photo
import com.squareup.picasso.Picasso

class UnsplashAdapter(context: Context, val photos: List<Photo>):
    RecyclerView.Adapter<UnsplashAdapter.UnsplashViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = UnsplashViewHolder(LayoutInflater
                                .from(parent.context)
                                .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return photos.count()
    }

    override fun onBindViewHolder(holder: UnsplashViewHolder, position: Int) {
        val photo: Photo = photos.get(position)

        val cardImageLink: String? = photo.urls?.regular
        val cardDesc: String? = photo.description
        val cardLoc: String? = "${photo.location?.country}, ${photo.location?.city}"

        Picasso.get()
            .load(cardImageLink)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(holder.card_image)
        holder.card_desc.text = cardDesc
        holder.card_location.text = cardLoc
    }

    class UnsplashViewHolder(itemView: View,
                             val card_image: ImageView = itemView.findViewById(R.id.card_image),
                             val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                             val card_location: TextView = itemView.findViewById(R.id.card_location)):
        RecyclerView.ViewHolder(itemView)
}