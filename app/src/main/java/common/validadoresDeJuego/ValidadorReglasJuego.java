package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;
import common.interfaces.ReglasJuegoMovimiento;

import java.util.List;

public class ValidadorReglasJuego implements ValidadorDeJuego{

    private List<ReglasJuegoMovimiento> reglasJuego;

    public ValidadorReglasJuego(List<ReglasJuegoMovimiento> reglasJuego) {
        this.reglasJuego = reglasJuego;
    }

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        for (ReglasJuegoMovimiento regla : reglasJuego) {
            if (!regla.movValidoJuego(posicionInicial, posicionFinal, tablero, usuario)) {
                return new ResultSet(tablero, "No cumple las reglas", false, true);
            }
        }
        return new ResultSet(tablero, "Movimiento valido", false, false);
    }
}
