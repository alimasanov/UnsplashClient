package com.alimasanov.unsplash.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alimasanov.unsplash.R
import com.squareup.picasso.Picasso

class FullScreenActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fullscreen_activity)

        val bundle: Bundle? = intent.extras
        val fullImage: ImageView = findViewById(R.id.full_image)
        val description: TextView = findViewById(R.id.ff_description)
        val location: TextView = findViewById(R.id.ff_location)
        val createdAt: TextView = findViewById(R.id.ff_created_at)
        val updated_at: TextView = findViewById(R.id.ff_updated_at)
        val width: TextView = findViewById(R.id.ff_width)
        val height: TextView = findViewById(R.id.ff_height)
        val likes: TextView = findViewById(R.id.ff_likes)
        val download: TextView = findViewById(R.id.ff_download)
        val username: TextView = findViewById(R.id.ff_username)

        val link: String = bundle.get("LinkPhoto")
        Picasso.get()
            .load()
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(holder.card_image)
    }
}