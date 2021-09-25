package com.example.app.Database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ClientesDB (context: Context): SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    private val DB: SQLiteDatabase
    private val values: ContentValues

    companion object{
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "Clientes"
    }

    init{
        DB = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE "+ Tablas.Cliente.TABLE_NAME+" (" +
        Tablas.Cliente.NOMBRE+ " TEXT NOT NULL," +
        Tablas.Cliente.CEDULA+ " INTEGER PRIMARY KEY," +
        Tablas.Cliente.EDAD+ " TEXT NOT NULL," +
        Tablas.Cliente.FECHA_NAC+ " TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}