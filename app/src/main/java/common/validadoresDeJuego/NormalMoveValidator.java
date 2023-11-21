package common.validadoresDeJuego;

import common.Piece;
import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

public class NormalMoveValidator implements gameValidator {

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (board.isWithinLimits(initialPosition) && board.isWithinLimits(finalPosition)) {
//
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
}
