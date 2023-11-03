package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback

class MainActivity : AppCompatActivity() {
    private lateinit var imagenesTablero: Array<Array<ImageView>>
    private lateinit var turnoPantalla: Array<TextView>
    private lateinit var imagenesFichas: Array<Int>

    private lateinit var fichaTurno: ImageView
    private lateinit var turnoJugador: TextView
    private lateinit var turnoTexto: TextView

    private lateinit var imagenCasilla1: ImageView
    private lateinit var imagenCasilla2: ImageView
    private lateinit var imagenCasilla3: ImageView
    private lateinit var imagenCasilla4: ImageView
    private lateinit var imagenCasilla5: ImageView
    private lateinit var imagenCasilla6: ImageView
    private lateinit var imagenCasilla7: ImageView
    private lateinit var imagenCasilla8: ImageView
    private lateinit var imagenCasilla9: ImageView

    private lateinit var jugadores: Array<Jugadores>
    private lateinit var jugadoresJuegoTexto: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fichaTurno = findViewById(R.id.fichaTurno)
        turnoTexto = findViewById(R.id.turnoTexto)
        turnoJugador = findViewById(R.id.turnoJugador)

        imagenCasilla1 = findViewById(R.id.imagenCasilla1)
        imagenCasilla2 = findViewById(R.id.imagenCasilla2)
        imagenCasilla3 = findViewById(R.id.imagenCasilla3)
        imagenCasilla4 = findViewById(R.id.imagenCasilla4)
        imagenCasilla5 = findViewById(R.id.imagenCasilla5)
        imagenCasilla6 = findViewById(R.id.imagenCasilla6)
        imagenCasilla7 = findViewById(R.id.imagenCasilla7)
        imagenCasilla8 = findViewById(R.id.imagenCasilla8)
        imagenCasilla9 = findViewById(R.id.imagenCasilla9)

        imagenesTablero = arrayOf(arrayOf(imagenCasilla1, imagenCasilla2, imagenCasilla3),
            arrayOf(imagenCasilla4, imagenCasilla5, imagenCasilla6),
            arrayOf(imagenCasilla7, imagenCasilla8, imagenCasilla9))

        turnoPantalla = arrayOf(turnoTexto, turnoJugador)
        val fichasTexto = Jugadores.getFichas()
        val modalidad = Jugadores.getModalidad()

        if(fichasTexto[0] == "X"){
            imagenesFichas = arrayOf(R.drawable.ficha_x, R.drawable.ficha_o)
            Jugadores.setTurno(false)
        }
        else{
            imagenesFichas = arrayOf(R.drawable.ficha_o, R.drawable.ficha_x)
            Jugadores.setTurno(true)
        }

        if(modalidad == "2 JUGADORES") {
            jugadoresJuegoTexto = arrayOf("JUGADOR 1", "JUGADOR 2")

            Jugadores.setJugadoresJuego(jugadoresJuegoTexto)

            val jugador1 = Jugador1(imagenesTablero, imagenesFichas, turnoPantalla, fichaTurno, this)
            val jugador2 = Jugador2(imagenesTablero, imagenesFichas, turnoPantalla, fichaTurno, this)

            jugadores = arrayOf(jugador1, jugador2)
        }
        else{
            val nivel = Jugadores.getNivel()
            jugadoresJuegoTexto = arrayOf("MAQUINA", "JUGADOR 1")

            Jugadores.setJugadoresJuego(jugadoresJuegoTexto)

            val jugador1 = Jugador1(imagenesTablero, imagenesFichas, turnoPantalla, fichaTurno, this)
            val maquina = Maquina(imagenesTablero, imagenesFichas, turnoPantalla, fichaTurno, nivel, this)

            jugadores = arrayOf(maquina, jugador1)
        }

        Jugadores.setJugadores(jugadores)
        var bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
        jugadores[bandera].inicializarTablero()

        imagenCasilla1.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("0")
            }
        }

        imagenCasilla2.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("1")
            }
        }

        imagenCasilla3.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("2")
            }
        }

        imagenCasilla4.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("3")
            }
        }

        imagenCasilla5.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("4")
            }
        }

        imagenCasilla6.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("5")
            }
        }

        imagenCasilla7.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("6")
            }
        }

        imagenCasilla8.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("7")
            }
        }

        imagenCasilla9.setOnClickListener {
            if(Jugadores.getJugar() && !Jugadores.getTurnoMaquina()){
                bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
                jugadores[bandera].movimientoJugador("8")
            }
        }

        onBackPressedDispatcher.addCallback(this, object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                cerrarActividad(modalidad)
            }
        })
    }

    private fun cerrarActividad(modalidad: String){
        var actividad = Intent(this@MainActivity, DificultadActivity::class.java)
        if(modalidad == "2 JUGADORES"){
            actividad = Intent(this@MainActivity, FichasActivity::class.java)
        }

        startActivity(actividad)
        finish()
    }
}