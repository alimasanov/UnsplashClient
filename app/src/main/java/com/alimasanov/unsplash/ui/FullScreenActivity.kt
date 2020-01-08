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
        val updatedAt: TextView = findViewById(R.id.ff_updated_at)
        val width: TextView = findViewById(R.id.ff_width)
        val height: TextView = findViewById(R.id.ff_height)
        val likes: TextView = findViewById(R.id.ff_likes)
        val downloads: TextView = findViewById(R.id.ff_download)
        val username: TextView = findViewById(R.id.ff_username)

        val link: String = bundle!!.get("LinkPhoto").toString()
        Picasso.get()
            .load(link)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(fullImage)

        description.text = bundle.get("Description").toString()
        location.text = bundle.get("Location").toString()
        description.text = bundle.get("Description").toString()
        createdAt.text = bundle.get("CreatedAt").toString()
        updatedAt.text = bundle.get("UpdatedAt").toString()
        width.text = bundle.get("Width").toString()
        height.text = bundle.get("Height").toString()
        likes.text = bundle.get("Likes").toString()
        downloads.text = bundle.get("Downloads").toString()
        username.text = bundle.get("Username").toString()
    }
}