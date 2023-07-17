package com.example.tictactoe

import android.content.Context
import android.widget.ImageView
import android.widget.TextView

class Jugador1(private var imagenesTablero: Array<Array<ImageView>>, private var imagenesFichas: Array<Int>,
               private var turnoPantalla: Array<TextView>, private var turnoFicha: ImageView,
               private var contexto: Context): Jugadores {

    private var jugadoresJuegoTexto = Jugadores.getJugadoresJuego()

    override fun movimientoJugador(casilla: String){
        val filas = (casilla.toInt()/3)
        val columnas = casilla.toInt() - (3*filas)

        val matrizTablero = Jugadores.getMatrizTablero()
        if(matrizTablero[filas][columnas] == " "){
            val bandera = Jugadores.convertirBooleano(Jugadores.getTurno())
            imagenesTablero[filas][columnas].setImageResource(imagenesFichas[bandera])
            val fichas = Jugadores.getFichas()
            matrizTablero[filas][columnas] = fichas[bandera]
            val numeroMovimientos = Jugadores.getNumeroMovimientos() + 1

            Jugadores.setMatrizTablero(matrizTablero)
            Jugadores.setNumeroMovimientos(numeroMovimientos)

            val ganador = Jugadores.getGanador()
            ganador.revisarGanador(fichas, matrizTablero, numeroMovimientos, bandera, jugadoresJuegoTexto)
            val resultado = ganador.resultado

            if(resultado != " "){
                ganador.declararGanador(resultado, contexto)
            }
            else{
                val turno = !Jugadores.getTurno()
                Jugadores.setTurno(turno)
                turnoJuego(turno)

                if(jugadoresJuegoTexto[Jugadores.convertirBooleano(turno)] == "MAQUINA"){
                    val jugadoresJuego = Jugadores.getJugadores()
                    JugadorMaquina.setTurnoMaquina(true)
                    jugadoresJuego[Jugadores.convertirBooleano(turno)].movimientoJugador("")
                }
            }
        }
    }

    private fun turnoJuego(turno: Boolean){
        val bandera = Jugadores.convertirBooleano(turno)
        turnoPantalla[1].text = jugadoresJuegoTexto[bandera]
        turnoFicha.setImageResource(imagenesFichas[bandera])
    }

    override fun terminarJuego(){
        Jugadores.setJugar(false)
        turnoPantalla[0].text = "JUEGO"
        turnoPantalla[1].text = "TERMINADO"
        turnoFicha.setImageResource(R.color.transparent)
    }

    override fun inicializarTablero(contador: Int){
        if(contador > 8){
            Jugadores.setNumeroMovimientos(0)
            Jugadores.setTurno(false)
            turnoJuego(Jugadores.getTurno())
            JugadorMaquina.setTurnoMaquina(false)
            Jugadores.setJugar(true)

            if(jugadoresJuegoTexto[Jugadores.convertirBooleano(false)] == "MAQUINA"){
                val jugadoresJuego = Jugadores.getJugadores()
                JugadorMaquina.setTurnoMaquina(true)
                return jugadoresJuego[Jugadores.convertirBooleano(false)].movimientoJugador("")
            }

            return
        }

        val filas = (contador/3)
        val columnas = contador - (3*filas)
        val matrizTablero = Jugadores.getMatrizTablero()

        matrizTablero[filas][columnas] = " "
        Jugadores.setMatrizTablero(matrizTablero)
        imagenesTablero[filas][columnas].setImageResource(R.color.transparent)
        inicializarTablero(contador + 1)
    }
}