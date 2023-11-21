package common.conectUI;

import common.PieceNames;
import common.Piece;
import common.Position;
import common.Board;
import edu.austral.dissis.chess.gui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UIAdapter {
    public static BoardSize adaptBoard(Board board){
        return new BoardSize(board.rowsSize(), board.columnsSize());
    }
    public static edu.austral.dissis.chess.gui.Position adaptPosition(Position position){
        return new edu.austral.dissis.chess.gui.Position(position.getX() +1 , position.getY() +1 );
    }

    public static List<ChessPiece> adaptPieces(Board board){

        List<ChessPiece> newPieces = new ArrayList<>();
        Map<Position, Piece> oldPieces = board.getAllPieces();
        oldPieces.forEach((posicion, pieza) -> {
            newPieces.add(
                    new ChessPiece(
                            pieza.getId(),
                            pieza.getColor(),
                            adaptPosition(posicion),
                            adaptName(pieza.getName())));
        });

        return newPieces;
    }
    private static String adaptName(PieceNames name){

        return switch (name) {
            case PEON -> "pawn";
            case TORRE -> "rook";
            case CABALLO -> "knight";
            case ALFIL -> "bishop";
            case REINA -> "queen";
            case REY -> "king";
            case ESPECIAL -> "chancellor";
            default -> "empty";
        };
    }


}
