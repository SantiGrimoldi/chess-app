package myChess.conectUI;

import edu.austral.dissis.chess.gui.*;
import edu.austral.dissis.chess.gui.Position;
import myChess.game.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Conection {
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
                            "pieza"+pieza.getOwner().getName()+pieza.getNombre()+posicion.getX()+posicion.getY(),
                            pieza.getOwner().getColor(),
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
            default -> "empty";
        };
    }


}
