package myChess.game.condicionesGanadoras;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Pieza;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;
import myChess.game.reglas.Jaque;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class JaqueMate implements CondicionGanadora{
    Jaque jaque = new Jaque();

    @Override
    public boolean condicionGanadora(Posicion incial, Posicion destino, Tablero tablero, User usuario) {
        PlayerColor color = usuario.getColor() == PlayerColor.WHITE ? PlayerColor.BLACK : PlayerColor.WHITE;
        User dummy = new User("dummy", color);
        Posicion posRey = tablero.reyes(usuario).stream().filter((pos) -> tablero.obtenerPieza(pos).getOwner().getColor() == color).toList().get(0);
        if (jaque.estoyEnJaque(posRey, tablero, color)) {
            if (reySalvaJaque(posRey, tablero, dummy)) {
                return false;
            }
            else return !piezaSalvaJaque(tablero, dummy, color);
        }
        return false;
    }

    private boolean reySalvaJaque(Posicion posRey, Tablero tablero, User usuario) {
        Pieza rey = tablero.obtenerPieza(posRey);
        for (int i = 0; i< tablero.getFilas(); i++){
            for (int j = 0; j< tablero.getColumnas(); j++){
                Posicion posicion = new Posicion(i,j);
                if (jaque.salgoDeJaque(posRey, posicion, tablero, usuario) && rey.movimientoValido(posRey, posicion, tablero) && (!tablero.tienePieza(posicion) || tablero.tienePieza(posicion) && tablero.obtenerPieza(posicion).getOwner().getColor() != usuario.getColor())) {
                    System.out.println(posicion.getX()+","+posicion.getY());
                    return true;
                }
            }
        }
        return false;
    }

    private boolean piezaSalvaJaque( Tablero tablero, User usuario, PlayerColor color){
        AtomicBoolean salva = new AtomicBoolean(false);
        Map<Posicion, Pieza> piezas = tablero.getTodasLasPiezas();
        // filtro solo las piezas del color en jaque
        piezas = piezas.entrySet().stream().filter((entry) -> entry.getValue().getOwner().getColor() == color && !entry.getValue().isPiezaGanadora()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        piezas.forEach((pos, pieza) ->{
            for (int i = 0; i< tablero.getFilas(); i++){
                for (int j = 0; j< tablero.getColumnas(); j++){
                    Posicion posicion = new Posicion(i,j);
                    if (jaque.salgoDeJaque(pos, posicion,tablero, usuario) && pieza.movimientoValido(pos, posicion, tablero) && isEmptyOrHasOpponentPiece(tablero, usuario, posicion)) {
                        System.out.println("pieza: "+ pieza.getNombre() +"; "+posicion.getX()+","+posicion.getY());
                        salva.set(true);
                    }
                }
            }
        });
        return salva.get();
    }

    private static boolean isEmptyOrHasOpponentPiece(Tablero tablero, User usuario, Posicion posicion) {
        return (tablero.tienePieza(posicion) && tablero.obtenerPieza(posicion).getOwner().getColor() != usuario.getColor()) || !tablero.tienePieza(posicion);
    }

}
