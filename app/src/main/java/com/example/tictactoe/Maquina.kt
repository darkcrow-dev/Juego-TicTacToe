package com.example.tictactoe

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
import kotlin.random.nextInt

class Maquina(private var imagenesTablero: Array<Array<ImageView>>, private var imagenesFichas: Array<Int>,
              private var turnoPantalla: Array<TextView>, private var turnoFicha: ImageView,
              private var nivel: String, private var contexto: Context): Jugadores {

    private var jugadoresJuegoTexto = Jugadores.getJugadoresJuego()
    private var movimientoCasilla = arrayOf("", "")
    private var cambio = false

    override fun movimientoJugador(casilla: String){
        val matrizTablero = Jugadores.getMatrizTablero()
        val fichas = Jugadores.getFichas()
        var turno = Jugadores.getTurno()
        val bandera = Jugadores.convertirBooleano(turno)
        var numeroMovimientos = Jugadores.getNumeroMovimientos()

        when(nivel){
            "FACIL" -> {
                ataqueDebil(matrizTablero)
            }
            "INTERMEDIO" -> {
                ataqueIntermedio(fichas, matrizTablero, turno)
            }
            else -> {
                ataqueFuerte(fichas, matrizTablero, numeroMovimientos, turno, jugadoresJuegoTexto)
            }
        }

        val filas = movimientoCasilla[0]
        val columnas = movimientoCasilla[1]

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
        }
    }

    private fun turnoJuego(turno: Boolean){
        val bandera = Jugadores.convertirBooleano(turno)
        turnoPantalla[1].text = jugadoresJuegoTexto[bandera]
        turnoFicha.setImageResource(imagenesFichas[bandera])
        Jugadores.setTurnoMaquina(false)
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
            Jugadores.setTurnoMaquina(false)
            Jugadores.setJugar(true)
            val fichas = Jugadores.getFichas()

            if(fichas[0] == "X"){
                Jugadores.setTurno(false)
                turnoJuego(Jugadores.getTurno())

                if(jugadoresJuegoTexto[Jugadores.convertirBooleano(false)] == "MAQUINA"){
                    val jugadoresJuego = Jugadores.getJugadores()
                    Jugadores.setTurnoMaquina(true)
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

    private fun ataqueDebil(matrizTablero: Array<Array<String>>){
        return aleatorio(matrizTablero)
    }

    private fun ataqueIntermedio(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Boolean){
        return revisarFilas(fichas, matrizTablero, turno)
    }

    private fun ataqueFuerte(fichas: Array<String>, matrizTablero: Array<Array<String>>, numeroMovimientos: Int, turno: Boolean, jugadores: Array<String>){
        return mejorJugada(fichas, matrizTablero, numeroMovimientos, turno, jugadores)
    }

    private fun aleatorio(matrizTablero: Array<Array<String>>) {
        val casilla = Random.nextInt(0..8)
        val filas = (casilla / 3)
        val columnas = casilla - (3 * filas)

        if (matrizTablero[filas][columnas] == " ") {
            movimientoCasilla = arrayOf(filas.toString(), columnas.toString())
            return
        }

        aleatorio(matrizTablero)
    }

    private fun revisarFilas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Boolean) {
        val bandera = Jugadores.convertirBooleano(turno)
        for(i in 0..2){
            if( ( (matrizTablero[i][0] == fichas[bandera]) && (matrizTablero[i][1] == fichas[bandera]) && (matrizTablero[i][2] == " ") ) ||
                ( (matrizTablero[i][0] == fichas[bandera]) && (matrizTablero[i][2] == fichas[bandera]) && (matrizTablero[i][1] == " ") ) ||
                ( (matrizTablero[i][1] == fichas[bandera]) && (matrizTablero[i][2] == fichas[bandera]) && (matrizTablero[i][0] == " ") ) ){
                for(j in 0..2){
                    if(matrizTablero[i][j] == " "){
                        movimientoCasilla = arrayOf(i.toString(), j.toString())
                        return
                    }
                }
            }
        }

        return revisarColumnas(fichas, matrizTablero, turno)
    }

    private fun revisarColumnas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Boolean){
        val bandera = Jugadores.convertirBooleano(turno)
        for(i in 0..2){
            if( ( (matrizTablero[0][i] == fichas[bandera]) && (matrizTablero[1][i] == fichas[bandera]) && (matrizTablero[2][i] == " ") ) ||
                ( (matrizTablero[0][i] == fichas[bandera]) && (matrizTablero[2][i] == fichas[bandera]) && (matrizTablero[1][i] == " ") ) ||
                ( (matrizTablero[1][i] == fichas[bandera]) && (matrizTablero[2][i] == fichas[bandera]) && (matrizTablero[0][i] == " ") ) ){
                for(j in 0..2){
                    if(matrizTablero[j][i] == " "){
                        movimientoCasilla = arrayOf(j.toString(), i.toString())
                        return
                    }
                }
            }
        }

        return revisarDiagonales(fichas, matrizTablero, turno)
    }

    private fun revisarDiagonales(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Boolean){
        val bandera = Jugadores.convertirBooleano(turno)
        if( ( (matrizTablero[0][0] == fichas[bandera]) && (matrizTablero[1][1] == fichas[bandera]) && (matrizTablero[2][2] == " ") ) ||
            ( (matrizTablero[0][0] == fichas[bandera]) && (matrizTablero[2][2] == fichas[bandera]) && (matrizTablero[1][1] == " ") ) ||
            ( (matrizTablero[1][1] == fichas[bandera]) && (matrizTablero[2][2] == fichas[bandera]) && (matrizTablero[0][0] == " ") ) ){
            for(i in 0..2){
                if(matrizTablero[i][i] == " "){
                    movimientoCasilla = arrayOf(i.toString(), i.toString())
                    return
                }
            }
        }
        else if( ( (matrizTablero[0][2] == fichas[bandera]) && (matrizTablero[1][1] == fichas[bandera]) && (matrizTablero[2][0] == " ") ) ||
            ( (matrizTablero[0][2] == fichas[bandera]) && (matrizTablero[2][0] == fichas[bandera]) && (matrizTablero[1][1] == " ") ) ||
            ( (matrizTablero[1][1] == fichas[bandera]) && (matrizTablero[2][0] == fichas[bandera]) && (matrizTablero[0][2] == " ") ) ){
            for(i in 0..2){
                if(matrizTablero[i][2 - i] == " "){
                    movimientoCasilla = arrayOf(i.toString(), (2 - i).toString())
                    return
                }
            }
        }

        if( (cambio) && ( (movimientoCasilla[0] == "") && (movimientoCasilla[1] == "") ) ){
            cambio = false
            return aleatorio(matrizTablero)
        }

        cambio = true
        val cambiarTurno = !turno
        movimientoCasilla = arrayOf("", "")

        return revisarFilas(fichas, matrizTablero, cambiarTurno)
    }

    private fun mejorJugada(fichas: Array<String>, matrizTablero: Array<Array<String>>, numeroMovimientos: Int, turno: Boolean, jugadores: Array<String>){
        var mejorPuntaje = -10000
        for(i in 0..2){
            for(j in 0..2){
                if(matrizTablero[i][j] == " "){
                    matrizTablero[i][j] = fichas[Jugadores.convertirBooleano(turno)]
                    var numeroMovimientosIA = numeroMovimientos + 1
                    val puntaje = minimax(fichas, matrizTablero, numeroMovimientosIA, turno, -10000, 10000, jugadores)
                    matrizTablero[i][j] = " "
                    numeroMovimientosIA -= 1

                    if(puntaje > mejorPuntaje){
                        mejorPuntaje = puntaje
                        movimientoCasilla = arrayOf(i.toString(), j.toString())
                    }
                }
            }
        }
        return
    }

    private fun minimax(fichas: Array<String>, matrizTablero: Array<Array<String>>, numeroMovimientos: Int, turno: Boolean, alpha: Int, beta: Int, jugadores: Array<String>): Int{
        val ganador = Jugadores.getGanador()
        val bandera = Jugadores.convertirBooleano(turno)
        val banderaInvertida = Jugadores.convertirBooleano(!turno)
        ganador.revisarGanador(fichas, matrizTablero, numeroMovimientos, bandera, jugadores)
        val resultado = ganador.resultado

        var mejorPuntaje: Int

        when (resultado) {
            jugadores[bandera] -> {
                mejorPuntaje = 10*(banderaInvertida - bandera)
                return mejorPuntaje
            }
            jugadores[banderaInvertida] -> {
                mejorPuntaje = 10*(bandera - banderaInvertida)
                return mejorPuntaje
            }
            "EMPATE" -> {
                mejorPuntaje = 0
                return mejorPuntaje
            }
        }

        if(turno){
            mejorPuntaje = -10000
            for(i in 0..2){
                for(j in 0..2){
                    if(matrizTablero[i][j] == " "){
                        matrizTablero[i][j] = fichas[banderaInvertida]
                        var numeroMovimientosIA = numeroMovimientos + 1
                        val puntaje = minimax(fichas, matrizTablero, numeroMovimientosIA, !turno, alpha, beta, jugadores)
                        matrizTablero[i][j] = " "
                        numeroMovimientosIA -= 1
                        mejorPuntaje = max(puntaje, mejorPuntaje)
                        val alphaIA = max(alpha, mejorPuntaje)

                        if(beta <= alphaIA){
                            break
                        }
                    }
                }
            }

            return mejorPuntaje
        }
        else{
            mejorPuntaje = 10000
            for(i in 0..2){
                for(j in 0..2){
                    if(matrizTablero[i][j] == " "){
                        matrizTablero[i][j] = fichas[banderaInvertida]
                        var numeroMovimientosIA = numeroMovimientos + 1
                        val puntaje = minimax(fichas, matrizTablero, numeroMovimientosIA, !turno, alpha, beta, jugadores)
                        matrizTablero[i][j] = " "
                        numeroMovimientosIA -= 1
                        mejorPuntaje = min(puntaje, mejorPuntaje)
                        val betaIA = min(beta, mejorPuntaje)

                        if(betaIA <= alpha){
                            break
                        }
                    }
                }
            }

            return mejorPuntaje
        }
    }
}