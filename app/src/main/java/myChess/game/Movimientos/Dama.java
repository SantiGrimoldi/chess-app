package myChess.game.Movimientos;

import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;

public class Dama implements Movimiento{
    private final Diagonal diagonal = new Diagonal(true, 2);
    private final int direccion;
    private final boolean rey;

    public Dama(){
        this.direccion = 1;
        this.rey = false;
    }
    public Dama(int direccion){
        this.direccion = direccion > 0 ? 1 : -1;
        this.rey = false;
    }
    public Dama(boolean rey){
        this.direccion = 0;
        this.rey = rey;
    }

    @Override
    public Boolean movimientoValido(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        if (!tablero.estaDentoDeLosLimites(posicionFinal)) return false;
        int diffX =  posicionFinal.getX() - posicionInicial.getX();
        int diffY =  posicionFinal.getY() - posicionInicial.getY();
        if (Math.abs(diffY) >= 2 && wrongMiddlePiece(posicionInicial, posicionFinal, tablero)) return false;
        return isRightDirection(diffX) && diagonal.movimientoValido(posicionInicial, posicionFinal, tablero);
    }

    private boolean wrongMiddlePiece(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        return checkMiddleEmpty(posicionInicial, posicionFinal, tablero) || checkColorJump(posicionInicial, posicionFinal, tablero);
    }

    private boolean isRightDirection(int diffX) {
        if (rey) return true;
        return diffX * direccion > 0;
    }

    private boolean checkMiddleEmpty(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int diffX =  posicionFinal.getX() - posicionInicial.getX() > 0 ? 1 : -1;
        int diffY =  posicionFinal.getY() - posicionInicial.getY()> 0 ? 1 : -1;
        Posicion middle = new Posicion(posicionInicial.getX() + diffX, posicionInicial.getY() + diffY);
        return !tablero.tienePieza(middle);
    }

    private boolean checkColorJump(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        int diffX =  posicionFinal.getX() - posicionInicial.getX() > 0 ? 1 : -1;
        int diffY =  posicionFinal.getY() - posicionInicial.getY()> 0 ? 1 : -1;
        Pieza pieza = tablero.obtenerPieza(posicionInicial);
        Pieza intermedia = tablero.obtenerPieza(new Posicion(posicionInicial.getX() + diffX, posicionInicial.getY() + diffY));
        if (intermedia == null || pieza == null) return false;
        return intermedia.getOwner().getColor() == pieza.getOwner().getColor();
    }

}
