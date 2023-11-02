package myChess.game.condicionesGanadoras;

import common.interfaces.CondicionGanadora;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Pieza;
import common.Posicion;
import common.Tablero;
import common.User;
import myChess.game.reglas.Jaque;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class JaqueMate implements CondicionGanadora {
    Jaque jaque = new Jaque();

    @Override
    public boolean condicionGanadora(Posicion incial, Posicion destino, Tablero tablero, User usuario) {
        PlayerColor color = usuario.getColor() == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        Posicion posRey = tablero.reyes().stream().filter((pos) -> tablero.obtenerPieza(pos).getColor() == color).toList().get(0);
        if (jaque.estoyEnJaque(posRey, tablero, color)) {
            if (reySalvaJaque(posRey, tablero, color)) {
                return false;
            }
            else return !piezaSalvaJaque(tablero, color);
        }
        return false;
    }

    private boolean reySalvaJaque(Posicion posRey, Tablero tablero, PlayerColor usuario) {
        Pieza rey = tablero.obtenerPieza(posRey);
        for (int i = 0; i< tablero.getFilas(); i++){
            for (int j = 0; j< tablero.getColumnas(); j++){
                Posicion posicion = new Posicion(i,j);
                if (
                        jaque.salgoDeJaque(posRey, posicion, tablero, usuario) &&
                                rey.movimientoValido(posRey, posicion, tablero) &&
                                destinoDisponible(tablero, usuario, posicion)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean destinoDisponible(Tablero tablero, PlayerColor usuario, Posicion posicion) {
        return !tablero.tienePieza(posicion) || tablero.tienePieza(posicion)
                && tablero.obtenerPieza(posicion).getColor() != usuario;
    }

    private boolean piezaSalvaJaque( Tablero tablero, PlayerColor color){
        AtomicBoolean salva = new AtomicBoolean(false);
        Map<Posicion, Pieza> piezas = tablero.getTodasLasPiezas();
        // filtro solo las piezas del color en jaque
        piezas = obtenerLasPiezasDeMiColor(color, piezas);
        piezas.forEach((pos, pieza) ->{
            for (int i = 0; i< tablero.getFilas(); i++){
                for (int j = 0; j< tablero.getColumnas(); j++){
                    Posicion posicion = new Posicion(i,j);
                    if (jaque.salgoDeJaque(pos, posicion, tablero, color) &&
                            DestinoPosible(posicion, tablero, color, pos, pieza)) {
                        salva.set(true);
                    }
                }
            }
        });
        return salva.get();
    }

    private static boolean DestinoPosible(Posicion posicion, Tablero tablero, PlayerColor color, Posicion pos, Pieza pieza) {
        return pieza.movimientoValido(pos, posicion, tablero) &&
                isEmptyOrHasOpponentPiece(tablero, color, posicion);
    }

    @NotNull
    private static Map<Posicion, Pieza> obtenerLasPiezasDeMiColor(PlayerColor color, Map<Posicion, Pieza> piezas) {
        return piezas.entrySet().stream().filter((entry) -> entry.getValue().getColor() == color && !entry.getValue().isPiezaGanadora()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private static boolean isEmptyOrHasOpponentPiece(Tablero tablero, PlayerColor usuario, Posicion posicion) {
        return (tablero.tienePieza(posicion) && tablero.obtenerPieza(posicion).getColor() != usuario) || !tablero.tienePieza(posicion);
    }

}
