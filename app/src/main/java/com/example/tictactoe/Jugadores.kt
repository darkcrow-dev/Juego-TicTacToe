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
        private var modalidad = ""
        private var nivel = ""
        private var turnoMaquina = false

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

        fun getTurnoMaquina(): Boolean{
            return turnoMaquina
        }

        fun setTurnoMaquina(booleano: Boolean){
            turnoMaquina = booleano
        }

        fun getModalidad(): String {
            return modalidad
        }

        fun setModalidad(string: String){
            modalidad = string
        }

        fun getNivel(): String {
            return nivel
        }

        fun setNivel(string: String){
            nivel = string
        }

        fun getGanador(): Ganador {
            return ganador
        }

        fun convertirBooleano(booleano: Boolean): Int{
            var bandera = 0

            if(booleano){
                bandera = 1
            }

            return bandera
        }
    }

    fun movimientoJugador(casilla: String){
    }

    fun inicializarTablero(){
    }

    fun terminarJuego(){

    }
}