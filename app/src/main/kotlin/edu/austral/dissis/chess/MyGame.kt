package edu.austral.dissis.chess

import common.conectUI.UIAdapter

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import common.Posicion
import common.Tablero
import common.User
import common.validadoresDeJuego.ResultSet
import common.validadoresDeJuego.ValidadorDeJuego
import edu.austral.dissis.common.GamesInterface

class Mychess: GamesInterface {
    override var tablero : Tablero = MyThings().tablero
    override var jugadores : ArrayList<User> = MyThings().jugadores as ArrayList<User>
    override var turno = 0
    override var validador : ValidadorDeJuego = ChessFactory().createClasssicValidators()


    override fun applyMove(move: Move) : MoveResult {
        val from  = Posicion(move.from.row - 1, move.from.column - 1)
        val to = Posicion(move.to.row - 1, move.to.column - 1)
        val nextColor : PlayerColor = if (jugadores[turno].color == WHITE) BLACK else WHITE
        val thisColor : PlayerColor = jugadores[turno].color
        val result : ResultSet = validador.validarJuego(from,to, tablero, thisColor )
        if (result.win){
            return GameOver(jugadores[turno].color)
        }
        if (result.invalid){
            return InvalidMove(result.explanation)
        }
        if (result.returnable == true){
            tablero = result.tablero
            turno = (turno + 1) % jugadores.size
            return NewGameState(UIAdapter.adaptPieces(result.tablero), nextColor)
        }

        return InvalidMove("No se pudo realizar el movimiento")
    }

    override fun init(): InitialState {
        return InitialState(
            UIAdapter.adaptBoard(tablero),
            UIAdapter.adaptPieces(tablero), WHITE)
    }
}

class MovePrinter : PieceMovedListener {
    override fun onMovePiece(from: Position, to: Position) {
        print("Move: from ")
        print(from)
        print(" to ")
        println(to)
    }
}