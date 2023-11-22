package myChess.game.gameValidators;

import common.gameValidator.ResultSet;
import common.gameValidator.GameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Piece;
import common.Position;
import common.Board;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class CheckMate implements GameValidator {
    Check check = new Check();

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        color = color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        Position posRey = getPosKing(board, color);
        if (check.isInCheck(posRey, board, color)) {
            if (!pieceSavesCheck(board, color)){
                return new ResultSet(true, board,"Jaque Mate");
            }
        }
        return new ResultSet(board,"No estas en jaque", false, false);
    }

    private static Position getPosKing(Board board, PlayerColor color) {
        return board.kings().stream().filter((pos) -> board.getPiece(pos).getColor() == color).toList().get(0);
    }

    private boolean pieceSavesCheck(Board board, PlayerColor color){
        AtomicBoolean saves = new AtomicBoolean(false);
        Map<Position, Piece> pieces = board.getAllPieces();
        // filtro solo las piezas del color en jaque
        pieces = getMyPieces(color, pieces);
        pieces.forEach((pos, piece) ->{
            for (int i = 0; i< board.rowsSize(); i++){
                for (int j = 0; j< board.columnsSize(); j++){
                    Position position = new Position(i,j);
                    if (check.getsOutOfCheck(pos, position, board, color) &&
                            possibleDestination(position, board, color, pos, piece)) {
                        saves.set(true);
                    }
                }
            }
        });
        return saves.get();
    }

    private static boolean possibleDestination(Position position, Board board, PlayerColor color, Position pos, Piece piece) {
        return piece.isValidMovement(pos, position, board) &&
                isEmptyOrHasOpponentPiece(board, color, position);
    }

    @NotNull
    private static Map<Position, Piece> getMyPieces(PlayerColor color, Map<Position, Piece> pieces) {
        return pieces.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean isEmptyOrHasOpponentPiece(Board board, PlayerColor user, Position position) {
        return (board.hasPiece(position) && board.getPiece(position).getColor() != user) || !board.hasPiece(position);
    }


}
