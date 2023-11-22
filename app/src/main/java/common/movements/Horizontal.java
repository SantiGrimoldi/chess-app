package common.movements;

import common.interfaces.Movement;
import common.Position;
import common.Board;

public class Horizontal implements Movement {

    private final boolean canJump;
    private final int distance;

    public Horizontal(boolean canJump, int distance) {
        this.canJump = canJump;
        this.distance = distance;
    }

    public Horizontal (boolean canJump) {
        this.canJump = canJump;
        this.distance = Integer.MAX_VALUE;
    }

    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        if (isHorizontal(initialPosition, finalPosition)) {
            if (!canJump) {
                return hasFreePath(initialPosition, finalPosition, board);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isHorizontal(Position initialPosition, Position finalPosition){
        int x0 = initialPosition.getX();
        int x1 = finalPosition.getX();
        int y0 = initialPosition.getY();
        int y1 = finalPosition.getY();
        return x0 == x1 && y0 != y1 && distance >= Math.abs(y0 - y1);
    }

    public boolean hasFreePath(Position initialPosition, Position finalPosition, Board board)  {
        int y0 = initialPosition.getY();
        int y1 = finalPosition.getY();
        int x = initialPosition.getX();
        int diffY = y0 < y1 ? 1 : -1;
        if (Math.abs(y0 - y1) > distance) return false;
        for (int i = y0 + diffY; i != y1; i += diffY) {
            if (board.hasPiece(new Position(x, i))) return false;
        }
        return true;
    }

}
