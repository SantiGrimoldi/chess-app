package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public class ValidadorMoverTuPieza implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.tienePieza(posicionInicial) && tablero.obtenerPieza(posicionInicial).getOwner() != usuario) {
            return new ResultSet(tablero, "No es tu pieza", false, true);
        }
        return new ResultSet(tablero, "Movimiento valido", true, false);
    }
}
