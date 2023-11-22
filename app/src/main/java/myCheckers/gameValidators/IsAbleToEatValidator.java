package myCheckers.gameValidators;

import common.gameValidator.ResultSet;
import common.gameValidator.GameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Piece;
import common.Position;
import common.Board;

public class IsAbleToEatValidator implements GameValidator {
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        int diffX = Math.abs(finalPosition.getX() - initialPosition.getX());
        if (diffX > 1) {
            return new ResultSet(board,"movimiento valido", false, false);
        }
        else {
            return checkIfIsAbleToEat(board, color);
        }
    }

    public static ResultSet checkIfIsAbleToEat(Board board, PlayerColor color) {
        for (int i = 0; i < board.columnsSize(); i++) {
            for (int j = 0; j < board.rowsSize(); j++) {
                Position origen = new Position(i, j);
                if (board.hasPiece(origen) && board.getPiece(origen).getColor() == color) {
                    ResultSet resultSet = AllPositions(origen, board);
                    if (resultSet != null) {
                        return resultSet;
                    }
                }
            }
        }
        return new ResultSet(board, "Movimiento valido", false, false);
    }

    private static ResultSet AllPositions(Position inicio, Board board) {
        for (int i = 0; i < board.columnsSize(); i++) {
            for (int j = 0; j < board.rowsSize(); j++) {
                Position destino = new Position(i, j);
                if (inicio.equals(destino)) continue;
                if (!board.hasPiece(destino)) {
                    Piece piece = board.getPiece(inicio);
                    if (piece.isValidMovement(inicio, destino, board) && Math.abs(inicio.getX() - destino.getX()) > 1) {
                        return new ResultSet(board, "Movimiento invalido, puedo comer", false, true);
                    }
                }
            }
        }
        return null;
    }
}
