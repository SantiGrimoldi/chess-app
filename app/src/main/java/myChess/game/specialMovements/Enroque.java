package myChess.game.specialMovements;

import common.movements.SpecialMovements;
import common.interfaces.SpecialMovement;
import common.PieceNames;
import common.Piece;
import common.Position;
import common.Board;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Enroque implements SpecialMovement {
    @Override
    public Board makeSpecialMovement(Piece piece, Position initial, Position post, Board board) {
        if (notKing(piece)) return board;
        Piece torre = board.getPiece(post);
        if (torre == null) return board;
        if (esEnrocable(torre)) {
            if (freePath(initial, post, board, torre)){
                return enrocar(piece, initial, post, board, torre);
            }
        }
        return board;
    }

    private static Board enrocar(Piece piece, Position inicial, Position post, Board board, Piece torre) {
        Position enroqueRey = newPosition(inicial, post, 2);
        Position enroqueTorre = newPosition(inicial, post, 1);
        Piece torreNueva = cleanSpecialMovements(torre);
        Piece reyNuevo = cleanSpecialMovements(piece);
        return updateBoard(inicial, post, board, reyNuevo, enroqueRey, torreNueva, enroqueTorre);
    }

    private static Board updateBoard(Position inicial, Position post, Board board, Piece reyNuevo, Position enroqueRey, Piece torreNueva, Position enroqueTorre) {
        board = board.forceMovements(reyNuevo, enroqueRey);
        board = board.forceMovements(torreNueva, enroqueTorre);
        board = board.deletePiece(inicial);
        board = board.deletePiece(post);
        return board;
    }

    private static Piece cleanSpecialMovements(Piece piece) {
        return piece.takeSpecialMovements(List.of(SpecialMovements.ENROQUE));
    }

    @NotNull
    private static Position newPosition(Position inicial, Position post, int x) {
        return inicial.getY() < post.getY() ? new Position(inicial.getX(), inicial.getY() + x) : new Position(inicial.getX(), inicial.getY() - x);
    }

    private static Boolean freePath(Position inicial, Position post, Board board, Piece torre) {
        return torre.isValidMovement(post, inicial, board);
    }

    private static boolean esEnrocable(Piece piece) {
        return piece.getSpecialMovements().contains(SpecialMovements.ENROQUE);
    }

    private boolean notKing(Piece piece) {
        return !esEnrocable(piece) || piece.getName() != PieceNames.REY;
    }
}
