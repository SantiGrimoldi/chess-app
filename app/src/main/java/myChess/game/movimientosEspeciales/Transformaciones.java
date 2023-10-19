package myChess.game.movimientosEspeciales;

import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;

public class Transformaciones implements MovimientoEspecial{

    Pieza piezaActual;
    Pieza proximaPieza;

    @Override
    public Tablero MovimientoEspecial(Pieza pieza, Posicion inicial, Posicion post, Tablero tablero) {
        if (pieza.movimientoValido(inicial, post, tablero) && isPiezaIntercambiable(pieza)) {
            return actualizarTablero(inicial, post, tablero);
        }
        return tablero;
    }

    private Tablero actualizarTablero (Posicion inicial, Posicion post, Tablero tablero) {
        tablero = tablero.forzarMovimiento(proximaPieza, post);
        tablero = tablero.eliminarPieza(inicial);
        return tablero;
    }

    private boolean isPiezaIntercambiable(Pieza pieza) {
        return pieza.getMovimientosEspeciales().contains(MovimientosEspeciales.PRIMERO) &&
                piezaActual.equals(pieza);
    }
}
