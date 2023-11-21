package common.validadoresDeJuego;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public class ValidadorOr implements gameValidator {

    List<gameValidator> validators;

    public ValidadorOr(List<gameValidator> validators) {
        this.validators = validators;
    }
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        for (gameValidator validador : validators) {
            ResultSet resultSet = validador.validateGame(initialPosition, finalPosition, board, color);
            if (resultSet.getReturnable() || resultSet.getWin()) {
                return resultSet;
            }
        }
        return new ResultSet(board, "Movimiento invalido", false, true);
    }
}
