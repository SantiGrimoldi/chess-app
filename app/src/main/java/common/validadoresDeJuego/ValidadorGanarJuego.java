package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;
import common.interfaces.CondicionGanadora;

import java.util.List;

public class ValidadorGanarJuego implements ValidadorDeJuego {
    private final List<CondicionGanadora> condiciones;

    public ValidadorGanarJuego(List<CondicionGanadora> condiciones) {
        this.condiciones = condiciones;
    }

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        tablero = tablero.moverPieza(posicionInicial, posicionFinal, tablero.obtenerPieza(posicionInicial));
        for (CondicionGanadora condicion : condiciones) {
            if (condicion.condicionGanadora(posicionInicial, posicionFinal, tablero, usuario)) {
                return new ResultSet(true, tablero, "Ganaste");
            }
        }
        return new ResultSet(tablero, "Segui jugando", false, false);
    }
}
