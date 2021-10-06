package com.example.app.Database.Cliente

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.app.Database.Tablas

class ClientesDB (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
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
        db!!.execSQL("CREATE TABLE "+ Tablas.Cliente.TABLE_NAME +" (" +
                Tablas.Cliente.NOMBRE + " TEXT NOT NULL," +
                Tablas.Cliente.CEDULA + " INTEGER PRIMARY KEY," +
                Tablas.Cliente.EDAD + " TEXT NOT NULL," +
                Tablas.Cliente.FECHA_NAC + " DATE," +
                Tablas.Cliente.USUARIO + " TEXT NOT NULL," +
                Tablas.Cliente.CONTRASENA + " TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(cliente: List<ClienteMod>){
        values.put(Tablas.Cliente.NOMBRE, cliente[0].getNombre())
        values.put(Tablas.Cliente.CEDULA, cliente[0].getCedula())
        values.put(Tablas.Cliente.EDAD, cliente[0].getEdad())
        values.put(Tablas.Cliente.FECHA_NAC, cliente[0].getFecha_nac())
        values.put(Tablas.Cliente.USUARIO, cliente[0].getUsuario())
        values.put(Tablas.Cliente.CONTRASENA, cliente[0].getContrasena())
    }
}