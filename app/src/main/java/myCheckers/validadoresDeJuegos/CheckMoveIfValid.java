package myCheckers.validadoresDeJuegos;

import common.Posicion;
import common.Tablero;
import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.ValidadorDeJuego;
import edu.austral.dissis.chess.gui.PlayerColor;
import myCheckers.validadoresDeJuegos.ValidadorPuedeComer;

public class CheckMoveIfValid implements ValidadorDeJuego {

    private ValidadorDeJuego condition;
    public CheckMoveIfValid(ValidadorDeJuego condition) {
        this.condition = condition;
    }

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        ResultSet result = condition.validarJuego(posicionInicial, posicionFinal, tablero, color);
        if (result.getReturnable()) {
            tablero = result.getTablero();
            if (ValidadorPuedeComer.verSiPuedoComer(tablero, color).getInvalid()) return new ResultSet(tablero, "Sigue jugando", true, false, true);

        }
        return result;
    }
}
