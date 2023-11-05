package edu.austral.dissis.checkers

import common.NombrePieza
import common.Pieza
import common.Transformaciones
import common.User
import common.validadoresDeJuego.ValidadorAnd
import common.validadoresDeJuego.ValidadorCasilleroFinal
import common.validadoresDeJuego.ValidadorDeJuego
import common.validadoresDeJuego.ValidadorMoverTuPieza
import common.validadoresDeJuego.ValidadorMovimientosEspeciales
import edu.austral.dissis.chess.gui.PlayerColor
import myCheckers.Dama
import myCheckers.validadoresDeJuegos.ValidadorGanarDamas
import myCheckers.validadoresDeJuegos.ValidadorMovimientoDama
import myCheckers.validadoresDeJuegos.ValidadorPuedeComer

class CheckersFactory {
    fun classicCheckersValidator () : ValidadorDeJuego {
        val movEsp = validadorMovsEsp()
        val myV = ValidadorAnd(listOf(ValidadorMoverTuPieza(), ValidadorCasilleroFinal(), ValidadorPuedeComer(), ValidadorMovimientoDama(), movEsp, ValidadorGanarDamas()))
        return myV
    }

    private fun validadorMovsEsp() : ValidadorMovimientosEspeciales {
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
            )
        )
        return movEsp
    }
}