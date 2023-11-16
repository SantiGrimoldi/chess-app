package common.conectUI;

import common.NombrePieza;
import common.Pieza;
import common.Posicion;
import common.Tablero;
import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UIAdapter {
    public static BoardSize adaptBoard(Tablero board){
        return new BoardSize(board.getFilas(), board.getColumnas());
    }
    public static Position adaptPosition(Posicion position){
        return new Position(position.getX() +1 , position.getY() +1 );
    }

    public static List<ChessPiece> adaptPieces(Tablero board){

        List<ChessPiece> piezasNuevas = new ArrayList<>();
        Map<Posicion, Pieza> piezasViejas = board.getTodasLasPiezas();
        piezasViejas.forEach((posicion, pieza) -> {
            piezasNuevas.add(
                    new ChessPiece(
                            pieza.getId(),
                            pieza.getColor(),
                            adaptPosition(posicion),
                            adaptName(pieza.getNombre())));
        });

        return piezasNuevas;
    }
    private static String adaptName(NombrePieza name){

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
