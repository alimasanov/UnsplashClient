package com.alimasanov.unsplash.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.server.PhotoOperations
import com.alimasanov.unsplash.ui.GridItemDecoration

class PhotoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_photo, container, false)

        val recyclerView: RecyclerView = root.findViewById(R.id.photo_recycler_view)
        recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            addItemDecoration(GridItemDecoration(10, 2))
        }

        val photoOperations = PhotoOperations()
        photoOperations.loadRandomPhotos(context, recyclerView, 30)

        return root
    }

}