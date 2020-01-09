package com.alimasanov.unsplash.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alimasanov.unsplash.R
import com.squareup.picasso.Picasso

class FullScreenActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_activity)

        val bundle: Bundle? = intent.extras

        val linearMain: LinearLayout = findViewById(R.id.linear_main)
        val fullImage: ImageView = findViewById(R.id.full_image)
        val description: TextView = findViewById(R.id.ff_description)
        val location: TextView = findViewById(R.id.ff_location)
        val createdAt: TextView = findViewById(R.id.ff_created_at)
        val updatedAt: TextView = findViewById(R.id.ff_updated_at)
        val width: TextView = findViewById(R.id.ff_width)
        val height: TextView = findViewById(R.id.ff_height)
        val likes: TextView = findViewById(R.id.ff_likes)
        val downloads: TextView = findViewById(R.id.ff_download)
        val username: TextView = findViewById(R.id.ff_username)

        //нужно проинициализировать линеры их дохуя

        val link: String = bundle!!.get("LinkPhoto").toString()
        Picasso.get()
            .load(link)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(fullImage)

        if(bundle.get("Description").toString() == null){
            linearMain.removeView(description)
        } else description.text = ("Description: ${bundle.get("Description").toString()}")

        if(bundle.get("Location").toString() == null) {

        }
        location.text = bundle.get("Location").toString()
        createdAt.text = ("${R.string.fa_created_at} ${bundle.get("CreatedAt").toString()}")
        updatedAt.text = ("${R.string.fa_updated_at} ${bundle.get("UpdatedAt").toString()}")
        width.text = ("${R.string.fa_width} ${bundle.get("Width").toString()}")
        height.text = ("${R.string.fa_height} ${bundle.get("Height").toString()}")
        likes.text = ("${R.string.fa_likes} ${bundle.get("Likes").toString()}")
        downloads.text = ("${R.string.fa_download} ${bundle.get("Downloads").toString()}")
        username.text = ("${R.string.fa_username} ${bundle.get("Username").toString()}")
    }
}