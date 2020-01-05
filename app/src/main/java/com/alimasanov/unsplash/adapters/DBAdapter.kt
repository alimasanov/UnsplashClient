package com.alimasanov.unsplash.adapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.DB.UnsplashDB
import com.alimasanov.unsplash.R
import com.squareup.picasso.Picasso

class DBAdapter(val context: Context,
                val cursor: Cursor): RecyclerView.Adapter<DBAdapter.DBViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DBViewHolder(LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.fav_item, parent, false))

    override fun getItemCount(): Int {
        return cursor.count
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        if(!cursor.moveToPosition(position)) {
            return
        }
        val image_link: String = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_PHOTO))
        val description: String = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_DESCRIPTION))
        val location: String = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_LOCATION))

        holder.card_desc.text = description
        holder.card_location.text = location
        Picasso
            .with(context)
            .load(image_link)
            .into(holder.card_image)
    }

    class DBViewHolder(itemView: View,
                       val card_image: ImageView = itemView.findViewById(R.id.card_image),
                       val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                       val card_location: TextView = itemView.findViewById(R.id.card_location)):
        RecyclerView.ViewHolder(itemView)
}