package myChess.testPiezas;

import edu.austral.dissis.chess.MyThings;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.reglas.Jaque;
import myChess.game.validadoresDeJuego.ValidadorDeJuego;
import myChess.game.validadoresDeJuego.ValidadorMovimientosNormales;
import myChess.game.validadoresDeJuego.ValidadorReglasJuego;

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
