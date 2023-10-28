package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;
import myChess.game.movimientosEspeciales.MovimientoEspecial;

import java.util.List;

public class ValidadorMovimientosEspeciales implements ValidadorDeJuego{
    private List<MovimientoEspecial> movimientosEspeciales;

    public ValidadorMovimientosEspeciales(List<MovimientoEspecial> movimientosEspeciales) {
        this.movimientosEspeciales = movimientosEspeciales;
    }

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        for (MovimientoEspecial movimientoEspecial : movimientosEspeciales) {
            Tablero tableroAux = movimientoEspecial.MovimientoEspecial(tablero.obtenerPieza(posicionInicial), posicionInicial, posicionFinal, tablero);
            if (tableroAux != tablero) {
                System.out.println(tableroAux.imprimirTablero());
                return new ResultSet(tableroAux, "Movimiento especial valido", true, false);
            }
        }
        return new ResultSet(tablero, "No es un movimiento especial", false, false);
    }
}
