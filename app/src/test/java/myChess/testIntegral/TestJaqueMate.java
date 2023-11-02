package myChess.testIntegral;

import common.*;
import edu.austral.dissis.chess.gui.PlayerColor;
import common.Movimientos.Diagonal;
import common.Movimientos.Horizontal;
import common.Movimientos.UnCuadrado;
import common.Movimientos.Vertical;
import myChess.game.condicionesGanadoras.JaqueMate;
import common.validadoresDeJuego.ResultSet;
import common.validadoresDeJuego.ValidadorDeJuego;
import common.validadoresDeJuego.ValidadorGanarJuego;

import java.util.ArrayList;
import java.util.List;

public class TestJaqueMate {
    public static void main(String[] args){
        ValidadorDeJuego validadorGanarJuego = new ValidadorGanarJuego(List.of(new JaqueMate()));
        Tablero tablero = new Tablero(8, 8);
        User user = new User("yo", PlayerColor.WHITE);
        User user2 = new User("yo2", PlayerColor.BLACK);
        Pieza rey = new Pieza(NombrePieza.REY, List.of(new UnCuadrado()), user2, true, new ArrayList<>(), "k2");
        Pieza reina = new Pieza(NombrePieza.REINA, List.of(new Vertical(false), new Horizontal(false), new Diagonal(false)), user, "q1");
        Pieza torre = new Pieza(NombrePieza.TORRE, List.of(new Vertical(false), new Horizontal(false)), user, "t1");
        tablero = tablero.agregarPieza(rey, new Posicion(0,0));
        tablero = tablero.agregarPieza(reina, new Posicion(1,5));
        tablero = tablero.agregarPieza(torre, new Posicion(1,6));
        ResultSet resultSet = validadorGanarJuego.validarJuego(new Posicion(1,5), new Posicion(0,5), tablero, user);
        System.out.println(resultSet.getExplanation() + " " + resultSet.getWin());

    }

}
