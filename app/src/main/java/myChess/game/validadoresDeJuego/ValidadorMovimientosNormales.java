package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public class ValidadorMovimientosNormales implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.estaDentoDeLosLimites(posicionInicial) && tablero.estaDentoDeLosLimites(posicionFinal)) {
            if (!tablero.tienePieza(posicionInicial)) return new ResultSet(tablero, "No hay pieza en la posicion inicial", false, true);
            if (tablero.tienePieza(posicionFinal) && tablero.obtenerPieza(posicionFinal).getOwner() == usuario) return new ResultSet(tablero, "No puedes comer tu propia pieza", false, true);
            Pieza pieza = tablero.obtenerPieza(posicionInicial);
            if (pieza.movimientoValido(posicionInicial, posicionFinal, tablero)) {
                Tablero tableroNuevo = actualizarTablero(posicionInicial, posicionFinal, tablero, pieza);
                return new ResultSet(tableroNuevo, "Movimiento valido", true, false);
            }
        }
        return new ResultSet(tablero, "Movimiento invalido", false, false);
    }

    private Tablero actualizarTablero(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, Pieza pieza) {
        pieza = pieza.limpiarMovimientosEspeciales();
        Tablero tableroNuevo = tablero.moverPieza(posicionInicial, posicionFinal, pieza);
        return tableroNuevo;
    }
}
