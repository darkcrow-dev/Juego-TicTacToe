package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback

class FichasActivity : AppCompatActivity() {
    private lateinit var botonFichaX: Button
    private lateinit var botonFichaO: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fichas)

        botonFichaX = findViewById(R.id.botonFichaX)
        botonFichaO = findViewById(R.id.botonFichaO)

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
                startActivity(Intent(this@FichasActivity, ModalidadActivity::class.java))
                finish()
            }

        })
    }

    private fun jugar(arrayFichas: Array<String>){
        Jugadores.setFichas(arrayFichas)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}