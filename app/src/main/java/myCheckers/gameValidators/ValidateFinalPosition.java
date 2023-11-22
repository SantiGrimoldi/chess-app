package myCheckers.gameValidators;

import common.Position;
import common.Board;
import common.gameValidator.ResultSet;
import common.gameValidator.GameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;

public class ValidateFinalPosition implements GameValidator {
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (board.hasPiece(finalPosition)) return new ResultSet(board, "No puedes mover a un casillero ocupado", false, true);
        return new ResultSet(board, "Casillero final valido", false, false);
    }
}
