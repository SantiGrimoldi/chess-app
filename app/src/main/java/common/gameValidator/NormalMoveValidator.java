package common.gameValidator;

import common.Piece;
import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

public class NormalMoveValidator implements GameValidator {
    private final GameValidator destinationValidator = new FinalSquareValidator();
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (board.isWithinLimits(initialPosition) && board.isWithinLimits(finalPosition)) {
            if (!isFinalSquareValid(initialPosition, finalPosition, board, board.getPiece(initialPosition)))
                return new ResultSet(board, "No puedes comer tu propia pieza", false, false);
            Piece piece = board.getPiece(initialPosition);
            if (piece.isValidMovement(initialPosition, finalPosition, board)) {
                Board newBoard = updateBoard(initialPosition, finalPosition, board, piece);
                return new ResultSet(newBoard, "Movimiento valido", true, false);
            }
        }
        return new ResultSet(board, "Movimiento invalido", false, false);
    }


    private Board updateBoard(Position positionInicial, Position positionFinal, Board board, Piece piece) {
        piece = piece.cleanSpecialMovements();
        Board boardNuevo = board.moovePiece(positionInicial, positionFinal, piece);
        return boardNuevo;
    }

    private boolean isFinalSquareValid(Position positionInicial, Position positionFinal, Board board, Piece piece) {
        return !destinationValidator.validateGame(positionInicial, positionFinal, board, piece.getColor()).getInvalid();
    }
}
