package common;

import common.interfaces.MovimientoEspecial;
import common.Movimientos.MovimientosEspeciales;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Transformaciones implements MovimientoEspecial {

    Pieza piezaActual;
    Pieza proximaPieza;
    int row;

    public Transformaciones(Pieza piezaActual, Pieza proximaPieza, int row) {
        this.piezaActual = piezaActual;
        this.proximaPieza = proximaPieza;
        this.row = row;
    }

    @Override
    public Tablero MovimientoEspecial(Pieza piece, Posicion inicial, Posicion post, Tablero tablero) {
        Map<Posicion, Pieza> piezas = tablero.getTodasLasPiezas();
        AtomicBoolean isPiezaIntercambiable = new AtomicBoolean(false);
        piezas.forEach((posicion, pieza) -> {
            if (posicion.getX() == row && isPiezaIntercambiable(pieza)) {
                proximaPieza = proximaPieza.setId(pieza.getId());
                isPiezaIntercambiable.set(true);
            }
        });
        if (isPiezaIntercambiable.get()) {
            return actualizarTablero(post, tablero);
        }
        return tablero;
    }

    private Tablero actualizarTablero ( Posicion post, Tablero tablero) {
        return tablero.forzarMovimiento(proximaPieza, post);
    }

    private boolean isPiezaIntercambiable(Pieza pieza) {
        return pieza.getMovimientosEspeciales().contains(MovimientosEspeciales.PRIMERO) &&
                piezaActual.equals(pieza);

    }
}
