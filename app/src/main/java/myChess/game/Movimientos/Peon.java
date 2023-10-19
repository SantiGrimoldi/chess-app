package myChess.game.Movimientos;

import myChess.game.Posicion;
import myChess.game.Tablero;

public class Peon implements Movimiento{

    private  int direccion;

    public Peon(){
        this.direccion = 1;
    }

    public Peon(int direccion){
        this.direccion = direccion > 0 ? 1 : -1;
    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int diffX =  posicionFinal.getX() - posicionInicial.getX();
        int diffY =  posicionFinal.getY() - posicionInicial.getY();
        return diffX * direccion == 1 && diffY == 0 && !tablero.tienePieza(posicionFinal)|| comeDiagonal(posicionInicial, posicionFinal, tablero);
    }

    private boolean comeDiagonal(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        if (tablero.obtenerPieza(posicionFinal) == null) return false;
        if (tablero.obtenerPieza(posicionInicial).getOwner() == tablero.obtenerPieza(posicionFinal).getOwner()) return false;
        int diffX =  posicionFinal.getX() - posicionInicial.getX();
        int diffY =  posicionFinal.getY() - posicionInicial.getY();
        return diffX * direccion == 1 && Math.abs(diffY) == 1;
    }
}
