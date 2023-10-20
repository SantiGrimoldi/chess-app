package edu.austral.dissis.chess

import myChess.conectUI.Conection

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import myChess.game.Posicion
import myChess.game.Tablero
import myChess.game.User
import myChess.game.movimientosEspeciales.Enroque
import myChess.game.movimientosEspeciales.PrimeroPeon
import myChess.game.reglas.Jaque
import myChess.game.validadoresDeJuego.ResultSet
import myChess.game.validadoresDeJuego.ValidadorDeJuego
import myChess.game.validadoresDeJuego.ValidadorCasilleroFinal
import myChess.game.validadoresDeJuego.ValidadorMoverTuPieza
import myChess.game.validadoresDeJuego.ValidadorMovimientosEspeciales
import myChess.game.validadoresDeJuego.ValidadorMovimientosNormales
import myChess.game.validadoresDeJuego.ValidadorReglasJuego

public class Mychess: GameEngine {
    private var tablero : Tablero = MyThings().tablero
    private var jugadores : ArrayList<User> = MyThings().jugadores as ArrayList<User>
    private var turno = 0
    private var validadores : List<ValidadorDeJuego> =
        listOf(
            ValidadorCasilleroFinal(),
            ValidadorMoverTuPieza(),
            ValidadorReglasJuego(listOf(Jaque())),
            ValidadorMovimientosEspeciales(listOf(Enroque(), PrimeroPeon())),
            ValidadorMovimientosNormales()
        )

    override fun applyMove(move: Move) : MoveResult {
        val from  = Posicion(move.from.row -1,move.from.column -1)
        val to = Posicion(move.to.row -1,move.to.column -1)
//        println("" + from.x + "," + from.y + " to " + to.x + "," + to.y)
//        println(tablero.obtenerPieza(from).nombre)
        val nextColor : PlayerColor = if (jugadores[turno].color == WHITE) BLACK else WHITE
        for (validador in validadores){
            var result : ResultSet = validador.validarJuego(from,to, tablero, jugadores[turno] )
//            println(result.explanation)
            if (result.invalid){
                return InvalidMove(result.explanation)
            }
            if (result.returnable == true){
                tablero = result.tablero
                turno = (turno + 1) % jugadores.size
//                println("Movimiento realizado")
//                println(tablero.imprimirTablero())
                return NewGameState(Conection.adaptPieces(result.tablero), nextColor)
            }
        }
        return InvalidMove("No se pudo realizar el movimiento")
    }

    override fun init(): InitialState {
        return InitialState(Conection.adaptBoard(tablero),Conection.adaptPieces(tablero), WHITE)
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