package myChess.game.Movimientos;

import myChess.game.Posicion;
import myChess.game.Tablero;

public interface Movimiento {
    public Boolean movimientoValido (Posicion posicionInicial, Posicion posicionFinal, Tablero tablero);
}
