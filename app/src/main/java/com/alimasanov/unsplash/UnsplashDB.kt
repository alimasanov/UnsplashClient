package com.alimasanov.unsplash

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UnsplashDB(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_UNSPLASH_TABLE = "CREATE TABLE $TABLE_NAME (" +
                                    "   $COLUMN_ID              INTEGER PRIMARY KEY,    " +
                                    "   $COLUMN_PHOTO           TEXT,                   " +
                                    "   $COLUMN_DESCRIPTION     TEXT,                   " +
                                    "   $COLUMN_LOCATION        TEXT)                   "
        db.execSQL(CREATE_UNSPLASH_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPhoto(link : String, description : String, location : String) {
        val values : ContentValues()

        values.put(COLUMN_PHOTO, link)
        values.put(COLUMN_DESCRIPTION, description)
        values.put(COLUMN_LOCATION, location)

        val db this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getPhoto(link : String) {
        val db this.readableDatabase
        return db.rawQuery("SELECT * FROM ")
    }

    companion object{
        const val DB_NAME = "UnsplashSavedPhoto.db"
        const val DB_VERSION = 1

        val TABLE_NAME = "Photos"
        val COLUMN_ID = "ID"
        val COLUMN_PHOTO = "Photo link"
        val COLUMN_DESCRIPTION = "Description"
        val COLUMN_LOCATION = "Location"
    }
}
