package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import javafx.geometry.Pos;
import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public class ValidadorMovimientosNormales implements ValidadorDeJuego{

    ValidadorCasilleroFinal validadorCasilleroFinal = new ValidadorCasilleroFinal();
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.estaDentoDeLosLimites(posicionInicial) && tablero.estaDentoDeLosLimites(posicionFinal)) {
            if (!CheckCasillerosInicialYFinal(posicionInicial, posicionFinal, tablero, usuario)) {
                return new ResultSet(tablero, "Los casilleros seleccionados no son aptos para el movimiento", false, true);
            }
            Pieza pieza = tablero.obtenerPieza(posicionInicial);
            if (pieza.movimientoValido(posicionInicial, posicionFinal, tablero)) {
                Tablero tableroNuevo = actualizarTablero(posicionInicial, posicionFinal, tablero, pieza);
                return new ResultSet(tableroNuevo, "Movimiento valido", true, false);
            }
        }
        return new ResultSet(tablero, "Movimiento invalido", false, false);
    }

    private boolean CheckCasillerosInicialYFinal(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        ResultSet resultSet = validadorCasilleroFinal.validarJuego(posicionInicial, posicionFinal, tablero, usuario);
        return !resultSet.getReturnable() && !resultSet.getInvalid();
    }

    private Tablero actualizarTablero(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, Pieza pieza) {
        pieza = pieza.limpiarMovimientosEspeciales();
        Tablero tableroNuevo = tablero.moverPieza(posicionInicial, posicionFinal, pieza);
        return tableroNuevo;
    }
}
