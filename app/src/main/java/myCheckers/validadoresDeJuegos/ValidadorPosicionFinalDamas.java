package myCheckers.validadoresDeJuegos;

import common.Posicion;
import common.Tablero;
import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.ValidadorDeJuego;
import edu.austral.dissis.chess.gui.PlayerColor;

public class ValidadorPosicionFinalDamas implements ValidadorDeJuego {
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        if (tablero.tienePieza(posicionFinal)) return new ResultSet(tablero, "No puedes mover a un casillero ocupado", false, true);
        return new ResultSet(tablero, "Casillero final valido", false, false);
    }
}
