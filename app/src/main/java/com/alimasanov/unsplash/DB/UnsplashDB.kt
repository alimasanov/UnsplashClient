package com.alimasanov.unsplash.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.alimasanov.unsplash.server.POJO.Photo

class UnsplashDB(context: Context): SQLiteOpenHelper(context,
    DB_NAME, null,
    DB_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        val createUnsplashTable = "CREATE TABLE $TABLE_NAME (" +
                                    "   $COLUMN_ID              INTEGER PRIMARY KEY,    " +
                                    "   $COLUMN_PHOTO           TEXT,                   " +
                                    "   $COLUMN_DESCRIPTION     TEXT,                   " +
                                    "   $COLUMN_LOCATION        TEXT)                   "
        db.execSQL(createUnsplashTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addPhoto(photo: Photo) {
        val values = ContentValues()

        values.put(COLUMN_PHOTO, photo.id)

        val db = this.writableDatabase
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getPhoto(photo: Photo): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME " +
                                "WHERE $COLUMN_PHOTO = '${photo.id}'",
            null)
    }

    fun getAllPhoto(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    companion object {
        const val DB_NAME = "UnsplashSavedPhoto.db"
        const val DB_VERSION = 1

        const val TABLE_NAME = "Photos"
        const val COLUMN_ID = "ID"
        const val COLUMN_PHOTO = "ID Photo"
        const val COLUMN_DESCRIPTION = "Description"
        const val COLUMN_LOCATION = "Location"
    }
}
