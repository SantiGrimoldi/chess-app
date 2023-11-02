package myChess.testPiezas;

import edu.austral.dissis.chess.MyThings;
import common.Posicion;
import common.Tablero;
import myChess.game.reglas.Jaque;
import common.validadoresDeJuego.ValidadorDeJuego;
import common.validadoresDeJuego.ValidadorReglasJuego;

import java.util.List;

public class MovimientosBasicos {

    public static void main(String[] args) {
        MyThings mychess = new MyThings();
        Tablero tablero = mychess.getTablero();
        ValidadorDeJuego validadorDeJuego = new ValidadorReglasJuego(List.of(new Jaque()));
//        17 36
        System.out.println(validadorDeJuego.validarJuego(new Posicion(0,6), new Posicion(2,5), tablero, mychess.getJugadores().get(0)).getExplanation());
    }
}
