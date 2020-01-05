package com.alimasanov.unsplash.server

import com.alimasanov.unsplash.POJO.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkEndpoints {
    @GET("/photos/random")
    fun getRandomPhotos(
        @Query("count") count: Int
    ): Call<List<Photo>>

    @GET("/photos/:id")
    fun getPhoto(
        @Query("id") id: String
    ): Call<Photo>
}