package myChess.game.Movimientos;

import myChess.game.Posicion;
import myChess.game.Tablero;

public class Horizontal implements Movimiento{

    private final boolean salto;
    private final int distancia;

    public Horizontal(boolean salto, int distancia) {
        this.salto = salto;
        this.distancia = distancia;
    }

    public Horizontal (boolean salto) {
        this.salto = salto;
        this.distancia = Integer.MAX_VALUE;
    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
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
        return x0 == x1 && y0 != y1;
    }

    public boolean caminoLibre(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero)  {
        int y0 = posicionInicial.getY();
        int y1 = posicionFinal.getY();
        int x = posicionInicial.getX();
        int diffY = y0 < y1 ? 1 : -1;
        if (Math.abs(y0 - y1) > distancia) return false;
        for (int i = y0 + diffY; i != y1; i += diffY) {
            if (tablero.tienePieza(new Posicion(x, i))) return false;
        }
        return true;
    }

}
