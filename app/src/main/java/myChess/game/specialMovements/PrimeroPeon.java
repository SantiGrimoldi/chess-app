package myChess.game.specialMovements;

import common.Movimientos.MovimientosEspeciales;
import common.PieceNames;
import common.interfaces.SpecialMovement;
import common.Piece;
import common.Position;
import common.Board;
import common.Movimientos.Vertical;

import java.util.List;

public class PrimeroPeon implements SpecialMovement {
    @Override
    public Board makeSpecialMovement(Piece piece, Position initial, Position post, Board board) {
        if (!piece.getSpecialMovements().contains(MovimientosEspeciales.PRIMERO)) return board;
        if (board.hasPiece(post)) return board;
        int diffx = post.getX() - initial.getX();
        int diffy = post.getY() - initial.getY();
        if (diffy == 0 && Math.abs(diffx) == 2 & piece.getName() == PieceNames.PEON) {
            if (new Vertical(false).isMovementValid(initial, post, board)) {
                Piece pawn = piece.takeSpecialMovements(List.of(MovimientosEspeciales.PRIMERO));
                board = board.forceMovements(pawn, post);
                board = board.deletePiece(initial);
            }
        }
        return board;
    }
}
