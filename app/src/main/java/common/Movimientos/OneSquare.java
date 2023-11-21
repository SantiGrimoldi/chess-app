package common.Movimientos;

import common.interfaces.Movement;
import common.Position;
import common.Board;

public class OneSquare implements Movement {
    @Override
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board) {
        int x0 = initialPosition.getX();
        int x1 = finalPosition.getX();
        int y0 = initialPosition.getY();
        int y1 = finalPosition.getY();
        int diffX = x0 < x1 ? x1 - x0 : x0 - x1;
        int diffY = y0 < y1 ? y1 - y0 : y0 - y1;
        return diffY <= 1 && diffX <= 1 && diffX + diffY != 0;
    }
}
