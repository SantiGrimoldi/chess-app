package common.validadoresDeJuego;

import common.Posicion;
import common.Tablero;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class ValidadorAnd implements ValidadorDeJuego{
    private Boolean keepTurn = false;
    List<ValidadorDeJuego> validadores;


    public ValidadorAnd(List<ValidadorDeJuego> validadores) {
        this.validadores = validadores;
    }


    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, PlayerColor color) {
        for (ValidadorDeJuego validador : validadores) {
            ResultSet resultSet = validador.validarJuego(posicionInicial, posicionFinal, tablero, color);
            if (resultSet.getInvalid() || resultSet.getWin()) {
                return resultSet;
            }
            else {
                tablero = resultSet.getTablero();
                if (resultSet.keepTurn()) {
                    keepTurn = true;
                }
            }

        }
        if (keepTurn){
            keepTurn = false;
            return new ResultSet(tablero, "Movimiento valido", true, false, true);
        }
        return new ResultSet(tablero, "Movimiento valido", true, false);

    }

    public ValidadorAnd addValidador(ValidadorDeJuego validador) {
        List<ValidadorDeJuego> validadores = new ArrayList<>(this.validadores);
        validadores.add(validador);
        return new ValidadorAnd(validadores);
    }
}
