package myChess.game.Movimientos;

import myChess.game.Posicion;
import myChess.game.Tablero;

public class UnCuadrado implements Movimiento{
    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int x0 = posicionInicial.getX();
        int x1 = posicionFinal.getX();
        int y0 = posicionInicial.getY();
        int y1 = posicionFinal.getY();
        int diffX = x0 < x1 ? x1 - x0 : x0 - x1;
        int diffY = y0 < y1 ? y1 - y0 : y0 - y1;
        return diffY <= 1 && diffX <= 1 && diffX + diffY != 0;
    }
}
