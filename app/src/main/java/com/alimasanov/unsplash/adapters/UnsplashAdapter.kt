package com.alimasanov.unsplash.adapters

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.server.pojo.Photo
import com.alimasanov.unsplash.ui.FullScreenActivity
import com.squareup.picasso.Picasso

class UnsplashAdapter(private val context: Context?,
                      private var photos: List<Photo>?,
                      private val db: SQLiteDatabase):
    RecyclerView.Adapter<UnsplashAdapter.UnsplashViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = UnsplashViewHolder(LayoutInflater
                                .from(parent.context)
                                .inflate(R.layout.photo_item, parent, false))

    override fun getItemCount(): Int {
        return photos!!.count()
    }

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
            holder.ll_main.removeView(cardDesc)
        }
        else holder.card_desc.text = cardDesc

        if (photo.location?.country !== null && photo.location.city !== null){
            val cardLoc: String? = "${photo.location.country}, ${photo.location.city}"
            holder.card_location.text = cardLoc
        } else if(photo.location?.country == null && photo.location?.city !== null){
            val cardLoc: String? = photo.location.city
            holder.card_location.text = cardLoc
        } else if(photo.location?.country !== null && photo.location.city == null){
            val cardLoc: String? = photo.location.country
            holder.card_location.text = cardLoc
        } else holder.ll_main.removeAllViews()

        holder.itemView.setOnLongClickListener{
            val cv = ContentValues()
            cv.put(UnsplashDB.COLUMN_PHOTO, photo.urls!!.small)
            cv.put(UnsplashDB.COLUMN_DESCRIPTION, photo.description)
            cv.put(UnsplashDB.COLUMN_LOCATION, "${photo.location!!.city} ${photo.location.country}")

            db.insert(UnsplashDB.TABLE_NAME, null, cv)

            true
        }

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, FullScreenActivity::class.java)
            intent.putExtra("LinkPhoto", photo.urls!!.regular)
            intent.putExtra("Description", photo.description)
            intent.putExtra("CreatedAt", photo.created_at)
            intent.putExtra("UpdatedAt", photo.updated_at)
            intent.putExtra("Width", photo.width)
            intent.putExtra("Height", photo.height)
            intent.putExtra("Likes", photo.likes)
            intent.putExtra("Downloads", photo.downloads)
            intent.putExtra("Username", photo.user!!.username)
            intent.putExtra("Location", "${photo.location!!.country}, ${photo.location.city}")
            it.context.startActivity(intent)
        }

    }

    class UnsplashViewHolder(itemView: View,
                             val card_image: ImageView = itemView.findViewById(R.id.card_image),
                             val card_desc: TextView = itemView.findViewById(R.id.card_desc),
                             val card_location: TextView = itemView.findViewById(R.id.card_location),
                             val ll_main: RelativeLayout = itemView.findViewById(R.id.ll_main),
                             val ll: LinearLayout = itemView.findViewById(R.id.ll)):
        RecyclerView.ViewHolder(itemView)
}