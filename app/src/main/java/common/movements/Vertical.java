package common.movements;

import common.interfaces.Movement;
import common.Position;
import common.Board;

public class Vertical implements Movement {
    private final boolean canJump;
    private final int distance;

    public Vertical (boolean canJump, int distance) {
        this.canJump = canJump;
        this.distance = distance;
    }

    public Vertical (boolean canJump) {
        this.canJump = canJump;
        this.distance = Integer.MAX_VALUE;
    }

    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        if (isVertical(initialPosition, finalPosition)) {
            if (!canJump) {
                return hasFreePath(initialPosition, finalPosition, board);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isVertical(Position inicial, Position post){
        int x0 = inicial.getX();
        int x1 = post.getX();
        int y0 = inicial.getY();
        int y1 = post.getY();
        return x0 != x1 && y0 == y1 && distance >= Math.abs(x0 - x1);
    }

    public boolean hasFreePath(Position initialPosition, Position finalPosition, Board board) {
        int x0 = initialPosition.getX();
        int x1 = finalPosition.getX();
        int y0 = initialPosition.getY();
        int y1 = finalPosition.getY();
        if (Math.abs(x0 - x1) > distance) return false;
        if (y0 != y1) return false;
        int diffX = x0 < x1 ? 1 : -1;
        for (int i = x0 + diffX ; i != x1; i += diffX) {
            if (board.hasPiece(new Position(i, y0))) return false;
        }
        return true;
    }
}
