package common.gameValidator;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

public class MoveYourPieceValidator implements GameValidator {
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (board.hasPiece(initialPosition) && board.getPiece(initialPosition).getColor() != color) {
            return new ResultSet(board, "No es tu pieza", false, true);
        }
        if (!board.hasPiece(initialPosition)) {
            return new ResultSet(board, "No hay pieza en el casillero inicial", false, true);
        }
        return new ResultSet(board, "Es tu pieza", false, false);
    }
}
