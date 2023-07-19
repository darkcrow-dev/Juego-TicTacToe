package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback

class Fichas : AppCompatActivity() {
    private lateinit var botonFichaX: Button
    private lateinit var botonFichaO: Button

    private var modalidad: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fichas)

        botonFichaX = findViewById(R.id.botonFichaX)
        botonFichaO = findViewById(R.id.botonFichaO)

        modalidad = intent.getStringExtra("modalidad")

        botonFichaX.setOnClickListener {
            val fichas = arrayOf("X", "O")
            jugar(fichas)


        }

        botonFichaO.setOnClickListener {
            val fichas = arrayOf("O", "X")
            jugar(fichas)
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                val actividad = Intent(this@Fichas, Modalidad::class.java)
                actividad.putExtra("modalidad", modalidad)
                startActivity(actividad)
                finish()
            }

        })
    }

    private fun jugar(arrayFichas: Array<String>){
        val actividad = Intent(this, MainActivity::class.java)
        actividad.putExtra("modalidad", modalidad)
        actividad.putExtra("fichas", arrayFichas)
        startActivity(actividad)
        finish()
    }
}