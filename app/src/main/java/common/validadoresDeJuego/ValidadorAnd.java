package common.validadoresDeJuego;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class ValidadorAnd implements gameValidator {
    private Boolean keepTurn = false;
    List<gameValidator> validators;


    public ValidadorAnd(List<gameValidator> validators) {
        this.validators = validators;
    }


    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        for (gameValidator validador : validators) {
            ResultSet resultSet = validador.validateGame(initialPosition, finalPosition, board, color);
            if (resultSet.getInvalid() || resultSet.getWin()) {
                return resultSet;
            }
            else {
                board = resultSet.getBoard();
                if (resultSet.keepTurn()) {
                    keepTurn = true;
                }
            }

        }
        if (keepTurn){
            keepTurn = false;
            return new ResultSet(board, "Movimiento valido", true, false, true);
        }
        return new ResultSet(board, "Movimiento valido", true, false);

    }

    public ValidadorAnd addValidador(gameValidator validador) {
        List<gameValidator> validadores = new ArrayList<>(this.validators);
        validadores.add(validador);
        return new ValidadorAnd(validadores);
    }
}
