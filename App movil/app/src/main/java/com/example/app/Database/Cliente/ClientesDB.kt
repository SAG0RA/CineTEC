package com.example.app.Database.Cliente

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.app.Database.Tablas

class ClientesDB (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    private val db: SQLiteDatabase
    private val values: ContentValues

    companion object{
        val DATABASE_VERSION = 1
        val DATABASE_NAME = "Clientes"
    }

    init{
        db = this.writableDatabase
        values = ContentValues()
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE "+ Tablas.Cliente.TABLE_NAME +" (" +
                Tablas.Cliente.NOMBRE + " TEXT NOT NULL," +
                Tablas.Cliente.APELLIDO + " TEXT NOT NULL," +
                Tablas.Cliente.CEDULA + " INTEGER PRIMARY KEY," +
                Tablas.Cliente.EDAD + " TEXT NOT NULL," +
                Tablas.Cliente.FECHA_NAC + " TEXT NOT NULL," +
                Tablas.Cliente.USUARIO + " TEXT NOT NULL," +
                Tablas.Cliente.CONTRASENA + " TEXT NOT NULL)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insert(cliente: ClienteModelo){
        values.put(Tablas.Cliente.NOMBRE, cliente.nombre)
        values.put(Tablas.Cliente.APELLIDO, cliente.apellido)
        values.put(Tablas.Cliente.CEDULA, cliente.cedula)
        values.put(Tablas.Cliente.EDAD, cliente.edad)
        values.put(Tablas.Cliente.FECHA_NAC, cliente.fecha_nac)
        values.put(Tablas.Cliente.USUARIO, cliente.usuario)
        values.put(Tablas.Cliente.CONTRASENA, cliente.contrasena)
        db.insert(Tablas.Cliente.TABLE_NAME,null,values)
        Log.d("Usuario AGREGADO:",values.toString())
    }

    fun getData(): MutableList<ClienteModelo>{
        val list: MutableList<ClienteModelo> = ArrayList()
        val query = "Select * from " + Tablas.Cliente.TABLE_NAME
        val result = db.rawQuery(query,null)
        if(result.moveToFirst()){
            do{
                val cliente = ClienteModelo()
                cliente.usuario = result.getString(result.getColumnIndex(Tablas.Cliente.USUARIO))
                cliente.contrasena = result.getString(result.getColumnIndex(Tablas.Cliente.CONTRASENA))
                list.add(cliente)
            }while(result.moveToNext())
        }

        result.close()
        db.close()

        return list
    }
}