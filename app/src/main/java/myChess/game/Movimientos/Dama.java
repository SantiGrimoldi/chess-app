package myChess.game.Movimientos;

import myChess.game.Posicion;
import myChess.game.Tablero;

public class Dama implements Movimiento{
    private final Diagonal diagonal = new Diagonal(false, 2);
    private final int direccion;

    public Dama(){
        this.direccion = 1;
    }
    public Dama(int direccion){
        this.direccion = direccion > 0 ? 1 : -1;
    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int diffX =  posicionFinal.getX() - posicionInicial.getX();
        int diffY =  posicionFinal.getY() - posicionInicial.getY();
        if (Math.abs(diffY) == 2 && checkMiddleEmpty(posicionInicial, posicionFinal, tablero)) return false;
        return diffX * direccion > 0 && diagonal.movimientoValido(posicionInicial, posicionFinal, tablero);
    }

    private boolean checkMiddleEmpty(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int diffX =  posicionFinal.getX() - posicionInicial.getX();
        int diffY =  posicionFinal.getY() - posicionInicial.getY();
        Posicion middle = new Posicion(posicionInicial.getX() + diffX/2, posicionInicial.getY() + diffY/2);
        return !tablero.tienePieza(middle);
    }
}
