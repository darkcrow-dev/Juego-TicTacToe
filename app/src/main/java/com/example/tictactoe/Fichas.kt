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
            val jugadores = arrayOf("JUGADOR 1", "JUGADOR 2")
            jugar(jugadores)


        }

        botonFichaO.setOnClickListener {
            val jugadores = arrayOf("JUGADOR 2", "JUGADOR 1")
            jugar(jugadores)
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

    private fun jugar(arrayJugadores: Array<String>){
        val actividad = Intent(this, MainActivity::class.java)
        actividad.putExtra("modalidad", modalidad)
        actividad.putExtra("jugadores", arrayJugadores)
        startActivity(actividad)
        finish()
    }
}