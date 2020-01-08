package com.alimasanov.unsplash.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UnsplashDB(context: Context?): SQLiteOpenHelper(context,
    DB_NAME, null,
    DB_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        val createUnsplashTable = "CREATE TABLE $TABLE_NAME (                    " +
                                    "   $COLUMN_ID              INTEGER PRIMARY KEY,    " +
                                    "   $COLUMN_PHOTO           TEXT,                   " +
                                    "   $COLUMN_DESCRIPTION     TEXT,                   " +
                                    "   $COLUMN_LOCATION        TEXT,                   " +
                                    "   $COLUMN_TIMESTAMP       TIMESTAMP DEFAULT CURRENT_TIMESTAMP)"

        val insertIntoTable = " INSERT INTO $TABLE_NAME($COLUMN_ID, $COLUMN_PHOTO, $COLUMN_DESCRIPTION, $COLUMN_LOCATION)" +
                " VALUES(1,                                                                         " +
                "       'https://images.unsplash.com/photo-1417325384643-aac51acc9e5d?q=75&fm=jpg', " +
                "       'A man drinking a coffee.',                                                 " +
                "       'Montreal, Canada')                                                         "
        db.execSQL(createUnsplashTable)
        db.execSQL(insertIntoTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        const val DB_NAME = "UnsplashSavedPhoto.db"
        const val DB_VERSION = 1

        const val TABLE_NAME = "Photos"
        const val COLUMN_ID = "ID"
        const val COLUMN_PHOTO = "ID_Photo"
        const val COLUMN_DESCRIPTION = "Description"
        const val COLUMN_LOCATION = "Location"
        const val COLUMN_TIMESTAMP = "timestamp"
    }
}
