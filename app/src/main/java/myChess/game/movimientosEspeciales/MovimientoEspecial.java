package myChess.game.movimientosEspeciales;

import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;

public interface MovimientoEspecial {

    Tablero MovimientoEspecial (Pieza pieza, Posicion inicial, Posicion post, Tablero tablero);

}
