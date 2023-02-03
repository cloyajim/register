package com.example.register

import android.annotation.SuppressLint
import android.content.ContentValues
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

    @SuppressLint("Range", "Recycle")
    fun getAllRegister(): MutableList<Register>{
        val registers:MutableList<Register> = mutableListOf()

        val database = this.readableDatabase
        val query = "SELECT * FROM ${Constants.ENTITY_REGISTER}"

        val result = database.rawQuery(query,null)

        if(result.moveToFirst()){
            do{
                val register = Register()
                register.id = result.getLong(result.getColumnIndex(Constants.PROPERTY_ID))
                register.name = result.getString(result.getColumnIndex(Constants.PROPERTY_NAME))
                register.lastName = result.getString(result.getColumnIndex(Constants.PROPERTY_LASTNAME))
                register.phone = result.getString(result.getColumnIndex(Constants.PROPERTY_PHONE))
                register.address = result.getString(result.getColumnIndex(Constants.PROPERTY_ADDRESS))
                register.description = result.getString(result.getColumnIndex(Constants.PROPERTY_DESCRIPTION))
                registers.add(register)

            } while (result.moveToNext())
        }

        return registers
    }

    fun insertRegister(register: Register): Long {
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.PROPERTY_NAME, register.name)
            put(Constants.PROPERTY_LASTNAME, register.lastName)
            put(Constants.PROPERTY_PHONE, register.phone)
            put(Constants.PROPERTY_ADDRESS, register.address)
            put(Constants.PROPERTY_DESCRIPTION, register.description)
        }

        val resultId = database.insert(Constants.ENTITY_REGISTER, null, contentValues)
        return resultId
    }

    //Falta implementar este metodo
    /*fun updateRegister(register: Register): Boolean{
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.PROPERTY_NAME, register.name)
            put(Constants.PROPERTY_LASTNAME, register.lastName)
            put(Constants.PROPERTY_PHONE, register.phone)
            put(Constants.PROPERTY_ADDRESS, register.address)
            put(Constants.PROPERTY_DESCRIPTION, register.description)
        }

        val result = database.update(Constants.ENTITY_REGISTER,
            contentValues,
            "${Constants.PROPERTY_ID} = ${register.id}",
            null)

        return result == Constants.TRUE
    }*/

    fun deleteRegister(register: Register) : Boolean{
        val database = this.writableDatabase
        val result = database.delete(Constants.ENTITY_REGISTER,
            "${Constants.PROPERTY_ID} == ${register.id}",
            null)

        return  result == Constants.TRUE
    }
}



