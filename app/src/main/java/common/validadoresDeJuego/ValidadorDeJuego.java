package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;

public interface ValidadorDeJuego {
    public ResultSet validarJuego(
            Posicion posicionInicial,
            Posicion posicionFinal,
            Tablero tablero,
            User usuario);
}
