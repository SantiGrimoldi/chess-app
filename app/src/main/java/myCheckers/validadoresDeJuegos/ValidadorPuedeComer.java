package myCheckers.validadoresDeJuegos;

import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.ValidadorDeJuego;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Pieza;
import common.Posicion;
import common.Tablero;

public class ValidadorPuedeComer implements ValidadorDeJuego {
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        int diffX = Math.abs(posicionFinal.getX() - posicionInicial.getX());
        if (diffX > 1) {
            return new ResultSet(tablero,"movimiento valido", false, false);
        }
        else {
            return verSiPuedoComer(tablero, color);
        }
    }

    public static ResultSet verSiPuedoComer(Tablero tablero, PlayerColor color) {
        for (int i = 0; i < tablero.getColumnas(); i++) {
            for (int j = 0; j < tablero.getFilas(); j++) {
                Posicion origen = new Posicion(i, j);
                if (tablero.tienePieza(origen) && tablero.obtenerPieza(origen).getColor() == color) {
                    ResultSet resultSet = todasLasPosiciones(origen, tablero);
                    if (resultSet != null) {
                        return resultSet;
                    }
                }
            }
        }
        return new ResultSet(tablero, "Movimiento valido", false, false);
    }

    private static ResultSet todasLasPosiciones(Posicion inicio, Tablero tablero) {
        for (int i = 0; i < tablero.getColumnas(); i++) {
            for (int j = 0; j < tablero.getFilas(); j++) {
                Posicion destino = new Posicion(i, j);
                if (inicio.equals(destino)) continue;
                if (!tablero.tienePieza(destino)) {
                    Pieza pieza = tablero.obtenerPieza(inicio);
                    if (pieza.movimientoValido(inicio, destino, tablero) && Math.abs(inicio.getX() - destino.getX()) > 1) {
                        return new ResultSet(tablero, "Movimiento invalido, puedo comer", false, true);
                    }
                }
            }
        }
        return null;
    }
}
