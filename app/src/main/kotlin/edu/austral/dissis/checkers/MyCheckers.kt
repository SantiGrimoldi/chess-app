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
import myCheckers.validadoresDeJuegos.ValidadorPuedeComer

class MyCheckers : GameEngine {
    private var tablero: Tablero = Tablero(8, 8)
    private val jugadores: MutableList<User> = ArrayList()
    private var turno = 0
    private val validador : ValidadorDeJuego = CheckersFactory().classicCheckersValidator()
//    private val especiales : MutableList<ValidadorDeJuego> = ArrayList()
    private val winningValidator = ValidadorGanarDamas()

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

//    private fun specialMove(from: Posicion, to: Posicion) : ResultSet {
//        for (especial in especiales) {
//            val tableroaux = especial.validarJuego(from, to, tablero, jugadores[turno].color).tablero
//            if (tableroaux != tablero) {
//                tablero = tableroaux
//                if (ValidadorPuedeComer.verSiPuedoComer(tablero, jugadores[turno].color).invalid) {
//                    return ResultSet(
//                        tablero,
//                        "Rey puede comer",
//                        true,
//                        true
//                    )
//                }
//                return ResultSet(
//                    tablero,
//                    "Rey no puede comer",
//                    true,
//                    false
//                )
//            }
//        }
//        return ResultSet(tablero, "", false, false)
//    }

    override fun init(): InitialState {
        return InitialState(
            UIAdapter.adaptBoard(tablero),
            UIAdapter.adaptPieces(tablero), PlayerColor.WHITE)
    }

    init {
        val player1 = User("Player 1", PlayerColor.WHITE)
        jugadores.add(player1)
        val player2 = User("Player 2", PlayerColor.BLACK)
        jugadores.add(player2)
        val movBlanco = Dama()
        val movNegro = Dama(-1)
        for (row in 0 until tablero.filas) {
            // Recorre las columnas del tablero
            for (col in 0 until tablero.columnas) {
                // Inicializa las posiciones iniciales de las damas blancas en las primeras 3 filas
                if (row < 3 && (col+row) % 2 == 0) {
                    tablero = tablero.agregarPieza(
                        Pieza(
                            NombrePieza.REINA,
                            listOf(movBlanco),
                            player1,
                            listOf(MovimientosEspeciales.PRIMERO),
                            "" + col + row
                        ), Posicion(row, col)
                    )
                }

                // Inicializa las posiciones iniciales de las damas negras en las últimas 3 filas
                if (row >= tablero.columnas - 3 && (col + row) % 2 == 0) {
                    tablero = tablero.agregarPieza(
                        Pieza(
                            NombrePieza.REINA,
                            listOf(movNegro),
                            player2,
                            listOf(MovimientosEspeciales.PRIMERO),
                            "" + col + row
                        ), Posicion(row, col)
                    )
                }
            }
        }
    }
}