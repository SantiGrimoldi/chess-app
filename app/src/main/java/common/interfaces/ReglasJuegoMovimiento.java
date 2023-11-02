package common.interfaces;

import common.Posicion;
import common.Tablero;
import common.User;

public interface ReglasJuegoMovimiento {

    public  boolean movValidoJuego(Posicion inicial, Posicion post, Tablero tablero, User user);
}
