package common.interfaces;

import common.Position;
import common.Board;

public interface Movement {
    public Boolean isMovementValid(Position initialPosition, Position finalPosition, Board board);
}
