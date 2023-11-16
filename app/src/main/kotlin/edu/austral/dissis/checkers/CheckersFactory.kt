package edu.austral.dissis.checkers

import common.*
import common.Movimientos.MovimientosEspeciales
import common.validadoresDeJuego.ValidadorAnd
import common.validadoresDeJuego.ValidadorDeJuego
import myCheckers.validadoresDeJuegos.CheckMoveIfValid
import common.validadoresDeJuego.ValidadorMoverTuPieza
import common.validadoresDeJuego.ValidadorMovimientosEspeciales
import edu.austral.dissis.chess.gui.PlayerColor
import myCheckers.Dama
import myCheckers.validadoresDeJuegos.ValidadorGanarDamas
import myCheckers.validadoresDeJuegos.ValidadorMovimientoDama
import myCheckers.validadoresDeJuegos.ValidadorPosicionFinalDamas
import myCheckers.validadoresDeJuegos.ValidadorPuedeComer

class CheckersFactory {
    fun classicCheckersValidator () : ValidadorDeJuego {
        val movEsp = validadorMovsEsp()
        val myV = ValidadorAnd(listOf(ValidadorMoverTuPieza(),
            ValidadorPosicionFinalDamas(), ValidadorPuedeComer(), ValidadorMovimientoDama(), movEsp, ValidadorGanarDamas()))
        return myV
    }

    private fun validadorMovsEsp() : ValidadorDeJuego {
        val player1 = User("jugador", PlayerColor.WHITE)
        val player2 = User("jugador2", PlayerColor.BLACK)
        val blancaActual = Pieza(NombrePieza.REINA, listOf(Dama()), player1, "b1")
        val negraActual = Pieza(NombrePieza.REINA, listOf(Dama(-1)), player2, "b2")
        val reyBlanca = Pieza(NombrePieza.REY, listOf(Dama(true)), player1, "r1")
        val reyNegra = Pieza(NombrePieza.REY, listOf(Dama(true)), player2, "r2")
        val movEsp = ValidadorMovimientosEspeciales(
            listOf(
                Transformaciones(blancaActual,reyBlanca,7),
                Transformaciones(negraActual,reyNegra,0)
            ))
        val condition = CheckMoveIfValid(movEsp)

        return condition
    }

    fun createClassicalCheckers() : Tablero {
        var tablero = Tablero(8, 8)
        val (player1, player2) = createPlayers()
        val movBlanco = Dama()
        val movNegro = Dama(-1)
        for (row in 0 until tablero.filas) {
            // Recorre las columnas del tablero
            for (col in 0 until tablero.columnas) {
                // Inicializa las posiciones iniciales de las damas blancas en las primeras 3 filas
                if (row < 3 && (col + row) % 2 == 0) {
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
        return tablero
    }

    fun createPlayers(): Pair<User, User> {
        val player1 = User("Player 1", PlayerColor.WHITE)
        val player2 = User("Player 2", PlayerColor.BLACK)
        return Pair(player1, player2)
    }
}