package edu.austral.dissis.chess

import common.*

class MyThings()  {
    private var factory = ChessFactory()
    var tablero: Tablero = Tablero(8, 8)
    val jugadores: MutableList<User> = ArrayList()

    init {
        val (player1, player2) = factory.createPlayers()
        jugadores.add(player1)
        jugadores.add(player2)
//        tablero = factory.simplifiedChess(player1, player2)
        tablero = factory.addPieces(player1, player2)
    }

}