package myChess.game.condicionesGanadoras;

import javafx.geometry.Pos;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public interface CondicionGanadora {
    boolean condicionGanadora(Posicion incial, Posicion destino, Tablero tablero, User usuario);
}
