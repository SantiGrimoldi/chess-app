package edu.austral.dissis.chess

import common.validadoresDeJuego.ValidadorAnd
import common.validadoresDeJuego.ValidadorCasilleroFinal
import common.validadoresDeJuego.ValidadorDeJuego
import common.validadoresDeJuego.ValidadorMoverTuPieza
import common.validadoresDeJuego.ValidadorMovimientosEspeciales
import common.validadoresDeJuego.ValidadorMovimientosNormales
import common.validadoresDeJuego.ValidadorOr
import myChess.game.movimientosEspeciales.Enroque
import myChess.game.movimientosEspeciales.PrimeroPeon
import myChess.game.validadoresDeJuego.Jaque
import myChess.game.validadoresDeJuego.JaqueMate

class ChessFactory {

    fun createClasssicValidators() : ValidadorDeJuego{
        var myValidator  = ValidadorAnd(listOf(ValidadorMoverTuPieza(), ValidadorCasilleroFinal()))
        val movimientos = ValidadorOr(listOf(ValidadorMovimientosNormales(), ValidadorMovimientosEspeciales(listOf(Enroque(), PrimeroPeon()))))
        myValidator = myValidator.addValidador(movimientos)
        myValidator = myValidator.addValidador(Jaque())
        return myValidator.addValidador(JaqueMate())

    }
}