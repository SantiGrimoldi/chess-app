package myCheckers.gameValidators;

import common.Position;
import common.Board;
import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.GameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;

public class CheckMoveIfValid implements GameValidator {

    private GameValidator condition;
    public CheckMoveIfValid(GameValidator condition) {
        this.condition = condition;
    }

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        ResultSet result = condition.validateGame(initialPosition, finalPosition, board, color);
        if (result.getReturnable()) {
            board = result.getBoard();
            if (IsAbleToEatValidator.checkIfIsAbleToEat(board, color).getInvalid()) return new ResultSet(board, "Sigue jugando", true, false, true);

        }
        return result;
    }
}
