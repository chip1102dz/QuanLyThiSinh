package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDataBase(context: Context): SQLiteOpenHelper(context,DATABASE,null,VERSION) {
    companion object{
        const val DATABASE = "ABC1.db"
        const val VERSION = 1
        const val TABLE = "user"
        const val ID = "id"
        const val NAME = "name"
        const val TOAN = "toan"
        const val HOA = "hoa"
        const val LY = "ly"
        const val UUTIEN = "uutien"
        const val CREATE_TABLE =
            " CREATE TABLE " + TABLE + "(" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    NAME + " TEXT, " +
                    TOAN + " INTEGER, " +
                    HOA + " INTEGER, " +
                    LY + " INTEGER, " +
                    UUTIEN + " TEXT " + ");"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
    fun insertUser(user: User){
        val db = writableDatabase
        val values = ContentValues()
        values.put(NAME, user.name)
        values.put(TOAN, user.toan)
        values.put(HOA, user.hoa)
        values.put(LY, user.ly)
        values.put(UUTIEN, user.uutien)
        db.insert(TABLE, null, values)
    }
}