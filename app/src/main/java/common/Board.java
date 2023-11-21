package common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final List<List<Piece>> pieceContainer;

    public Board(int x, int y) {
        pieceContainer = new ArrayList<>();
        for (int i=0; i<x;i++){
            List<Piece> columnas = new ArrayList<>();
            pieceContainer.add(columnas);
            for (int j=0; j<y;j++){
                columnas.add(null);
            }
        }
    }
    public Board(List<List<Piece>> pieceContainer) {
        this.pieceContainer = pieceContainer;
    }

    public Board addPiece(Piece piece, Position position) {
        List<List<Piece>> tableroAux = clonate();
        tableroAux.get(position.getX()).set(position.getY(), piece);
        return new Board(tableroAux);
    }

    public Boolean hasPiece(Position position) {
        if (!isWithinLimits(position)) return false;
        return pieceContainer.get(position.getX()).get(position.getY()) != null;
    }

    public Board deletePiece(Position position) {
        List<List<Piece>> tableroAux = clonate();
        tableroAux.get(position.getX()).set(position.getY(), null);
        return new Board(tableroAux);
    }

    public Piece getPiece(Position position) {
        return pieceContainer.get(position.getX()).get(position.getY());
    }


    public Board moovePiece(Position positionInicial, Position positionFinal, Piece piece) {
        List<List<Piece>> nuevaLista = clonate();
        Board newBoard = new Board(nuevaLista);
        newBoard = newBoard.deletePiece(positionInicial);
        newBoard = newBoard.addPiece(piece, positionFinal);
        return newBoard;
    }

    public boolean isWithinLimits(Position position) {
        return rowsSize() > position.getX() && columnsSize() > position.getY() && position.getX() >= 0 && position.getY() >= 0;
    }

    public Board forceMovements(Piece piece, Position positionFinal) {
        List<List<Piece>> nuevaLista = clonate();
        Board newBoard = new Board(nuevaLista);
        newBoard = newBoard.addPiece(piece, positionFinal);
        return newBoard;
    }

    public List<Position> kings() {
        List<Position> kings = new ArrayList<>();
        for (int i = 0; i< pieceContainer.size(); i++){
            for (int j = 0; j< pieceContainer.get(i).size(); j++){
                if (pieceContainer.get(i).get(j) != null && pieceContainer.get(i).get(j).isWiningPiece()) {
                    kings.add(new Position(i,j));
                }
            }
        }
        return kings;
    }

    private List<List<Piece>> getPieceContainer() {
        return pieceContainer;
    }

    public int rowsSize(){
        return pieceContainer.size();
    }

    public int columnsSize(){
        return pieceContainer.get(0).size();
    }

    private List<List<Piece>> clonate() {
        List<List<Piece>> nuevaLista = new ArrayList<>();
        for (int i = 0; i< pieceContainer.size(); i++){
            List<Piece> columnas = new ArrayList<>();
            nuevaLista.add(columnas);
            for (int j = 0; j< pieceContainer.get(i).size(); j++){
                columnas.add(pieceContainer.get(i).get(j));
            }
        }
        return nuevaLista;
    }

    public Map<Position, Piece> getAllPieces() {
        Map<Position, Piece> piezas = new HashMap<>();
        for (int i = 0; i< rowsSize(); i++){
            for (int j = 0; j< columnsSize(); j++){
                if (pieceContainer.get(i).get(j) != null) {
                    piezas.put(new Position(i,j), pieceContainer.get(i).get(j));
                }
            }
        }
        return piezas;
    }

//    public String imprimirTablero(){
//        String tableroString = "";
//        for (int i=0; i<tablero.size();i++){
//            for (int j=0; j<tablero.get(i).size();j++){
//                tableroString += "|";
//                if (tablero.get(i).get(j) == null) tableroString += "       ";
//                else tableroString += " " + tablero.get(i).get(j).getNombre().toString() + "  ";
//            }
//            tableroString += "|\n";
//        }
//        return tableroString;
//    }
}
