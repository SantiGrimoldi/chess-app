package common.Movimientos;

import common.interfaces.Movimiento;
import common.Posicion;
import common.Tablero;

public class Peon implements Movimiento {

    private  int direccion;

    public Peon(){
        this.direccion = 1;
    }

    public Peon(int direccion){
        this.direccion = direccion > 0 ? 1 : -1;
    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int diffX = getDiffX(posicionInicial, posicionFinal);
        int diffY = getDiffY(posicionInicial, posicionFinal);
        return diffX * direccion == 1 && diffY == 0 && !tablero.tienePieza(posicionFinal)|| comeDiagonal(posicionInicial, posicionFinal, tablero);
    }

    private static int getDiffY(Posicion posicionInicial, Posicion posicionFinal) {
        return posicionFinal.getY() - posicionInicial.getY();
    }

    private static int getDiffX(Posicion posicionInicial, Posicion posicionFinal) {
        return posicionFinal.getX() - posicionInicial.getX();
    }

    private boolean comeDiagonal(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        if (!tablero.tienePieza(posicionFinal)) return false;
        if (tablero.obtenerPieza(posicionInicial).getOwner() == tablero.obtenerPieza(posicionFinal).getOwner()) return false;
        int diffX = getDiffX(posicionInicial, posicionFinal);
        int diffY = getDiffY(posicionInicial, posicionFinal);
        return diffX * direccion == 1 && Math.abs(diffY) == 1;
    }
}
