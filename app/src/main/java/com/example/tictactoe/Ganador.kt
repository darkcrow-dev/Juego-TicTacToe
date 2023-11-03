package com.example.tictactoe

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.Button
import android.widget.TextView

class Ganador: Jugadores{
    var resultado = ""

    fun revisarGanador(fichas: Array<String>, matrizTablero: Array<Array<String>>, numeroMovimientos: Int, turno: Int, jugadores: Array<String>): String{
        resultado = revisarFilas(fichas, matrizTablero, turno, jugadores)

        if( (resultado == " ") && (numeroMovimientos == 9) ){
            resultado = "EMPATE"
        }

        return resultado
    }

    private fun revisarFilas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Int, jugadores: Array<String>): String{
        for(i in 0..2){
            if( (matrizTablero[i][0] == fichas[turno]) && (matrizTablero[i][1] == fichas[turno]) && (matrizTablero[i][2] == fichas[turno]) ){
                resultado = jugadores[turno]
                return resultado
            }
        }

        return revisarColumnas(fichas, matrizTablero, turno, jugadores)
    }

    private fun revisarColumnas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Int, jugadores: Array<String>): String{
        for(i in 0..2){
            if( (matrizTablero[0][i] == fichas[turno]) && (matrizTablero[1][i] == fichas[turno]) && (matrizTablero[2][i] == fichas[turno]) ){
                resultado = jugadores[turno]
                return resultado
            }
        }

        return revisarDiagonales(fichas, matrizTablero, turno, jugadores)
    }

    private fun revisarDiagonales(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Int, jugadores: Array<String>): String{
        if( ( (matrizTablero[0][0] == fichas[turno]) && (matrizTablero[1][1] == fichas[turno]) && (matrizTablero[2][2] == fichas[turno]) ) ||
            ( (matrizTablero[2][0] == fichas[turno]) && (matrizTablero[1][1] == fichas[turno]) && (matrizTablero[0][2] == fichas[turno]) ) ){
            resultado = jugadores[turno]
            return resultado
        }

        resultado = " "
        return resultado
    }

    fun declararGanador(string: String, contexto: Context){
        val dialogo = Dialog(contexto)
        dialogo.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialogo.setCancelable(false)
        dialogo.setCanceledOnTouchOutside(false)
        dialogo.setContentView(R.layout.winner_dialog)
        dialogo.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val botonAfirmativo: Button = dialogo.findViewById(R.id.botonAfirmativo)
        val botonNegativo: Button = dialogo.findViewById(R.id.botonNegativo)
        val mensajeGanador: TextView = dialogo.findViewById(R.id.mensajeGanador)
        val mensajeDialogo: TextView = dialogo.findViewById(R.id.mensajeDialogo)
        val tituloDialogo: TextView = dialogo.findViewById(R.id.tituloDialogo)

        if(string == "EMPATE"){
            tituloDialogo.text = "EMPATE"
            mensajeDialogo.text = "Juego"
            mensajeGanador.text = "EMPATADO"
        }
        else{
            tituloDialogo.text = "GANADOR"
            mensajeDialogo.text = "El ganador es:"
            mensajeGanador.text = string
        }
        val jugadoresJuego = Jugadores.getJugadores()
        val bandera = Jugadores.convertirBooleano(Jugadores.getTurno())

        botonAfirmativo.setOnClickListener {
            jugadoresJuego[bandera].inicializarTablero()
            dialogo.dismiss()
        }

        botonNegativo.setOnClickListener {
            jugadoresJuego[bandera].terminarJuego()
            dialogo.dismiss()
        }

        dialogo.show()
    }
}