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
        List<List<Piece>> auxBoard = clonate();
        auxBoard.get(position.getX()).set(position.getY(), piece);
        return new Board(auxBoard);
    }

    public Boolean hasPiece(Position position) {
        if (!isWithinLimits(position)) return false;
        return pieceContainer.get(position.getX()).get(position.getY()) != null;
    }

    public Board deletePiece(Position position) {
        List<List<Piece>> auxBoard = clonate();
        auxBoard.get(position.getX()).set(position.getY(), null);
        return new Board(auxBoard);
    }

    public Piece getPiece(Position position) {
        return pieceContainer.get(position.getX()).get(position.getY());
    }


    public Board movePiece(Position initial, Position post, Piece piece) {
        List<List<Piece>> newList = clonate();
        Board newBoard = new Board(newList);
        newBoard = newBoard.deletePiece(initial);
        newBoard = newBoard.addPiece(piece, post);
        return newBoard;
    }

    public boolean isWithinLimits(Position position) {
        return rowsSize() > position.getX() && columnsSize() > position.getY() && position.getX() >= 0 && position.getY() >= 0;
    }

    public Board forceMovements(Piece piece, Position positionFinal) {
        List<List<Piece>> newList = clonate();
        Board newBoard = new Board(newList);
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
        List<List<Piece>> newList = new ArrayList<>();
        for (int i = 0; i< pieceContainer.size(); i++){
            List<Piece> columns = new ArrayList<>();
            newList.add(columns);
            for (int j = 0; j< pieceContainer.get(i).size(); j++){
                columns.add(pieceContainer.get(i).get(j));
            }
        }
        return newList;
    }

    public Map<Position, Piece> getAllPieces() {
        Map<Position, Piece> pieces = new HashMap<>();
        for (int i = 0; i< rowsSize(); i++){
            for (int j = 0; j< columnsSize(); j++){
                if (pieceContainer.get(i).get(j) != null) {
                    pieces.put(new Position(i,j), pieceContainer.get(i).get(j));
                }
            }
        }
        return pieces;
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
