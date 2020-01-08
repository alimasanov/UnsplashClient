package com.alimasanov.unsplash.adapters

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.R
import com.squareup.picasso.Picasso

class DBAdapter(private val context: Context?,
                private val cursor: Cursor?): RecyclerView.Adapter<DBAdapter.DBViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = DBViewHolder(LayoutInflater
                        .from(context)
                        .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return cursor!!.count
    }

    override fun onBindViewHolder(holder: DBViewHolder, position: Int) {
        if(!cursor!!.moveToPosition(position)) {
            return
        }
        val imageLink: String = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_PHOTO))
        val description: String = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_DESCRIPTION))
        val location: String = cursor.getString(cursor.getColumnIndex(UnsplashDB.COLUMN_LOCATION))

        holder.card_desc.text = description
        holder.card_location.text = location
        Picasso.get()
            .load(imageLink)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(holder.card_image)
    }

    class DBViewHolder(itemView: View,
                       val card_image: ImageView = itemView.findViewById(R.id.card_image),
                       val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                       val card_location: TextView = itemView.findViewById(R.id.card_location)):
        RecyclerView.ViewHolder(itemView)
}