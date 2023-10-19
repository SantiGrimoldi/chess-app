package edu.austral.dissis.chess

import myChess.conectUI.Conection

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import myChess.game.Partida
import myChess.game.Posicion
import myChess.game.Tablero
import myChess.game.User
import myChess.game.validadoresDeJuego.ResultSet
import myChess.game.validadoresDeJuego.ValidadorDeJuego

public class Mychess: GameEngine {
    private var tablero : Tablero
    private var jugadores : ArrayList<User>
    private var turno = 0
    private var validadores : List<ValidadorDeJuego>

    override fun applyMove(move: Move) : MoveResult {
        val from : Posicion = Posicion(move.from.column,move.from.row)
        val to : Posicion = Posicion(move.to.column,move.to.row)
        val nextColor : PlayerColor = if (jugadores[turno].color == WHITE) BLACK else WHITE
        for (validador in validadores){
            val result : ResultSet = validador.validarJuego(from,to, tablero, jugadores[turno] )
            if (result.invalid){
                InvalidMove(result.explanation)
            }
            if (result.returnable){
                tablero = result.tablero
                turno = (turno + 1) % jugadores.size
                return NewGameState(Conection.adaptPieces(tablero), nextColor)
            }
        }
        return InvalidMove("No se pudo realizar el movimiento")
    }

    override fun init(): InitialState {
        return InitialState(Conection.adaptBoard(tablero),Conection.adaptPieces(tablero), WHITE)
    }

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