package myChess.game.movimientosEspeciales;

import common.Movimientos.MovimientosEspeciales;
import common.NombrePieza;
import common.interfaces.MovimientoEspecial;
import common.Pieza;
import common.Posicion;
import common.Tablero;
import common.Movimientos.Vertical;

import java.util.List;

public class PrimeroPeon implements MovimientoEspecial {
    @Override
    public Tablero MovimientoEspecial(Pieza pieza, Posicion inicial, Posicion post, Tablero tablero) {
        if (!pieza.getMovimientosEspeciales().contains(MovimientosEspeciales.PRIMERO)) return tablero;
        if (tablero.tienePieza(post)) return tablero;
        int diffx = post.getX() - inicial.getX();
        int diffy = post.getY() - inicial.getY();
        if (diffy == 0 && Math.abs(diffx) == 2 & pieza.getNombre() == NombrePieza.PEON) {
            if (new Vertical(false).movimientoValido(inicial, post, tablero)) {
                Pieza peon = pieza.sacarMovEspeciales(List.of(MovimientosEspeciales.PRIMERO));
                tablero = tablero.forzarMovimiento(peon, post);
                tablero = tablero.eliminarPieza(inicial);
            }
        }
        return tablero;
    }
}
