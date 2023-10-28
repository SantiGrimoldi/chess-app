package myChess.game;

import myChess.game.Movimientos.Movimiento;
import myChess.game.movimientosEspeciales.MovimientosEspeciales;

import java.util.ArrayList;
import java.util.List;

public class Pieza {
    private String id;
    private NombrePieza nombre;
    private User owner;
    private List<Movimiento> movimientos;
    List<MovimientosEspeciales> movimientosEspeciales;
    private boolean piezaGanadora;

    public Pieza (NombrePieza nombre, List<Movimiento> movs, User owner, List<MovimientosEspeciales> movsEspeciales, String id) {
        this.nombre = nombre;
        this.movimientos = movs;
        this.owner = owner;
        this.piezaGanadora = false;
        this.movimientosEspeciales = movsEspeciales == null ? new ArrayList<>() : movsEspeciales;
        this.id = id;
        
    }

    public Pieza(NombrePieza nombre, List<Movimiento> movs, User owner, String id) {
        this.nombre = nombre;
        this.movimientos = movs;
        this.owner = owner;
        this.piezaGanadora = false;
        this.movimientosEspeciales = new ArrayList<>();
        this.id = id;
    }

    public Pieza (NombrePieza nombre, List<Movimiento> movs, User owner, boolean piezaGanadora, List<MovimientosEspeciales> movsEspeciales, String id) {
        this.nombre = nombre;
        this.movimientos = movs;
        this.owner = owner;
        this.piezaGanadora = piezaGanadora;
        this.movimientosEspeciales = movsEspeciales == null ? new ArrayList<>() : movsEspeciales;
        this.id = id;
    }

    public Boolean movimientoValido (Posicion posicionInicial, Posicion posicionFinal, Tablero tablero) {
        for (Movimiento movimiento : movimientos) {
            if (movimiento.movimientoValido(posicionInicial, posicionFinal, tablero)) {
                return true;
            }
        }
        return false;
    }

    public NombrePieza getNombre() {
        return nombre;
    }

    public User getOwner() {
        return owner;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public boolean isPiezaGanadora() {
        return piezaGanadora;
    }

    public List<MovimientosEspeciales> getMovimientosEspeciales() {
        return movimientosEspeciales;
    }

    public String getId() {
        return id;
    }

    public Pieza sacarMovEspeciales (List<MovimientosEspeciales> movsEspeciales) {
        List<MovimientosEspeciales> nuevosMovsEspeciales = new ArrayList<>(this.movimientosEspeciales);
        nuevosMovsEspeciales.removeAll(movsEspeciales);
        return new Pieza(this.nombre, this.movimientos, this.owner, this.piezaGanadora, nuevosMovsEspeciales, this.id);
    }

    public Pieza limpiarMovimientosEspeciales () {
        return new Pieza(this.nombre, this.movimientos, this.owner, this.piezaGanadora, new ArrayList<>(), this.id);
    }

    public boolean equals(Pieza pieza) {
        return  this.owner.getColor() == pieza.getOwner().getColor() &&
                this.movimientos.containsAll(pieza.getMovimientos()) &&
                this.piezaGanadora == pieza.isPiezaGanadora();
    }

    public Pieza setId(String id) {
        return new Pieza(this.nombre, this.movimientos, this.owner, this.piezaGanadora, this.movimientosEspeciales, id);
    }
}
