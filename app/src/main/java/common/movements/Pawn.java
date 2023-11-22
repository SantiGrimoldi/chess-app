package common.movements;

import common.interfaces.Movement;
import common.Position;
import common.Board;

public class Pawn implements Movement {

    private final int direction;

    public Pawn(){
        this.direction = 1;
    }

    public Pawn(int direction){
        this.direction = direction > 0 ? 1 : -1;
    }

    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        int diffX = getDiffX(initialPosition, finalPosition);
        int diffY = getDiffY(initialPosition, finalPosition);
        return diffX * direction == 1 && diffY == 0 && !board.hasPiece(finalPosition)|| eatsInDiagonal(initialPosition, finalPosition, board);
    }

    private static int getDiffY(Position initialPosition, Position finalPosition) {
        return finalPosition.getY() - initialPosition.getY();
    }

    private static int getDiffX(Position initialPosition, Position finalPosition) {
        return finalPosition.getX() - initialPosition.getX();
    }

    private boolean eatsInDiagonal(Position initialPosition, Position finalPosition, Board board) {
        if (!board.hasPiece(finalPosition)) return false;
        if (board.getPiece(initialPosition).getOwner() == board.getPiece(finalPosition).getOwner()) return false;
        int diffX = getDiffX(initialPosition, finalPosition);
        int diffY = getDiffY(initialPosition, finalPosition);
        return diffX * direction == 1 && Math.abs(diffY) == 1;
    }
}
