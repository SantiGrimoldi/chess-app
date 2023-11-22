package common.gameValidator;

import common.Position;
import common.Board;
import common.interfaces.SpecialMovement;
import edu.austral.dissis.chess.gui.PlayerColor;

import java.util.List;

public class SpecialMoveValidator implements GameValidator {
    private final List<SpecialMovement> specialMovements;

    public SpecialMoveValidator(List<SpecialMovement> specialMovements) {
        this.specialMovements = specialMovements;
    }

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        for (SpecialMovement specialMovement : specialMovements) {
            Board boardAux = specialMovement.makeSpecialMovement(board.getPiece(initialPosition), initialPosition, finalPosition, board);
            if (boardAux != board) {
                return new ResultSet(boardAux, "Movimiento especial valido", true, false);
            }
        }
        return new ResultSet(board, "No es un movimiento especial", false, false);
    }
}
