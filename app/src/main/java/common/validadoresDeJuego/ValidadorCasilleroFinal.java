package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;

public class ValidadorCasilleroFinal implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.tienePieza(posicionFinal) && tablero.obtenerPieza(posicionFinal).getColor() == usuario.getColor()) {
            return new ResultSet(tablero, "No puedes comer tu propia pieza", false, true);
        }
        if (!tablero.tienePieza(posicionInicial)) return new ResultSet(tablero, "No hay pieza en el casillero inicial", false, true);
        return new ResultSet(tablero, "Ambos casilleros son validos", false, false);
    }
}
