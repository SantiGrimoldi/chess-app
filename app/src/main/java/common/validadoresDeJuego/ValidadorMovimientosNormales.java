package common.validadoresDeJuego;

import common.Pieza;
import common.Posicion;
import common.Tablero;
import edu.austral.dissis.chess.gui.PlayerColor;

public class ValidadorMovimientosNormales implements ValidadorDeJuego{

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        if (tablero.estaDentoDeLosLimites(posicionInicial) && tablero.estaDentoDeLosLimites(posicionFinal)) {
//
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
