package edu.austral.dissis.checkers

import common.*
import edu.austral.dissis.chess.gui.*
import common.conectUI.UIAdapter
import myCheckers.Dama
import common.Movimientos.MovimientosEspeciales
import common.validadoresDeJuego.ResultSet
import common.validadoresDeJuego.ValidadorDeJuego
import myCheckers.validadoresDeJuegos.ValidadorGanarDamas
import common.validadoresDeJuego.ValidadorMoverTuPieza
import myCheckers.validadoresDeJuegos.ValidadorMovimientoDama
import common.validadoresDeJuego.ValidadorMovimientosEspeciales
import edu.austral.dissis.common.GamesInterface
import myCheckers.validadoresDeJuegos.ValidadorPuedeComer

class MyCheckers :  GamesInterface {
    private val factory : CheckersFactory = CheckersFactory()
    override var tablero: Tablero = Tablero(8, 8)
    override var jugadores: ArrayList<User> = ArrayList()
    override var turno = 0
    override var validador : ValidadorDeJuego = factory.classicCheckersValidator()


    override fun applyMove(move: Move): MoveResult {
        val from  = Posicion(move.from.row - 1, move.from.column - 1)
        val to = Posicion(move.to.row - 1, move.to.column - 1)
        val nextColor : PlayerColor = if (jugadores[turno].color == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE
        return validateMovement(from, to, nextColor)
    }

    private fun validateMovement(from: Posicion, to: Posicion, nextColor: PlayerColor): MoveResult {
            var result: ResultSet = validador.validarJuego(from, to, tablero, jugadores[turno].color)
            if (result.returnable) {
                tablero = result.tablero
                if (result.win) return GameOver(jugadores[turno].color)
                return returnResult(result, nextColor)
            }
            if (result.invalid) return InvalidMove(result.explanation)
        return InvalidMove("No se pudo realizar el movimiento")
    }

    private fun returnResult(result: ResultSet,nextColor: PlayerColor): NewGameState {
        if (result.keepTurn()) return NewGameState(UIAdapter.adaptPieces(result.tablero), jugadores[turno].color)
        turno = (turno + 1) % jugadores.size
        return NewGameState(UIAdapter.adaptPieces(result.tablero), nextColor)
    }


    override fun init(): InitialState {
        return InitialState(
            UIAdapter.adaptBoard(tablero),
            UIAdapter.adaptPieces(tablero), PlayerColor.WHITE)
    }

    init {
        tablero = factory.createClassicalCheckers()
        jugadores = factory.createPlayers().toList().toMutableList() as ArrayList<User>
    }




}