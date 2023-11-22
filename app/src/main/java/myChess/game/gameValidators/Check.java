package myChess.game.gameValidators;

import common.gameValidator.ResultSet;
import common.gameValidator.GameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Piece;
import common.Position;
import common.Board;

import java.util.List;

public class Check implements GameValidator {
    public static Boolean check(Position posPiece, Position posKing, Board board) {
        try{
            if (!board.hasPiece(posPiece)) return false;
            Piece piece = board.getPiece(posPiece);
            if (piece.isValidMovement(posPiece, posKing, board)) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean isInCheck(Position king, Board board, PlayerColor player) {

        for (int i = 0; i< board.rowsSize(); i++){
            for (int j = 0; j< board.columnsSize(); j++){
                Position position = new Position(i,j);
                if (analyzeCheck(king, board, player, position)) return true;
            }
        }
        return false;
    }

    private static boolean analyzeCheck(Position king, Board board, PlayerColor player, Position position) {
        if (board.hasPiece(position) && board.getPiece(position).getColor() != player) {
            if (Check.check(position, king, board)) {
                return true;
            }
        }
        return false;
    }

    public boolean getsOutOfCheck(Position initial, Position post, Board board, PlayerColor player) {
        Board boardAux = board.movePiece(initial, post, board.getPiece(initial));
        List<Position> kings = boardAux.kings();
        if (kings.isEmpty())  return true;
        for (Position rey : kings) {
            if (boardAux.getPiece(rey).getColor() == player) {
                return !isInCheck(rey, boardAux, player);
            }
        }
        return false;
    }



    public boolean validGameMove(Position initial, Position post, Board board, PlayerColor color) {
        return getsOutOfCheck(initial, post, board, color);
    }

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (validGameMove(initialPosition, finalPosition, board, color)) {
            return new ResultSet(board, "Movimiento valido", false, false);
        }
        return new ResultSet(board, "Estas en jaque o pieza pineada", false, true);
    }
}
