package myChess.game.validadoresDeJuego;

import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.gameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Piece;
import common.Position;
import common.Board;

import java.util.List;

public class Jaque implements gameValidator {
    public static Boolean jaque (Position posPieza, Position posRey, Board board) {
        try{
            if (!board.hasPiece(posPieza)) return false;
            Piece piece = board.getPiece(posPieza);
            if (piece.isValidMovement(posPieza, posRey, board)) {
                return true;
            }
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean estoyEnJaque (Position rey, Board board, PlayerColor jugador) {

        for (int i = 0; i< board.rowsSize(); i++){
            for (int j = 0; j< board.columnsSize(); j++){
                Position position = new Position(i,j);
                if (board.hasPiece(position) && board.getPiece(position).getColor() != jugador) {
                    if (Jaque.jaque(position, rey, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean salgoDeJaque (Position positionInicial, Position positionFinal, Board board, PlayerColor jugador) {
        Board boardAux = board.moovePiece(positionInicial, positionFinal, board.getPiece(positionInicial));
        List<Position> reyes = boardAux.kings();
        if (reyes.isEmpty())  return true;
        for (Position rey : reyes) {
            if (boardAux.getPiece(rey).getColor() == jugador) {
                return !estoyEnJaque(rey, boardAux, jugador);
            }
        }
        return false;
    }



    public boolean movValidoJuego(Position inicial, Position post, Board board, PlayerColor color) {
        return salgoDeJaque(inicial, post, board, color);
    }

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (movValidoJuego(initialPosition, finalPosition, board, color)) {
            return new ResultSet(board, "Movimiento valido", false, false);
        }
        return new ResultSet(board, "Estas en jaque o pieza pineada", false, true);
    }
}
