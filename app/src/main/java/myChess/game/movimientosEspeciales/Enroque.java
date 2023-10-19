package myChess.game.movimientosEspeciales;

import myChess.game.NombrePieza;
import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Enroque implements MovimientoEspecial {
    @Override
    public Tablero MovimientoEspecial(Pieza pieza, Posicion inicial, Posicion post, Tablero tablero) {
        if (noEsRey(pieza)) return tablero;
        Pieza torre = tablero.obtenerPieza(post);
        if (torre == null) return tablero;
        if (esEnrocable(torre)) {
            if (caminoLibre(inicial, post, tablero, torre)){
                return enrocar(pieza, inicial, post, tablero, torre);
            }
        }
        return tablero;
    }

    private static Tablero enrocar(Pieza pieza, Posicion inicial, Posicion post, Tablero tablero, Pieza torre) {
        Posicion enroqueRey = nuevaPosicion(inicial, post, 2);
        Posicion enroqueTorre = nuevaPosicion(inicial, post, 1);
        Pieza torreNueva = limpiarMovEspecial(torre);
        Pieza reyNuevo = limpiarMovEspecial(pieza);
        return nuevoTablero(inicial, post, tablero, reyNuevo, enroqueRey, torreNueva, enroqueTorre);
    }

    private static Tablero nuevoTablero(Posicion inicial, Posicion post, Tablero tablero, Pieza reyNuevo, Posicion enroqueRey, Pieza torreNueva, Posicion enroqueTorre) {
        tablero = tablero.forzarMovimiento(reyNuevo, enroqueRey);
        tablero = tablero.forzarMovimiento(torreNueva, enroqueTorre);
        tablero = tablero.eliminarPieza(inicial);
        tablero = tablero.eliminarPieza(post);
        return tablero;
    }

    private static Pieza limpiarMovEspecial(Pieza pieza) {
        return pieza.sacarMovEspeciales(List.of(MovimientosEspeciales.ENROQUE));
    }

    @NotNull
    private static Posicion nuevaPosicion(Posicion inicial, Posicion post, int x) {
        return inicial.getY() < post.getY() ? new Posicion(inicial.getX(), inicial.getY() + x) : new Posicion(inicial.getX(), inicial.getY() - x);
    }

    private static Boolean caminoLibre(Posicion inicial, Posicion post, Tablero tablero, Pieza torre) {
        return torre.movimientoValido(post, inicial, tablero);
    }

    private static boolean esEnrocable(Pieza pieza) {
        return pieza.getMovimientosEspeciales().contains(MovimientosEspeciales.ENROQUE);
    }

    private boolean noEsRey(Pieza pieza) {
        return !esEnrocable(pieza) || pieza.getNombre() != NombrePieza.REY;
    }
}
