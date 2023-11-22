package common.validadoresDeJuego;

import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.PlayerColor;

public class FinalSquareValidator implements GameValidator {
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        if (board.hasPiece(finalPosition) && board.getPiece(finalPosition).getColor() == color) {
            return new ResultSet(board, "No puedes comer tu propia pieza", false, true);
        }
        return new ResultSet(board, "Ambos casilleros son validos", false, false);
    }
}
