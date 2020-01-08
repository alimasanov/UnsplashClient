package com.alimasanov.unsplash.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.adapters.UnsplashAdapter
import com.alimasanov.unsplash.server.NetworkEndpoints
import com.alimasanov.unsplash.server.pojo.Photo
import com.alimasanov.unsplash.server.UnsplashClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create

class PhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_photo, container, false)
        val recyclerView: RecyclerView = root.findViewById(R.id.photo_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val networkEndpoints: NetworkEndpoints = UnsplashClient().getUnsplashClient().create()
        val photoList = networkEndpoints.getRandomPhotos(30)

        photoList.enqueue(object: Callback<List<Photo>>{
            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                val unsplashAdapter = UnsplashAdapter(context, response.body())
                recyclerView.adapter = unsplashAdapter
            }
        })

        return root
    }
}