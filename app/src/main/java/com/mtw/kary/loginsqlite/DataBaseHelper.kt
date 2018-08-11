package com.mtw.kary.loginsqlite

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context) : SQLiteOpenHelper(context, "Login.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("Create table usuario (email text primary key, password text)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists usuario")
    }

    //Se inserta la información en la Base de Datos

    fun insert(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email", email)
        contentValues.put("password", password)

        val ins = db.insert("usuario", null, contentValues)

        /* return if (ins == -1)
            false
        else
            true
    }*/

        //Verifica si el email existe
        fun checkmail(email: String): Boolean? {
            val db = this.readableDatabase
            val cursor = db.rawQuery("Select * from usuario where email=?", arrayOf(email))

            return if (cursor.count > 0)
                false
            else
                true
        }

        //Verifica  el correo y la contraseña para poder hacer login
        fun emailPassword(email: String, password: String): Boolean? {
            val db = this.readableDatabase
            val cursor = db.rawQuery("select * from usuario where email=? and password =?", arrayOf(email, password))
            return if (cursor.count > 0)
                true
            else
                false
        }
        return true
    }
}
