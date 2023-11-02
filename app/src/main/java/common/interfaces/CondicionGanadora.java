package common.interfaces;

import common.Posicion;
import common.Tablero;
import common.User;

public interface CondicionGanadora {
    boolean condicionGanadora(Posicion incial, Posicion destino, Tablero tablero, User usuario);
}
