package com.mtw.kary.loginsqlite

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    protected lateinit var db: DataBaseHelper
    protected lateinit var e1: EditText
    protected lateinit var e2: EditText
    protected lateinit var e3: EditText
    protected lateinit var b1: Button
    protected lateinit var b2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = DataBaseHelper(this)

        e1 = findViewById<View>(R.id.txtEmail) as EditText
        e2 = findViewById<View>(R.id.txtPass) as EditText
        e3 = findViewById<View>(R.id.txtCpass) as EditText
        b1 = findViewById<View>(R.id.btnRegister) as Button
        b2 = findViewById<View>(R.id.btn2) as Button

        b2!!.setOnClickListener {
            val i = Intent(this@MainActivity, LoginMain::class.java)
            startActivity(i)
        }

        b1.setOnClickListener {
            val s1 = e1.text.toString()
            val s2 = e2.text.toString()
            val s3 = e3.text.toString()

            //Verifica que los 3 campos contengan información
            if (s1 == "" || s2 == "" || s2 == "") {
                Toast.makeText(applicationContext, "Todos los campos deben estar llenos", Toast.LENGTH_SHORT).show()
            } else {
                if (s2 == s3) {
                    val chekmail = db.checkmail(s1)
                    if (chekmail == true) {
                        val insert = db.insert(s1, s2)
                        if (insert == true) {
                            Toast.makeText(applicationContext, "Se agregó el usuario exitosamente", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(applicationContext, "No se agregó usuario", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    }
}
