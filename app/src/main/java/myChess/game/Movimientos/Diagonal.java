package myChess.game.Movimientos;

import myChess.game.Posicion;
import myChess.game.Tablero;

public class Diagonal implements Movimiento{
    private final boolean salto;
    private final int distancia;

    public Diagonal(boolean salto, int distancia) {
        this.salto = salto;
        this.distancia = distancia;
    }

    public Diagonal (boolean salto) {
        this.salto = salto;
        this.distancia = Integer.MAX_VALUE;
    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        if (posicionInicial.equals(posicionFinal)) return false;
        if (inicialFinalValido(posicionInicial, posicionFinal)) {
            if (!salto) {
                return caminoLibre(posicionInicial, posicionFinal, tablero);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean inicialFinalValido(Posicion posicionInicial, Posicion posicionFinal){
        int x0 = posicionInicial.getX();
        int x1 = posicionFinal.getX();
        int y0 = posicionInicial.getY();
        int y1 = posicionFinal.getY();
        int diffX = Math.abs(x0 - x1);
        int diffY = Math.abs(y0 - y1);
        return diffY == diffX;
    }

    public boolean caminoLibre(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int x0 = posicionInicial.getX();
        int x1 = posicionFinal.getX();
        int y0 = posicionInicial.getY();
        int y1 = posicionFinal.getY();
        if (Math.abs(x0 - x1) > distancia) return false;
        int diffX = x0 < x1 ? 1 : -1;
        int diffY = y0 < y1 ? 1 : -1;
        for (int i = x0 + diffX, j = y0 + diffY; i != x1 && j!= y1; i += diffX, j += diffY) {
            if (tablero.tienePieza(new Posicion(i, j))) throw new IllegalArgumentException("Hay una pieza en el camino, posición: " + i + ", " + j);
        }
        return true;
    }
}
