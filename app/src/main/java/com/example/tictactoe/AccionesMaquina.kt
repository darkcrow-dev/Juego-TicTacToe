package com.example.tictactoe

import kotlin.math.max
import kotlin.math.min
import kotlin.random.Random
import kotlin.random.nextInt

class AccionesMaquina: JugadorMaquina {
    private var cambio = false

    fun aleatorio(matrizTablero: Array<Array<String>>) {
        val casilla = Random.nextInt(0..8)
        val filas = (casilla / 3)
        val columnas = casilla - (3 * filas)

        if (matrizTablero[filas][columnas] == " ") {
            JugadorMaquina.setFilas(filas.toString())
            JugadorMaquina.setColumnas(columnas.toString())
            return
        }

        aleatorio(matrizTablero)
    }

    fun revisarFilas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Boolean) {
        val bandera = Jugadores.convertirBooleano(turno)
        for(i in 0..2){
            if( ( (matrizTablero[i][0] == fichas[bandera]) && (matrizTablero[i][1] == fichas[bandera]) && (matrizTablero[i][2] == " ") ) ||
                ( (matrizTablero[i][0] == fichas[bandera]) && (matrizTablero[i][2] == fichas[bandera]) && (matrizTablero[i][1] == " ") ) ||
                ( (matrizTablero[i][1] == fichas[bandera]) && (matrizTablero[i][2] == fichas[bandera]) && (matrizTablero[i][0] == " ") ) ){
                for(j in 0..2){
                    if(matrizTablero[i][j] == " "){
                        JugadorMaquina.setFilas(i.toString())
                        JugadorMaquina.setColumnas(j.toString())
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
                        JugadorMaquina.setFilas(j.toString())
                        JugadorMaquina.setColumnas(i.toString())
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
                    JugadorMaquina.setFilas(i.toString())
                    JugadorMaquina.setColumnas(i.toString())
                    return
                }
            }
        }
        else if( ( (matrizTablero[0][2] == fichas[bandera]) && (matrizTablero[1][1] == fichas[bandera]) && (matrizTablero[2][0] == " ") ) ||
            ( (matrizTablero[0][2] == fichas[bandera]) && (matrizTablero[2][0] == fichas[bandera]) && (matrizTablero[1][1] == " ") ) ||
            ( (matrizTablero[1][1] == fichas[bandera]) && (matrizTablero[2][0] == fichas[bandera]) && (matrizTablero[0][2] == " ") ) ){
            for(i in 0..2){
                if(matrizTablero[i][2 - i] == " "){
                    JugadorMaquina.setFilas(i.toString())
                    JugadorMaquina.setColumnas( (2 - i).toString() )
                    return
                }
            }
        }

        if( (cambio) && ( (JugadorMaquina.getFilas() == "") && (JugadorMaquina.getColumnas() == "") ) ){
            cambio = false
            return aleatorio(matrizTablero)
        }

        cambio = true
        val cambiarTurno = !turno
        JugadorMaquina.setFilas("")
        JugadorMaquina.setColumnas("")

        return revisarFilas(fichas, matrizTablero, cambiarTurno)
    }

    fun mejorJugada(fichas: Array<String>, matrizTablero: Array<Array<String>>, numeroMovimientos: Int, turno: Boolean, jugadores: Array<String>){
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
                        JugadorMaquina.setFilas(i.toString())
                        JugadorMaquina.setColumnas(j.toString())
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