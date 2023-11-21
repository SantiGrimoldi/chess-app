package myChess.game.validadoresDeJuego;

import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.gameValidator;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Piece;
import common.Position;
import common.Board;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class JaqueMate implements gameValidator {
    Jaque jaque = new Jaque();

    @Override
    public ResultSet validateGame(Position initialPosition, Position finalPosition, Board board, PlayerColor color) {
        color = color == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        Position posRey = getPosRey(board, color);
        if (jaque.estoyEnJaque(posRey, board, color)) {
            if (!piezaSalvaJaque(board, color)){
                return new ResultSet(true, board,"Jaque Mate");
            }
        }
        return new ResultSet(board,"No estas en jaque", false, false);
    }

    private static Position getPosRey(Board board, PlayerColor color) {
        return board.kings().stream().filter((pos) -> board.getPiece(pos).getColor() == color).toList().get(0);
    }

    private boolean piezaSalvaJaque(Board board, PlayerColor color){
        AtomicBoolean salva = new AtomicBoolean(false);
        Map<Position, Piece> piezas = board.getAllPieces();
        // filtro solo las piezas del color en jaque
        piezas = obtenerLasPiezasDeMiColor(color, piezas);
        piezas.forEach((pos, pieza) ->{
            for (int i = 0; i< board.rowsSize(); i++){
                for (int j = 0; j< board.columnsSize(); j++){
                    Position position = new Position(i,j);
                    if (jaque.salgoDeJaque(pos, position, board, color) &&
                            DestinoPosible(position, board, color, pos, pieza)) {
                        salva.set(true);
                    }
                }
            }
        });
        return salva.get();
    }

    private static boolean DestinoPosible(Position position, Board board, PlayerColor color, Position pos, Piece piece) {
        return piece.isValidMovement(pos, position, board) &&
                isEmptyOrHasOpponentPiece(board, color, position);
    }

    @NotNull
    private static Map<Position, Piece> obtenerLasPiezasDeMiColor(PlayerColor color, Map<Position, Piece> piezas) {
        return piezas.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean isEmptyOrHasOpponentPiece(Board board, PlayerColor usuario, Position position) {
        return (board.hasPiece(position) && board.getPiece(position).getColor() != usuario) || !board.hasPiece(position);
    }


}
