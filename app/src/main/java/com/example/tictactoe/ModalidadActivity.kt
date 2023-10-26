package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.OnBackPressedCallback

class ModalidadActivity : AppCompatActivity() {
    private lateinit var botonModo1: Button
    private lateinit var botonModo2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modalidad)

        botonModo1 = findViewById(R.id.botonModo1)
        botonModo2 = findViewById(R.id.botonModo2)

        botonModo1.setOnClickListener {
            Jugadores.setModalidad("1 JUGADOR")
            startActivity(Intent(this, DificultadActivity::class.java))
            finish()
        }

        botonModo2.setOnClickListener {
            Jugadores.setModalidad("2 JUGADORES")
            startActivity(Intent(this, FichasActivity::class.java))
            finish()
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                startActivity(Intent(this@ModalidadActivity, MenuActivity::class.java))
                finish()
            }

        })
    }
}