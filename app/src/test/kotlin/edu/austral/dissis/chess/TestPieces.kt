package edu.austral.dissis.chess

import myChess.game.Posicion
import myChess.game.validadoresDeJuego.ValidadorMovimientosNormales

class TestPieces {

    fun main(args: Array<String>) {
        val board = MyThings().tablero
        val normales = ValidadorMovimientosNormales()
        println(normales.validarJuego(Posicion(1, 0), Posicion(2, 0), board, board.obtenerPieza(Posicion(1, 0)).owner!!))
    }
}