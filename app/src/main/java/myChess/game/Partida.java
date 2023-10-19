package myChess.game;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.movimientosEspeciales.MovimientoEspecial;
import myChess.game.validadoresDeJuego.ResultSet;
import myChess.game.validadoresDeJuego.ValidadorDeJuego;

import java.util.List;

public class Partida {
    Tablero tablero;
    List<User> jugadores;
    PlayerColor turno;

    List<ValidadorDeJuego> validadoresDeJuego;

    public Partida (Tablero tablero, List<User> jugadores, List<MovimientoEspecial> movimientoEspeciales) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.turno = PlayerColor.WHITE;
    }

    public Partida (Tablero tablero, List<User> jugadores, PlayerColor turno) {
        this.tablero = tablero;
        this.jugadores = jugadores;
        this.turno = turno;

    }

    public Partida jugar(Posicion inicial, Posicion post, User jugador) {
        for (ValidadorDeJuego validadorDeJuego : validadoresDeJuego) {
            ResultSet resultSet = validadorDeJuego.validarJuego(inicial, post, tablero, jugador);
            if (resultSet.getInvalid()) {
                System.out.println(resultSet.getExplanation());
                return this;
            }
            if (resultSet.getReturnable()) {
                return new Partida(resultSet.getTablero(), jugadores, (turno == PlayerColor.WHITE) ? PlayerColor.BLACK : PlayerColor.WHITE);
            }
        }
        return this;
    }



//    public Partida mover(Posicion posicionInicial, Posicion posicionFinal, User jugador) {
//        try {
//            if (jugador != jugadores.get(turno)) {
//                System.out.println("No es tu turno");
//                return this;
//            }else if (!tablero.tienePieza(posicionInicial)){
//                System.out.println("No hay pieza en la posicion");
//                return this;
//            } else if (tablero.obtenerPieza(posicionInicial).getOwner() != jugador) {
//                System.out.println("No es tu pieza");
//                return this;
//            }
//            for (MovimientoEspecial movimientoEspecial : movimientoEspeciales) {
//                Tablero tableroAux = movimientoEspecial.MovimientoEspecial(tablero.obtenerPieza(posicionInicial), posicionInicial, posicionFinal, tablero);
//                if (tableroAux != tablero) {
//                    return new Partida(tableroAux, jugadores, (turno + 1) % jugadores.size(), movimientoEspeciales);
//                }
//            }
//            for (ReglasJuegoMovimiento reglasJuegoMovimiento : reglasJuegoMovimientos) {
//                if (!reglasJuegoMovimiento.movValidoJuego(posicionInicial, posicionFinal, tablero, jugador)) {
//                    System.out.println("Movimiento no permitido");
//                    return this;
//                }
//            }
//            return new Partida(tablero.moverPieza(posicionInicial, posicionFinal), jugadores, (turno + 1) % jugadores.size(), movimientoEspeciales);
//
//        }
//        catch (IllegalArgumentException e) {
//            System.out.println(e.getMessage());
//            return this;
//        }
//
//    }



}
