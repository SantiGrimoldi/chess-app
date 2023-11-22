package myCheckers.gameValidators;

import common.gameValidator.ResultSet;
import common.gameValidator.GameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Piece;
import common.Position;
import common.Board;

import java.util.Map;
import java.util.stream.Collectors;

public class WinCheckersValidator implements GameValidator {
    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        Map<Position, Piece> pieces = board.getAllPieces();
        pieces = pieces.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        PlayerColor otherColor = color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        if (checkOtherEmpty(board, otherColor)) return new ResultSet(true, board, "Ganaste");
        if (isAbleToMove(pieces, board)) {
            return new ResultSet(board, "Movimiento valido", false, false);
        }
        else {
            return new ResultSet(true, board, "Ganaste");
        }
    }

    private boolean checkOtherEmpty(Board board, PlayerColor color){
        Map<Position, Piece> pieces = board.getAllPieces();
        pieces = pieces.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        if (pieces.isEmpty()) return true;
        else return false;
    }

    private boolean isAbleToMove(Map<Position, Piece> piezas, Board board) {
        if (piezas.isEmpty()) return false;
        for (Map.Entry<Position, Piece> entry : piezas.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            for (int i = 0; i < board.columnsSize(); i++) {
                for (int j = 0; j < board.rowsSize(); j++) {
                    Position destino = new Position(i, j);
                    if (position.equals(destino)) continue;
                    if (!board.hasPiece(destino)) {
                        if (piece.isValidMovement(position, destino, board)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
