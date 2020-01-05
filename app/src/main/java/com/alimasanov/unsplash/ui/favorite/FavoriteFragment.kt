package com.alimasanov.unsplash.ui.favorite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alimasanov.unsplash.DB.UnsplashDB
import com.alimasanov.unsplash.R
import com.alimasanov.unsplash.adapters.DBAdapter

class FavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val unsplashDB: UnsplashDB? = UnsplashDB(context)
        val db: SQLiteDatabase = unsplashDB!!.writableDatabase
        val recyclerView: RecyclerView = root.findViewById(R.id.fav_rv)
        recyclerView.layoutManager = LinearLayoutManager(context)
        val dbAdapter: DBAdapter = DBAdapter(context, getAllItems(db))
        recyclerView.adapter = dbAdapter

        return root
    }

    fun getAllItems(db: SQLiteDatabase): Cursor? {
        return db.query(UnsplashDB.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)
    }
}