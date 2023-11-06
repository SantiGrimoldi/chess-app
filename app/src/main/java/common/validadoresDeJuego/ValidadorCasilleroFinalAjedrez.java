package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import edu.austral.dissis.chess.gui.PlayerColor;

public class ValidadorCasilleroFinalAjedrez implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        if (tablero.tienePieza(posicionFinal) && tablero.obtenerPieza(posicionFinal).getColor() == color) {
            return new ResultSet(tablero, "No puedes comer tu propia pieza", false, true);
        }
        return new ResultSet(tablero, "Ambos casilleros son validos", false, false);
    }
}
