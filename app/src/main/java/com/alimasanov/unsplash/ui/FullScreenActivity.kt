package com.alimasanov.unsplash.ui

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.server.PhotoOperations
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

        val linearLocation: LinearLayout = findViewById(R.id.linear_location)
        val linearCreatedAt: LinearLayout = findViewById(R.id.linear_created_at)
        val linearUpdatedAt: LinearLayout = findViewById(R.id.linear_updated_at)
        val linearLikes: LinearLayout = findViewById(R.id.linear_likes)
        val linearDownloads: LinearLayout = findViewById(R.id.linear_downloads)
        val linearUsername: LinearLayout = findViewById(R.id.linear_username)

        val link: String = bundle!!.get("LinkPhoto").toString()
        val photo = PhotoOperations().getPhotoById(link)
        Picasso.get()
            .load(photo!!.urls!!.raw)
            .placeholder(R.drawable.ic_picasso_placeholder)
            .error(R.drawable.ic_picasso_error)
            .into(fullImage)

        if(photo.description == null){
            linearMain.removeView(description)
        } else description.text = (photo.description)

        if(PhotoOperations().locationNormalize(photo) == null) {
            linearLocation.removeAllViews()
        } else location.text = PhotoOperations().locationNormalize(photo)

        if(photo.created_at == null) {
            linearCreatedAt.removeAllViews()
        } else createdAt.text = photo.created_at

        if(photo.updated_at == null) {
            linearUpdatedAt.removeAllViews()
        } else updatedAt.text = photo.updated_at

        width.text = photo.width.toString()
        height.text = photo.height.toString()

        if(photo.likes == null) {
            linearLikes.removeAllViews()
        } else likes.text = photo.likes.toString()

        if(photo.downloads == null) {
            linearDownloads.removeAllViews()
        } else downloads.text = photo.downloads.toString()

        if(photo.user!!.username == null) {
            linearUsername.removeAllViews()
        } else username.text = photo.user.username
    }
}