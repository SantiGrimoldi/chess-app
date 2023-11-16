package myCheckers.validadoresDeJuegos;

import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.ValidadorDeJuego;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Pieza;
import common.Posicion;
import common.Tablero;

import java.util.Map;
import java.util.stream.Collectors;

public class ValidadorGanarDamas implements ValidadorDeJuego {
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        Map<Posicion, Pieza> piezas = tablero.getTodasLasPiezas();
        piezas = piezas.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        PlayerColor otherColor = color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        if (checkOtherEmpty(tablero, otherColor)) return new ResultSet(true, tablero, "Ganaste");
        if (puedeMover(piezas, tablero)) {
            return new ResultSet(tablero, "Movimiento valido", false, false);
        }
        else {
            return new ResultSet(true, tablero, "Ganaste");
        }
    }

    private boolean checkOtherEmpty(Tablero tablero, PlayerColor color){
        Map<Posicion, Pieza> piezas = tablero.getTodasLasPiezas();
        piezas = piezas.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (piezas.isEmpty()) return true;
        else return false;
    }

    private boolean puedeMover(Map<Posicion, Pieza> piezas, Tablero tablero) {
        if (piezas.isEmpty()) return false;
        for (Map.Entry<Posicion, Pieza> entry : piezas.entrySet()) {
            Posicion posicion = entry.getKey();
            Pieza pieza = entry.getValue();
            for (int i = 0; i < tablero.getColumnas(); i++) {
                for (int j = 0; j < tablero.getFilas(); j++) {
                    Posicion destino = new Posicion(i, j);
                    if (posicion.equals(destino)) continue;
                    if (!tablero.tienePieza(destino)) {
                        if (pieza.movimientoValido(posicion, destino, tablero)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
