package com.example.register

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context:Context): SQLiteOpenHelper(context, Constants.DATABASE_NAME,
    null, Constants.DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ${Constants.ENTITY_REGISTER}(" +
                "${Constants.PROPERTY_ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                "${Constants.PROPERTY_NAME} VARCHAR(20)," +
                "${Constants.PROPERTY_LASTNAME} VARCHAR(20)," +
                "${Constants.PROPERTY_PHONE} INTEGER(10)," +
                "${Constants.PROPERTY_ADDRESS} VARCHAR(60)," +
                "${Constants.PROPERTY_DESCRIPTION} VARCHAR(120))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}



