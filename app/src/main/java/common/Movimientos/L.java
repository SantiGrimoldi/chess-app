package common.Movimientos;

import common.interfaces.Movimiento;
import common.Posicion;
import common.Tablero;

public class L implements Movimiento {
    public L() {

    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        return inicialFinalValido(posicionInicial, posicionFinal);
    }

    private boolean inicialFinalValido(Posicion posicionInicial, Posicion posicionFinal){
        int x0 = posicionInicial.getX();
        int x1 = posicionFinal.getX();
        int y0 = posicionInicial.getY();
        int y1 = posicionFinal.getY();
        int diffX = Math.abs(x0 - x1);
        int diffY = Math.abs(y0 - y1);
        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }

}
