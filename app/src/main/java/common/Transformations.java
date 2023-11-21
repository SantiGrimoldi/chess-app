package common;

import common.interfaces.SpecialMovement;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class Transformations implements SpecialMovement {

    Piece actualPiece;
    Piece nextPiece;
    int row;

    public Transformations(Piece actualPiece, Piece nextPiece, int row) {
        this.actualPiece = actualPiece;
        this.nextPiece = nextPiece;
        this.row = row;
    }

    @Override
    public Board makeSpecialMovement(Piece piece, Position initial, Position post, Board board) {
        Map<Position, Piece> piezas = board.getAllPieces();
        AtomicBoolean isPiezaIntercambiable = new AtomicBoolean(false);
        piezas.forEach((posicion, otherPiece) -> {
            if (posicion.getX() == row && pieceFitsDescription(otherPiece)) {
                nextPiece = nextPiece.setId(otherPiece.getId());
                isPiezaIntercambiable.set(true);
            }
        });
        if (isPiezaIntercambiable.get()) {
            return updateBoard(post, board);
        }
        return board;
    }

    private Board updateBoard(Position post, Board board) {
        return board.forceMovements(nextPiece, post);
    }

    private boolean pieceFitsDescription(Piece piece) {
        return actualPiece.equals(piece);

    }
}
