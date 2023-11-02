package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;
import common.interfaces.MovimientoEspecial;

import java.util.List;

public class ValidadorMovimientosEspeciales implements ValidadorDeJuego{
    private List<MovimientoEspecial> movimientosEspeciales;

    public ValidadorMovimientosEspeciales(List<MovimientoEspecial> movimientosEspeciales) {
        this.movimientosEspeciales = movimientosEspeciales;
    }

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        for (MovimientoEspecial movimientoEspecial : movimientosEspeciales) {
            Tablero tableroAux = movimientoEspecial.MovimientoEspecial(tablero.obtenerPieza(posicionInicial), posicionInicial, posicionFinal, tablero);
            if (tableroAux != tablero) {
                return new ResultSet(tableroAux, "Movimiento especial valido", true, false);
            }
        }
        return new ResultSet(tablero, "No es un movimiento especial", false, false);
    }
}
