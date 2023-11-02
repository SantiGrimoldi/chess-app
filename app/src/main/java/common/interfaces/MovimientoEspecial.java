package common.interfaces;

import common.Pieza;
import common.Posicion;
import common.Tablero;

public interface MovimientoEspecial {

    Tablero MovimientoEspecial (Pieza pieza, Posicion inicial, Posicion post, Tablero tablero);

}
