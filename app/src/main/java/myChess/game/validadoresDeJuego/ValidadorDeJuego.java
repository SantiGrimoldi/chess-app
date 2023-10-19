package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public interface ValidadorDeJuego {
    public ResultSet validarJuego(
            Posicion posicionInicial,
            Posicion posicionFinal,
            Tablero tablero,
            User usuario);
}
