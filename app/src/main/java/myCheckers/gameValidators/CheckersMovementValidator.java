package myCheckers.gameValidators;

import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.GameValidator;
import common.Piece;
import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

public class CheckersMovementValidator implements GameValidator {
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (board.hasPiece(finalPosition)) return new ResultSet(board, "No podes mover ahi", false, false);
        if (board.hasPiece(initialPosition)) {
            return validateMovement(initialPosition, finalPosition, board, color);
        }
        return new ResultSet(board, "No hay pieza en la posicion inicial", false, true);
    }

    private ResultSet validateMovement(Position positionInicial, Position positionFinal, Board board, PlayerColor color) {
        Piece piece = board.getPiece(positionInicial);
        if (piece.isValidMovement(positionInicial, positionFinal, board)){
            board = board.moovePiece(positionInicial, positionFinal, piece);
            board = cleanDiagonal(positionInicial, positionFinal, board);
            int diffX = Math.abs(positionFinal.getX() - positionInicial.getX());
            if (diffX == 2 && IsAbleToEatValidator.checkIfIsAbleToEat(board, color).getInvalid()) {
                return new ResultSet(board, "Sigue jugando", true, false, true);
            }
            return new ResultSet(board, "Segui jugando", true, false);
        }
        else {
            return new ResultSet(board, "Movimiento invalido", false, true);
        }
    }

    private Board cleanDiagonal(Position inicial, Position ultima, Board board) {
        if (inicial.equals(ultima)) return board;
        int direccionX = ultima.getX() - inicial.getX() > 0 ? 1 : -1;
        int direccionY = ultima.getY() - inicial.getY() > 0 ? 1 : -1;
        board = board.deletePiece(inicial);
        return cleanDiagonal(new Position(inicial.getX() + direccionX, inicial.getY() + direccionY), ultima, board);
    }
}
