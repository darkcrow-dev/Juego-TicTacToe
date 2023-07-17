package com.example.tictactoe

class Defensa: AccionesMaquina {
    fun revisarFilas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Int){
        for(i in 0..2){
            if( ( (matrizTablero[i][0] == fichas[turno]) && (matrizTablero[i][1] == fichas[turno]) && (matrizTablero[i][2] == " ") ) ||
                ( (matrizTablero[i][0] == fichas[turno]) && (matrizTablero[i][2] == fichas[turno]) && (matrizTablero[i][1] == " ") ) ||
                ( (matrizTablero[i][1] == fichas[turno]) && (matrizTablero[i][2] == fichas[turno]) && (matrizTablero[i][0] == " ") ) ){
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

    private fun revisarColumnas(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Int){
        for(i in 0..2){
            if( ( (matrizTablero[0][i] == fichas[turno]) && (matrizTablero[1][i] == fichas[turno]) && (matrizTablero[2][i] == " ") ) ||
                ( (matrizTablero[0][i] == fichas[turno]) && (matrizTablero[2][i] == fichas[turno]) && (matrizTablero[1][i] == " ") ) ||
                ( (matrizTablero[1][i] == fichas[turno]) && (matrizTablero[2][i] == fichas[turno]) && (matrizTablero[0][i] == " ") ) ){
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

    private fun revisarDiagonales(fichas: Array<String>, matrizTablero: Array<Array<String>>, turno: Int){
        if( ( (matrizTablero[0][0] == fichas[turno]) && (matrizTablero[1][1] == fichas[turno]) && (matrizTablero[2][2] == " ") ) ||
            ( (matrizTablero[0][0] == fichas[turno]) && (matrizTablero[2][2] == fichas[turno]) && (matrizTablero[1][1] == " ") ) ||
            ( (matrizTablero[1][1] == fichas[turno]) && (matrizTablero[2][2] == fichas[turno]) && (matrizTablero[0][0] == " ") ) ){
            for(i in 0..2){
                if(matrizTablero[i][i] == " "){
                    JugadorMaquina.setFilas(i.toString())
                    JugadorMaquina.setColumnas(i.toString())
                    return
                }
            }
        }
        else if( ( (matrizTablero[0][2] == fichas[turno]) && (matrizTablero[1][1] == fichas[turno]) && (matrizTablero[2][0] == " ") ) ||
            ( (matrizTablero[0][2] == fichas[turno]) && (matrizTablero[2][0] == fichas[turno]) && (matrizTablero[1][1] == " ") ) ||
            ( (matrizTablero[1][1] == fichas[turno]) && (matrizTablero[2][0] == fichas[turno]) && (matrizTablero[0][2] == " ") ) ){
            for(i in 0..2){
                if(matrizTablero[i][2 - i] == " "){
                    JugadorMaquina.setFilas(i.toString())
                    JugadorMaquina.setColumnas( (2 - i).toString() )
                    return
                }
            }
        }

        JugadorMaquina.setFilas("")
        JugadorMaquina.setColumnas("")
    }
}