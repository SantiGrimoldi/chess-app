package common.Movimientos;

import common.interfaces.Movimiento;
import common.Posicion;
import common.Tablero;

public class Vertical implements Movimiento {
    private final boolean salto;
    private final int distancia;

    public Vertical (boolean salto, int distancia) {
        this.salto = salto;
        this.distancia = distancia;
    }

    public Vertical (boolean salto) {
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

    private boolean inicialFinalValido(Posicion inicial, Posicion post){
        int x0 = inicial.getX();
        int x1 = post.getX();
        int y0 = inicial.getY();
        int y1 = post.getY();
        return x0 != x1 && y0 == y1;
    }

    public boolean caminoLibre(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int x0 = posicionInicial.getX();
        int x1 = posicionFinal.getX();
        int y0 = posicionInicial.getY();
        int y1 = posicionFinal.getY();
        if (Math.abs(x0 - x1) > distancia) return false;
        if (y0 != y1) return false;
        int diffX = x0 < x1 ? 1 : -1;
        for (int i = x0 + diffX ; i != x1; i += diffX) {
            if (tablero.tienePieza(new Posicion(i, y0))) return false;
        }
        return true;
    }
}
