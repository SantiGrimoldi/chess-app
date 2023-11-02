package myCheckers.validadoresDeJuegos;

import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.ValidadorDeJuego;
import common.Pieza;
import common.Posicion;
import common.Tablero;
import common.User;

public class ValidadorMovimientoDama implements ValidadorDeJuego {
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.tienePieza(posicionFinal)) return new ResultSet(tablero, "No podes mover ahi", false, false);
        if (tablero.tienePieza(posicionInicial)) {
            return validarMovimiento(posicionInicial, posicionFinal, tablero, usuario);
        }
        return new ResultSet(tablero, "No hay pieza en la posicion inicial", false, true);
    }

    private ResultSet validarMovimiento(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        Pieza pieza = tablero.obtenerPieza(posicionInicial);
        if (pieza.movimientoValido(posicionInicial, posicionFinal, tablero)){
            tablero = tablero.moverPieza(posicionInicial, posicionFinal, pieza);
            tablero = cleanDiagonal(posicionInicial, posicionFinal, tablero);
            int diffX = Math.abs(posicionFinal.getX() - posicionInicial.getX());
            if (diffX == 2 && ValidadorPuedeComer.verSiPuedoComer(tablero, usuario.getColor()).getInvalid()) {
                return new ResultSet(tablero, "Sigue jugando", true, true);
            }
            return new ResultSet(tablero, "Segui jugando", true, false);
        }
        else {
            return new ResultSet(tablero, "Movimiento invalido", false, true);
        }
    }

    private Tablero cleanDiagonal(Posicion inicial, Posicion ultima, Tablero tablero) {
        if (inicial.equals(ultima)) return tablero;
        int direccionX = ultima.getX() - inicial.getX() > 0 ? 1 : -1;
        int direccionY = ultima.getY() - inicial.getY() > 0 ? 1 : -1;
        tablero = tablero.eliminarPieza(inicial);
        return cleanDiagonal(new Posicion(inicial.getX() + direccionX, inicial.getY() + direccionY), ultima, tablero);
    }
}
