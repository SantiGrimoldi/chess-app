package myChess.game.reglas;

import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public interface ReglasJuegoMovimiento {

    public  boolean movValidoJuego(Posicion inicial, Posicion post, Tablero tablero, User user);
}
