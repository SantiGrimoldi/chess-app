package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public class ValidadorMoverTuPieza implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.tienePieza(posicionInicial) && tablero.obtenerPieza(posicionInicial).getOwner().getColor() != usuario.getColor()) {
            return new ResultSet(tablero, "No es tu pieza", false, true);
        }
        if (!tablero.tienePieza(posicionInicial)) {
            return new ResultSet(tablero, "No hay pieza en el casillero inicial", false, true);
        }
        return new ResultSet(tablero, "Es tu pieza", false, false);
    }
}
