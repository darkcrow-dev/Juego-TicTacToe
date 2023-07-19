package com.example.tictactoe

interface JugadorMaquina: Jugadores {

    companion object{
        var acciones = AccionesMaquina()

        private var turnoMaquina = false
        private var filas = ""
        private var columnas = ""

        fun getFilas(): String {
            return filas
        }

        fun setFilas(string: String){
            filas = string
        }

        fun getColumnas(): String {
            return columnas
        }

        fun setColumnas(string: String){
            columnas = string
        }

        fun getTurnoMaquina(): Boolean{
            return turnoMaquina
        }

        fun setTurnoMaquina(booleano: Boolean){
            turnoMaquina = booleano
        }
    }
}