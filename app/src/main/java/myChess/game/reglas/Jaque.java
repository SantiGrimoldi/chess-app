package myChess.game.reglas;

import common.interfaces.ReglasJuegoMovimiento;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Pieza;
import common.Posicion;
import common.Tablero;
import common.User;

import java.util.List;

public class Jaque implements ReglasJuegoMovimiento {
    public static Boolean jaque (Posicion posPieza, Posicion posRey, Tablero tablero) {
        try{
            if (!tablero.tienePieza(posPieza)) return false;
            Pieza pieza = tablero.obtenerPieza(posPieza);
            if (pieza.movimientoValido(posPieza, posRey, tablero)) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean estoyEnJaque (Posicion rey, Tablero tablero, PlayerColor jugador) {

        for (int i = 0; i< tablero.getFilas(); i++){
            for (int j = 0; j< tablero.getColumnas(); j++){
                Posicion posicion = new Posicion(i,j);
                if (tablero.tienePieza(posicion) && tablero.obtenerPieza(posicion).getColor() != jugador) {
                    if (Jaque.jaque(posicion, rey, tablero)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean salgoDeJaque (Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor jugador) {
        Tablero tableroAux = tablero.moverPieza(posicionInicial, posicionFinal, tablero.obtenerPieza(posicionInicial));
        List<Posicion> reyes = tableroAux.reyes();
        if (reyes.isEmpty())  return true;
        for (Posicion rey : reyes) {
            if (tableroAux.obtenerPieza(rey).getColor() == jugador) {
                return !estoyEnJaque(rey, tableroAux, jugador);
            }
        }
        return false;
    }


    @Override
    public boolean movValidoJuego(Posicion inicial, Posicion post, Tablero tablero, User user) {
        return salgoDeJaque(inicial, post, tablero, user.getColor());
    }
}
