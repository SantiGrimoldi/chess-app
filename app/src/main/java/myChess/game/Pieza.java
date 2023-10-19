package myChess.game;

import myChess.game.Movimientos.Movimiento;
import myChess.game.movimientosEspeciales.MovimientosEspeciales;

import java.util.ArrayList;
import java.util.List;

public class Pieza {
    private NombrePieza nombre;
    private User owner;
    private List<Movimiento> movimientos;
    List<MovimientosEspeciales> movimientosEspeciales;
    private boolean piezaGanadora;

    public Pieza (NombrePieza nombre, List<Movimiento> movs, User owner, List<MovimientosEspeciales> movsEspeciales) {
        this.nombre = nombre;
        this.movimientos = movs;
        this.owner = owner;
        this.piezaGanadora = false;
        this.movimientosEspeciales = movsEspeciales;
        
    }

    public Pieza(NombrePieza nombre, List<Movimiento> movs, User owner) {
        this.nombre = nombre;
        this.movimientos = movs;
        this.owner = owner;
        this.piezaGanadora = false;
        this.movimientosEspeciales = new ArrayList<>();
    }

    public Pieza (NombrePieza nombre, List<Movimiento> movs, User owner, boolean piezaGanadora, List<MovimientosEspeciales> movsEspeciales) {
        this.nombre = nombre;
        this.movimientos = movs;
        this.owner = owner;
        this.piezaGanadora = piezaGanadora;
        this.movimientosEspeciales = movsEspeciales;
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

    public Pieza sacarMovEspeciales (List<MovimientosEspeciales> movsEspeciales) {
        List<MovimientosEspeciales> nuevosMovsEspeciales = new ArrayList<>(this.movimientosEspeciales);
        nuevosMovsEspeciales.removeAll(movsEspeciales);
        return new Pieza(this.nombre, this.movimientos, this.owner, this.piezaGanadora, nuevosMovsEspeciales);
    }

    public Pieza limpiarMovimientosEspeciales () {
        return new Pieza(this.nombre, this.movimientos, this.owner, this.piezaGanadora, new ArrayList<>());
    }

    public boolean equals(Pieza pieza) {
        return  this.owner == pieza.getOwner() &&
                this.movimientos.containsAll(pieza.getMovimientos()) &&
                this.piezaGanadora == pieza.isPiezaGanadora();
    }
}
