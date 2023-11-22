package common.movements;

import common.interfaces.Movement;
import common.Position;
import common.Board;

public class Diagonal implements Movement {
    private final boolean canJump;
    private final int distance;

    public Diagonal(boolean canJump, int distance) {
        this.canJump = canJump;
        this.distance = distance;
    }

    public Diagonal (boolean canJump) {
        this.canJump = canJump;
        this.distance = Integer.MAX_VALUE;
    }

    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        if (initialPosition.equals(finalPosition)) return false;
        if (isDiagonal(initialPosition, finalPosition)) {
            if (!canJump) {
                return hasFreePath(initialPosition, finalPosition, board);
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isDiagonal(Position positionInicial, Position positionFinal){
        int x0 = positionInicial.getX();
        int x1 = positionFinal.getX();
        int y0 = positionInicial.getY();
        int y1 = positionFinal.getY();
        int diffX = Math.abs(x0 - x1);
        int diffY = Math.abs(y0 - y1);
        return diffY == diffX && distance >= diffX;
    }

    public boolean hasFreePath(Position positionInicial, Position positionFinal, Board board) {
        int x0 = positionInicial.getX();
        int x1 = positionFinal.getX();
        int y0 = positionInicial.getY();
        int y1 = positionFinal.getY();
        int diffX = x0 < x1 ? 1 : -1;
        int diffY = y0 < y1 ? 1 : -1;
        for (int i = x0 + diffX, j = y0 + diffY; i != x1 && j!= y1; i += diffX, j += diffY) {
            if (board.hasPiece(new Position(i, j))) return false;
        }
        return true;
    }
}
