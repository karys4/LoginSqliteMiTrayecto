package com.mtw.kary.loginsqlite

import android.Manifest
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginMain : AppCompatActivity() {
    protected lateinit var e1: EditText
    protected lateinit var e2: EditText
    protected lateinit var b1: Button
    protected lateinit var db: DataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)
        db = DataBaseHelper(this)
        e1 = findViewById<View>(R.id.editText) as EditText
        e1 = findViewById<View>(R.id.editText2) as EditText

        //Botón ingresar en activity_login.xml
        b1 = findViewById<View>(R.id.button) as Button



        b1.setOnClickListener {
            val email = e1.text.toString()
            val password = e2!!.text.toString()
            val CheckmailPassword = db.emailPassword(email, password)

            if (CheckmailPassword == true)
                Toast.makeText(applicationContext, "Acceso a Mi Trayecto, exitoso", Toast.LENGTH_SHORT).show()
            else
                Toast.makeText(applicationContext, "Verifica tus credenciales de acceso", Toast.LENGTH_SHORT).show()
        }

    }


}
