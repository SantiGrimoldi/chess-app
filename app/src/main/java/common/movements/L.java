package common.movements;

import common.interfaces.Movement;
import common.Position;
import common.Board;

public class L implements Movement {
    public L() {

    }

    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        return isLMovement(initialPosition, finalPosition);
    }

    private boolean isLMovement(Position initialPosition, Position finalPosition){
        int x0 = initialPosition.getX();
        int x1 = finalPosition.getX();
        int y0 = initialPosition.getY();
        int y1 = finalPosition.getY();
        int diffX = Math.abs(x0 - x1);
        int diffY = Math.abs(y0 - y1);
        return (diffX == 1 && diffY == 2) || (diffX == 2 && diffY == 1);
    }

}
