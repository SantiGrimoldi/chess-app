package edu.austral.dissis.chess

import edu.austral.dissis.chess.gui.PlayerColor
import myChess.game.*
import myChess.game.Movimientos.*
import myChess.game.movimientosEspeciales.MovimientosEspeciales
import java.util.stream.IntStream.range

class MyChess()  {
    private var board: Tablero = Tablero(8, 8)
    private val playersList: MutableList<User> = ArrayList()

    init {
        val player1 = User("Player 1", PlayerColor.WHITE)
        playersList.add(player1)
        val player2 = User("Player 2", PlayerColor.BLACK)
        playersList.add(player2)

        board = board.agregarPieza(Pieza(NombrePieza.REY, listOf(UnCuadrado()), player1, true, listOf(MovimientosEspeciales.ENROQUE)), Posicion(0, 4))
        board = board.agregarPieza(Pieza(NombrePieza.REINA, listOf(Vertical(false), Horizontal(false), Diagonal(false)), player1, null), Posicion(0, 5))
        board = board.agregarPieza(Pieza(NombrePieza.REY, listOf(UnCuadrado()), player2, true, listOf(MovimientosEspeciales.ENROQUE)), Posicion(7, 5))
        board = board.agregarPieza(Pieza(NombrePieza.REINA, listOf(Vertical(false), Horizontal(false), Diagonal(false)), player2, null), Posicion(7, 4))
        board = board.agregarPieza(Pieza(NombrePieza.CABALLO, listOf(L()), player1, null), Posicion(0, 1))
        board = board.agregarPieza(Pieza(NombrePieza.CABALLO, listOf(L()), player1, null), Posicion(0, 6))
        board = board.agregarPieza(Pieza(NombrePieza.CABALLO, listOf(L()), player2, null), Posicion(7, 1))
        board = board.agregarPieza(Pieza(NombrePieza.CABALLO, listOf(L()), player2, null), Posicion(7, 6))
        board = board.agregarPieza(Pieza(NombrePieza.ALFIL, listOf(Diagonal(false)), player1, null), Posicion(0, 2))
        board = board.agregarPieza(Pieza(NombrePieza.ALFIL, listOf(Diagonal(false)), player1, null), Posicion(0, 5))
        board = board.agregarPieza(Pieza(NombrePieza.ALFIL, listOf(Diagonal(false)), player2, null), Posicion(7, 2))
        board = board.agregarPieza(Pieza(NombrePieza.ALFIL, listOf(Diagonal(false)), player2, null), Posicion(7, 5))
        board = board.agregarPieza(Pieza(NombrePieza.TORRE, listOf(Vertical(false), Horizontal(false)), player1, listOf(MovimientosEspeciales.ENROQUE)), Posicion(0, 0))
        board = board.agregarPieza(Pieza(NombrePieza.TORRE, listOf(Vertical(false), Horizontal(false)), player1, listOf(MovimientosEspeciales.ENROQUE)), Posicion(0, 7))
        board = board.agregarPieza(Pieza(NombrePieza.TORRE, listOf(Vertical(false), Horizontal(false)), player2, listOf(MovimientosEspeciales.ENROQUE)), Posicion(7, 0))
        board = board.agregarPieza(Pieza(NombrePieza.TORRE, listOf(Vertical(false), Horizontal(false)), player2, listOf(MovimientosEspeciales.ENROQUE)), Posicion(7, 7))

        for (i in range(0,8)){
            board = board.agregarPieza(Pieza(NombrePieza.PEON, listOf(Peon()), player1, listOf(MovimientosEspeciales.PRIMERO)), Posicion(1, i))
            board = board.agregarPieza(Pieza(NombrePieza.PEON, listOf(Peon()), player2, listOf(MovimientosEspeciales.PRIMERO)), Posicion(6, i))
        }

    }

}