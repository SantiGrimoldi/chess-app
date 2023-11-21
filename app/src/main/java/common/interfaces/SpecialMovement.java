package common.interfaces;

import common.Piece;
import common.Position;
import common.Board;

public interface SpecialMovement {

    Board makeSpecialMovement(Piece piece, Position initial, Position post, Board board);

}
