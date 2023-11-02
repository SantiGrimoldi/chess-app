package common.interfaces;

import common.Posicion;
import common.Tablero;

public interface Movimiento {
    public Boolean movimientoValido (Posicion posicionInicial, Posicion posicionFinal, Tablero tablero);
}
