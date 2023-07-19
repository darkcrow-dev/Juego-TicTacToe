package com.example.tictactoe

interface Jugadores{

    companion object{
        private var turnoJuego = false
        private var numeroMovimientos = 0
        private var matrizTablero = Array(3) {Array(3) {" "}}
        private var fichas = arrayOf("", "")
        private var jugar = true
        private var jugadoresJuego = arrayOf("", "")
        private lateinit var jugadores: Array<Jugadores>
        private var ganador = Ganador()

        fun getTurno(): Boolean {
            return turnoJuego
        }

        fun setTurno(booleano: Boolean){
            turnoJuego = booleano
        }

        fun getNumeroMovimientos(): Int{
            return numeroMovimientos
        }

        fun setNumeroMovimientos(integer: Int){
            numeroMovimientos = integer
        }

        fun getMatrizTablero(): Array<Array<String>>{
            return matrizTablero
        }

        fun setMatrizTablero(dobleArray: Array<Array<String>>){
            matrizTablero = dobleArray
        }

        fun getFichas(): Array<String>{
            return fichas
        }

        fun setFichas(stringArray: Array<String>){
            fichas = stringArray
        }

        fun getJugar(): Boolean{
            return jugar
        }

        fun setJugar(booleano: Boolean){
            jugar = booleano
        }

        fun getJugadoresJuego(): Array<String>{
            return jugadoresJuego
        }

        fun setJugadoresJuego(stringArray: Array<String>){
            jugadoresJuego = stringArray
        }

        fun getJugadores(): Array<Jugadores>{
            return jugadores
        }

        fun setJugadores(arrayJugadores: Array<Jugadores>){
            jugadores = arrayJugadores
        }

        fun convertirBooleano(booleano: Boolean): Int{
            var bandera = 0

            if(booleano){
                bandera = 1
            }

            return bandera
        }

        fun getGanador(): Ganador {
            return ganador
        }
    }

    fun movimientoJugador(casilla: String){
    }

    fun inicializarTablero(contador: Int){
    }

    fun terminarJuego(){

    }
}