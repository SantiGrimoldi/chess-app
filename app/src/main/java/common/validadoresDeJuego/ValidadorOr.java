package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import common.User;

import java.util.List;

public class ValidadorOr implements ValidadorDeJuego{

    List<ValidadorDeJuego> validadores;

    public ValidadorOr(List<ValidadorDeJuego> validadores) {
        this.validadores = validadores;
    }
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        for (ValidadorDeJuego validador : validadores) {
            ResultSet resultSet = validador.validarJuego(posicionInicial, posicionFinal, tablero, usuario);
            if (resultSet.getReturnable()) {
                return resultSet;
            }
        }
        return new ResultSet(tablero, "Movimiento invalido", false, true);
    }
}
