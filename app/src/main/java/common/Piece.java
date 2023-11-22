package common;

import common.interfaces.Movement;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.movements.SpecialMovements;

import java.util.ArrayList;
import java.util.List;

public class Piece {
    private String id;
    private PieceNames name;
    private User owner;
    private List<Movement> movements;
    List<SpecialMovements> specialMovements;
    private boolean isWiningPiece;

    public Piece(PieceNames name, List<Movement> movs, User owner, List<SpecialMovements> movsEspeciales, String id) {
        this.name = name;
        this.movements = movs;
        this.owner = owner;
        this.isWiningPiece = false;
        this.specialMovements = movsEspeciales == null ? new ArrayList<>() : movsEspeciales;
        this.id = id;
        
    }

    public Piece(PieceNames name, List<Movement> movs, User owner, String id) {
        this.name = name;
        this.movements = movs;
        this.owner = owner;
        this.isWiningPiece = false;
        this.specialMovements = new ArrayList<>();
        this.id = id;
    }

    public Piece(PieceNames name, List<Movement> movs, User owner, boolean isWiningPiece, List<SpecialMovements> specialMovs, String id) {
        this.name = name;
        this.movements = movs;
        this.owner = owner;
        this.isWiningPiece = isWiningPiece;
        this.specialMovements = specialMovs == null ? new ArrayList<>() : specialMovs;
        this.id = id;
    }

    public Boolean isValidMovement(Position initialPosition, Position finalPosition, Board board) {
        for (Movement movement : movements) {
            if (movement.isMovementValid(initialPosition, finalPosition, board)) {
                return true;
            }
        }
        return false;
    }

    public PieceNames getName() {
        return name;
    }

    public User getOwner() {
        return owner;
    }

    public List<Movement> getMovements() {
        return movements;
    }

    public boolean isWiningPiece() {
        return isWiningPiece;
    }

    public List<SpecialMovements> getSpecialMovements() {
        return specialMovements;
    }

    public String getId() {
        return id;
    }

    public Piece takeSpecialMovements(List<SpecialMovements> movsEspeciales) {
        List<SpecialMovements> nuevosMovsEspeciales = new ArrayList<>(this.specialMovements);
        nuevosMovsEspeciales.removeAll(movsEspeciales);
        return new Piece(this.name, this.movements, this.owner, this.isWiningPiece, nuevosMovsEspeciales, this.id);
    }

    public Piece cleanSpecialMovements() {
        return new Piece(this.name, this.movements, this.owner, this.isWiningPiece, new ArrayList<>(), this.id);
    }

    public boolean equals(Piece piece) {
        return  this.owner.getColor() == piece.getColor() &&
                this.name == piece.getName() &&
                this.isWiningPiece == piece.isWiningPiece();
    }

    public Piece setId(String id) {
        return new Piece(this.name, this.movements, this.owner, this.isWiningPiece, this.specialMovements, id);
    }

    public PlayerColor getColor() {
        return this.owner.getColor();
    }
}
