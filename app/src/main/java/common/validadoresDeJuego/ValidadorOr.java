package common.validadoresDeJuego;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public class ValidadorOr implements GameValidator {

    List<GameValidator> validators;

    public ValidadorOr(List<GameValidator> validators) {
        this.validators = validators;
    }
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        for (GameValidator validador : validators) {
            ResultSet resultSet = validador.validateGame(initialPosition, finalPosition, board, color);
            if (resultSet.getReturnable() || resultSet.getWin()) {
                return resultSet;
            }
        }
        return new ResultSet(board, "Movimiento invalido", false, true);
    }
}
