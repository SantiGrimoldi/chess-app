package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;

import java.util.List;

public class ValidadorAnd implements ValidadorDeJuego{
    List<ValidadorDeJuego> validadores;


    public ValidadorAnd(List<ValidadorDeJuego> validadores) {
        this.validadores = validadores;
    }


    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        for (ValidadorDeJuego validador : validadores) {
            ResultSet resultSet = validador.validarJuego(posicionInicial, posicionFinal, tablero, usuario);
            if (resultSet.getInvalid()) {
                return resultSet;
            }
            else {
                tablero = resultSet.getTablero();
            }

        }
        return new ResultSet(tablero, "Movimiento valido", true, false);
    }
}
