package com.example.tictactoe

import android.content.Context
import android.widget.ImageView
import android.widget.TextView

class Maquina(private var imagenesTablero: Array<Array<ImageView>>, private var imagenesFichas: Array<Int>,
              private var turnoPantalla: Array<TextView>, private var turnoFicha: ImageView,
              private var nivel: String, private var contexto: Context): Jugadores {

    private var jugadoresJuegoTexto = Jugadores.getJugadoresJuego()

    override fun movimientoJugador(casilla: String){
        val matrizTablero = Jugadores.getMatrizTablero()
        val fichas = Jugadores.getFichas()
        var turno = Jugadores.getTurno()
        val bandera = Jugadores.convertirBooleano(turno)
        var numeroMovimientos = Jugadores.getNumeroMovimientos()
        var filas: String
        var columnas: String

        when(nivel){
            "FACIL" -> {
                val ataque = JugadorMaquina.ataque
                ataque.aleatorio(matrizTablero)

                filas = (JugadorMaquina.getFilas())
                columnas = (JugadorMaquina.getColumnas())
            }
            "INTERMEDIO" -> {
                val ataque = JugadorMaquina.ataque

                ataque.revisarFilas(fichas, matrizTablero, bandera)
                filas = JugadorMaquina.getFilas()
                columnas = JugadorMaquina.getColumnas()

                if( (filas == "") && (columnas == "") ){
                    val defensa = JugadorMaquina.defensa
                    val banderaInvertida = Jugadores.convertirBooleano(!turno)

                    defensa.revisarFilas(fichas, matrizTablero, banderaInvertida)
                    filas = JugadorMaquina.getFilas()
                    columnas = JugadorMaquina.getColumnas()

                    if( (filas == "") && (columnas == "") ){
                        ataque.aleatorio(matrizTablero)
                        filas = JugadorMaquina.getFilas()
                        columnas = JugadorMaquina.getColumnas()
                    }
                }
            }
            else -> {
                val ataque = JugadorMaquina.ataque

                ataque.mejorJugada(fichas, matrizTablero, numeroMovimientos, turno, jugadoresJuegoTexto)

                filas = JugadorMaquina.getFilas()
                columnas = JugadorMaquina.getColumnas()
            }
        }

        imagenesTablero[filas.toInt()][columnas.toInt()].setImageResource(imagenesFichas[bandera])
        matrizTablero[filas.toInt()][columnas.toInt()] = fichas[bandera]
        numeroMovimientos += 1

        Jugadores.setMatrizTablero(matrizTablero)
        Jugadores.setNumeroMovimientos(numeroMovimientos)

        val ganador = Jugadores.getGanador()
        ganador.revisarGanador(fichas, matrizTablero, numeroMovimientos, bandera, jugadoresJuegoTexto)
        val resultado = ganador.resultado

        if(resultado != " "){
            ganador.declararGanador(resultado, contexto)
        }
        else{
            turno = !turno
            Jugadores.setTurno(turno)
            turnoJuego(turno)
            JugadorMaquina.setTurnoMaquina(false)
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
            JugadorMaquina.setTurnoMaquina(false)
            Jugadores.setJugar(true)
            val fichas = Jugadores.getFichas()

            if(fichas[0] == "X"){
                Jugadores.setTurno(false)
                turnoJuego(Jugadores.getTurno())

                if(jugadoresJuegoTexto[Jugadores.convertirBooleano(false)] == "MAQUINA"){
                    val jugadoresJuego = Jugadores.getJugadores()
                    JugadorMaquina.setTurnoMaquina(true)
                    return jugadoresJuego[Jugadores.convertirBooleano(false)].movimientoJugador("")
                }
            }
            else{
                Jugadores.setTurno(true)
                turnoJuego(Jugadores.getTurno())
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