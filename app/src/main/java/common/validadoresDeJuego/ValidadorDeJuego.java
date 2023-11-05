package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import edu.austral.dissis.chess.gui.PlayerColor;

public interface ValidadorDeJuego {
    public ResultSet validarJuego(
            Posicion posicionInicial,
            Posicion posicionFinal,
            Tablero tablero,
            PlayerColor color);
}
