package com.alimasanov.unsplash.server

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.adapters.UnsplashAdapter
import com.alimasanov.unsplash.db.UnsplashDB
import com.alimasanov.unsplash.server.pojo.Photo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoOperations {

    fun loadFavoritePhotos(context: Context, recyclerView: RecyclerView, count: Int){}

    fun loadRandomPhotos(
        context: Context?,
        recyclerView: RecyclerView,
        count: Int){
        val unsplashDB: UnsplashDB? = UnsplashDB(context)
        val db: SQLiteDatabase = unsplashDB!!.writableDatabase

        val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
        val photos = networkEndpoints.getRandomPhotos(count)

        photos.enqueue(object: Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val unsplashAdapter = UnsplashAdapter(context, response.body(), db)
                recyclerView.adapter = unsplashAdapter
            }
        })
    }

    fun getPhotoById(context: Context, bundle: Bundle ){}
}