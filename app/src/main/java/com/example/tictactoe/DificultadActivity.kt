package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import kotlin.random.Random
import kotlin.random.nextInt

class DificultadActivity : AppCompatActivity() {
    private lateinit var botonFacil: Button
    private lateinit var botonIntermedio: Button
    private lateinit var botonDificil: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dificultad)

        botonFacil = findViewById(R.id.botonFacil)
        botonIntermedio = findViewById(R.id.botonIntermedio)
        botonDificil = findViewById(R.id.botonDificil)

        botonFacil.setOnClickListener {
            jugar("FACIL")
        }

        botonIntermedio.setOnClickListener {
            jugar("INTERMEDIO")
        }

        botonDificil.setOnClickListener {
            jugar("DIFICIL")
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(Intent(this@DificultadActivity, ModalidadActivity::class.java))
                finish()
            }

        })
    }

    private fun jugar(string: String){
        val fichas = seleccionarTurno()
        Jugadores.setNivel(string)
        Jugadores.setFichas(fichas)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun seleccionarTurno(): Array<String>{
        var fichas = arrayOf("X", "O")

        val aleatorio = Random.nextInt(0..11)
        if(aleatorio >= 6){
            fichas = arrayOf("O", "X")
        }

        return fichas
    }
}