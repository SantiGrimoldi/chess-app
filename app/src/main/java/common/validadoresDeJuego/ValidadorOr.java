package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public class ValidadorOr implements ValidadorDeJuego{

    List<ValidadorDeJuego> validadores;

    public ValidadorOr(List<ValidadorDeJuego> validadores) {
        this.validadores = validadores;
    }
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        for (ValidadorDeJuego validador : validadores) {
            ResultSet resultSet = validador.validarJuego(posicionInicial, posicionFinal, tablero, color);
            if (resultSet.getReturnable() || resultSet.getWin()) {
                return resultSet;
            }
        }
        return new ResultSet(tablero, "Movimiento invalido", false, true);
    }
}
