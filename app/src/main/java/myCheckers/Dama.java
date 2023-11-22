package myCheckers;

import common.interfaces.Movement;
import common.movements.Diagonal;
import common.Piece;
import common.Position;
import common.Board;

public class Dama implements Movement {
    private final Diagonal diagonal = new Diagonal(true, 2);
    private final int direction;
    private final boolean rey;

    public Dama(){
        this.direction = 1;
        this.rey = false;
    }
    public Dama(int direction){
        this.direction = direction > 0 ? 1 : -1;
        this.rey = false;
    }
    public Dama(boolean rey){
        this.direction = 0;
        this.rey = rey;
    }

    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        if (!board.isWithinLimits(finalPosition)) return false;
        int diffX = getDifferenceX(initialPosition, finalPosition);
        int diffY = getDifferenceY(initialPosition, finalPosition);
        if (isJumping(diffY) && invalidMiddleSquare(initialPosition, finalPosition, board)) return false;
        return isRightDirection(diffX) && diagonal.isMovementValid(initialPosition, finalPosition, board);
    }

    private static boolean isJumping(int diffY) {
        return Math.abs(diffY) >= 2;
    }

    private static int getDifferenceY(Position positionInicial, Position positionFinal) {
        return positionFinal.getY() - positionInicial.getY();
    }

    private static int getDifferenceX(Position positionInicial, Position positionFinal) {
        return positionFinal.getX() - positionInicial.getX();
    }

    private boolean invalidMiddleSquare(Position positionInicial, Position positionFinal, Board board) {
        return checkMiddleEmpty(positionInicial, positionFinal, board) || checkColorJump(positionInicial, positionFinal, board);
    }

    private boolean isRightDirection(int diffX) {
        if (rey) return true;
        return diffX * direction > 0;
    }

    private boolean checkMiddleEmpty(Position positionInicial, Position positionFinal, Board board) {
        int diffX =  getDifferenceX(positionInicial, positionFinal) > 0 ? 1 : -1;
        int diffY =  getDifferenceY(positionInicial, positionFinal) > 0 ? 1 : -1;
        Position middle = new Position(positionInicial.getX() + diffX, positionInicial.getY() + diffY);
        return !board.hasPiece(middle);
    }

    private boolean checkColorJump(Position positionInicial, Position positionFinal, Board board) {
        int diffX =  getDifferenceX(positionInicial, positionFinal) > 0 ? 1 : -1;
        int diffY =  getDifferenceY(positionInicial, positionFinal) > 0 ? 1 : -1;
        Piece piece = board.getPiece(positionInicial);
        Piece intermedia = board.getPiece(new Position(positionInicial.getX() + diffX, positionInicial.getY() + diffY));
        if (intermedia == null || piece == null) return false;
        return intermedia.getColor() == piece.getColor();
    }

}
