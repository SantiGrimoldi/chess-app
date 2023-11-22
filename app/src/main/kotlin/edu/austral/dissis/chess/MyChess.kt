package edu.austral.dissis.chess

import common.*

class MyThings()  {
    private var factory = ChessFactory()
    var board: Board = Board(8, 8)
    val players: MutableList<User> = ArrayList()

    init {
        val (player1, player2) = factory.createPlayers()
        players.add(player1)
        players.add(player2)
//        board = factory.simplifiedChess(player1, player2)
        board = factory.addPieces(player1, player2)
//        board = factory.winChess(player1,player2)
    }

}