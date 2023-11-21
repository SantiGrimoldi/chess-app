package common.validadoresDeJuego;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

public interface gameValidator {
    public ResultSet validateGame(
            Position initialPosition,
            Position finalPosition,
            Board board,
            PlayerColor color);
}
