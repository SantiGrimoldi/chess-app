package edu.austral.dissis.chess

import edu.austral.dissis.chess.gui.*
import myChess.conectUI.Conection
import myChess.game.*
import myChess.game.Movimientos.Dama
import myChess.game.movimientosEspeciales.MovimientosEspeciales
import myChess.game.movimientosEspeciales.Transformaciones
import myChess.game.validadoresDeJuego.ResultSet
import myChess.game.validadoresDeJuego.ValidadorDeJuego
import myChess.game.validadoresDeJuego.ValidadorGanarDamas
import myChess.game.validadoresDeJuego.ValidadorMoverTuPieza
import myChess.game.validadoresDeJuego.ValidadorMovimientoDama
import myChess.game.validadoresDeJuego.ValidadorMovimientosEspeciales
import myChess.game.validadoresDeJuego.ValidadorPuedeComer

class MyCheckers : GameEngine {
    private var tablero: Tablero = Tablero(8, 8)
    private val jugadores: MutableList<User> = ArrayList()
    private var turno = 0
    private val validadores : MutableList<ValidadorDeJuego> =
        listOf(
            ValidadorGanarDamas(),
            ValidadorMoverTuPieza(),
            ValidadorPuedeComer(),
            ValidadorMovimientoDama()
            ).toMutableList()
    private val especiales : MutableList<ValidadorDeJuego> = ArrayList()

    override fun applyMove(move: Move): MoveResult {
        val from  = Posicion(move.from.row -1,move.from.column -1)
        val to = Posicion(move.to.row -1,move.to.column -1)
        val nextColor : PlayerColor = if (jugadores[turno].color == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE
        return validateMovement(from, to, nextColor)
    }

    private fun validateMovement(from: Posicion, to: Posicion, nextColor: PlayerColor): MoveResult {
        for (validador in validadores) {
            var result: ResultSet = validador.validarJuego(from, to, tablero, jugadores[turno])
            if (result.win) return GameOver(jugadores[(turno + 1) % jugadores.size].color)
            if (result.returnable) {
                tablero = result.tablero
                var specialResult = specialMove(from, to)
                result.tablero = specialResult.tablero
                if (specialResult.invalid){
                    result = specialResult
                }
                return returnResult(result, nextColor)
            }
            if (result.invalid) return InvalidMove(result.explanation)
        }
        return InvalidMove("No se pudo realizar el movimiento")
    }

    private fun returnResult(
        result: ResultSet,
        nextColor: PlayerColor
    ): NewGameState {
        if (result.invalid) return NewGameState(Conection.adaptPieces(result.tablero), jugadores[turno].color)
        turno = (turno + 1) % jugadores.size
        return NewGameState(Conection.adaptPieces(result.tablero), nextColor)
    }

    private fun specialMove(from: Posicion, to: Posicion) : ResultSet {
        for (especial in especiales) {
            val tableroaux = especial.validarJuego(from, to, tablero, jugadores[turno]).tablero
            if (tableroaux != tablero) {
                tablero = tableroaux
                if (ValidadorPuedeComer.verSiPuedoComer(tablero, jugadores[turno].color).invalid) {
                    return ResultSet(tablero, "Rey puede comer", true, true)
                }
                return ResultSet(tablero, "Rey no puede comer", true, false)
            }
        }
        return ResultSet(tablero, "", false, false)
    }

    override fun init(): InitialState {
        return InitialState(Conection.adaptBoard(tablero),Conection.adaptPieces(tablero), PlayerColor.WHITE)
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
                    tablero = tablero.agregarPieza(Pieza(NombrePieza.REINA, listOf(movBlanco), player1, listOf(MovimientosEspeciales.PRIMERO), ""+col+row), Posicion(row, col))
                }

                // Inicializa las posiciones iniciales de las damas negras en las últimas 3 filas
                if (row >= tablero.columnas - 3 && (col + row) % 2 == 0) {
                    tablero = tablero.agregarPieza(Pieza(NombrePieza.REINA, listOf(movNegro), player2, listOf(MovimientosEspeciales.PRIMERO), ""+col+row), Posicion(row, col))
                }
            }
        }
        val blancaActual : Pieza = tablero.obtenerPieza(Posicion(0,0))!!
        val negraActual : Pieza = tablero.obtenerPieza(Posicion(7,7))!!
        val reyBlanca = Pieza(NombrePieza.REY, listOf(Dama(true)), player1, "r1")
        val reyNegra = Pieza(NombrePieza.REY, listOf(Dama(true)), player2, "r2")
        especiales.add(ValidadorMovimientosEspeciales(listOf(Transformaciones(blancaActual, reyBlanca, 7))))
        especiales.add(ValidadorMovimientosEspeciales(listOf(Transformaciones(negraActual, reyNegra, 0))))
    }
}