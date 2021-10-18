package com.example.app.Database.Pelicula

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.app.Database.Tablas

class PeliculasDB (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object {
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Peliculas"
    }

    init {
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL(
            "CREATE TABLE " + Tablas.Pelicula.TABLE_NAME + " (" +
                    Tablas.Pelicula.NOMBRE_ORIGINAL + " TEXT NOT NULL PRIMARY KEY," +
                    Tablas.Pelicula.IMAGEN + " TEXT NOT NULL," +
                    Tablas.Pelicula.DURACION + " TEXT NOT NULL," +
                    Tablas.Pelicula.PROTAGONISTAS + " TEXT NOT NULL," +
                    Tablas.Pelicula.DIRECTOR + " TEXT NOT NULL," +
                    Tablas.Pelicula.CLASIFICACION + " TEXT NOT NULL," +
                    Tablas.Pelicula.PROYECCIONES + " TEXT NOT NULL)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    fun insert(pelicula: PeliModelo) {
        values.put(Tablas.Pelicula.NOMBRE_ORIGINAL, pelicula.nombre_original)
        values.put(Tablas.Pelicula.IMAGEN, pelicula.imagen)
        values.put(Tablas.Pelicula.DURACION, pelicula.duracion)
        values.put(Tablas.Pelicula.PROTAGONISTAS, pelicula.protagonistas)
        values.put(Tablas.Pelicula.DIRECTOR, pelicula.director)
        values.put(Tablas.Pelicula.CLASIFICACION, pelicula.clasificacion)
        values.put(Tablas.Pelicula.PROYECCIONES, pelicula.proyecciones)
        db.insert(Tablas.Pelicula.TABLE_NAME, null, values)
        Log.d("Pelicula AGREGADA:", values.toString())
    }

    fun getPeliculas(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val pelicula = PeliModelo()
                pelicula.nombre_original = result.getString(result.getColumnIndex(Tablas.Pelicula.NOMBRE_ORIGINAL))
                list.add(pelicula)
            } while (result.moveToNext())
        }

        result.close()
//        db.close()
        return list
    }

    fun getProyecciones(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val proyeccion = PeliModelo()
                proyeccion.proyecciones = result.getString(result.getColumnIndex(Tablas.Pelicula.PROYECCIONES))
                list.add(proyeccion)
            } while (result.moveToNext())
        }
        result.close()
//        db.close()
        return list
    }

    fun getImagen(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val imagen = PeliModelo()
                imagen.imagen = result.getString(result.getColumnIndex(Tablas.Pelicula.IMAGEN))
                list.add(imagen)
            } while (result.moveToNext())
        }
        result.close()
//        db.close()
        return list
    }

    fun getDirector(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val director = PeliModelo()
                director.director = result.getString(result.getColumnIndex(Tablas.Pelicula.DIRECTOR))
                list.add(director)
            } while (result.moveToNext())
        }
        result.close()
//        db.close()
        return list
    }

    fun getDuracion(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val duracion = PeliModelo()
                duracion.duracion = result.getString(result.getColumnIndex(Tablas.Pelicula.DURACION))
                list.add(duracion)
            } while (result.moveToNext())
        }
        result.close()
//        db.close()
        return list
    }

    fun getProtagonista(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val protagonista = PeliModelo()
                protagonista.protagonistas = result.getString(result.getColumnIndex(Tablas.Pelicula.PROTAGONISTAS))
                list.add(protagonista)
            } while (result.moveToNext())
        }
        result.close()
//        db.close()
        return list
    }

    fun getClasificacion(): MutableList<PeliModelo> {
        val list: MutableList<PeliModelo> = ArrayList()
        val query = "Select * from " + Tablas.Pelicula.TABLE_NAME
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                val clasificacion = PeliModelo()
                clasificacion.clasificacion = result.getString(result.getColumnIndex(Tablas.Pelicula.CLASIFICACION))
                list.add(clasificacion)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }




}