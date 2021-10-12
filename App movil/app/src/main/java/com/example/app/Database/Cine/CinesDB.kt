package com.example.app.Database.Cine

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.app.Database.Cliente.ClienteModelo
import com.example.app.Database.Tablas

class CinesDB (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Cines"
    }

    init {
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE " + Tablas.Cine.TABLE_NAME + " (" +
                    Tablas.Cine.NOMBRE + " TEXT NOT NULL PRIMARY KEY," +
                    Tablas.Cine.UBICACION + " TEXT NOT NULL," +
                    Tablas.Cine.CANTIDAD_SALAS + " INTEGER)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(cine: CineModelo) {
        values.put(Tablas.Cine.NOMBRE, cine.nombre_cine)
        values.put(Tablas.Cine.UBICACION, cine.ubicacion)
        values.put(Tablas.Cine.CANTIDAD_SALAS, cine.cantidad_salas)
        db.insert(Tablas.Cine.TABLE_NAME, null, values)
        Log.d("Cine AGREGADO:", values.toString())
    }

    fun getData(): MutableList<CineModelo> {
        val list: MutableList<CineModelo> = ArrayList()
        val query = "Select * from " + Tablas.Cine.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val cine = CineModelo()
                cine.nombre_cine = result.getString(result.getColumnIndex(Tablas.Cine.NOMBRE))
                list.add(cine)
            } while (result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }
}