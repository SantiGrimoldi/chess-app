package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public class ValidadorJugador implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.tienePieza(posicionFinal) && tablero.obtenerPieza(posicionFinal).getOwner() == usuario) {
            return new ResultSet(tablero, "No puedes comer tu propia pieza", false, true);
        }
        return new ResultSet(tablero, "Movimiento valido", false, false);
    }
}
