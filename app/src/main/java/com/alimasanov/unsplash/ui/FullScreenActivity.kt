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

        val linearLocation: LinearLayout = findViewById(R.id.linear_loacation)
        val linearCreatedAt: LinearLayout = findViewById(R.id.linear_created_at)
        val linearUpdatedAt: LinearLayout = findViewById(R.id.linear_updated_at)
        val linearLikes: LinearLayout = findViewById(R.id.linear_likes)
        val linearDownloads: LinearLayout = findViewById(R.id.linear_downloads)
        val linearUsername: LinearLayout = findViewById(R.id.linear_username)

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
            linearLocation.removeAllViews()
        } else location.text = bundle.get("Location").toString()

        if(bundle.get("CreatedAt").toString() == null) {
            linearCreatedAt.removeAllViews()
        } else createdAt.text = bundle.get("CreatedAt").toString()

        if(bundle.get("UpdatedAt").toString() == null) {
            linearUpdatedAt.removeAllViews()
        } else updatedAt.text = bundle.get("UpdatedAt").toString()

        width.text = bundle.get("Width").toString()
        height.text = bundle.get("Height").toString()

        if(bundle.get("Likes").toString() == null) {
            linearLikes.removeAllViews()
        } else likes.text = bundle.get("Likes").toString()

        if(bundle.get("Downloads").toString() == null) {
            linearDownloads.removeAllViews()
        } else downloads.text = bundle.get("Downloads").toString()

        if(bundle.get("Username").toString() == null) {
            linearUsername.removeAllViews()
        } else username.text = bundle.get("Username").toString()
    }
}