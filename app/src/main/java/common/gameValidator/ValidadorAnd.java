package common.gameValidator;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.ArrayList;
import java.util.List;

public class ValidadorAnd implements GameValidator {
    private Boolean keepTurn = false;
    List<GameValidator> validators;


    public ValidadorAnd(List<GameValidator> validators) {
        this.validators = validators;
    }


    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        for (GameValidator validador : validators) {
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

    public ValidadorAnd addValidador(GameValidator validador) {
        List<GameValidator> validadores = new ArrayList<>(this.validators);
        validadores.add(validador);
        return new ValidadorAnd(validadores);
    }
}
