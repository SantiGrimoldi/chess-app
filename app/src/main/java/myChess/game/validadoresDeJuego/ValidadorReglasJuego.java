package myChess.game.validadoresDeJuego;

import edu.austral.dissis.chess.gui.PlayerColor;
import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;
import myChess.game.reglas.ReglasJuegoMovimiento;

import java.util.List;

public class ValidadorReglasJuego implements ValidadorDeJuego{

    private List<ReglasJuegoMovimiento> reglasJuego;

    public ValidadorReglasJuego(List<ReglasJuegoMovimiento> reglasJuego) {
        this.reglasJuego = reglasJuego;
    }

    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        for (ReglasJuegoMovimiento regla : reglasJuego) {
            if (!regla.movValidoJuego(posicionInicial, posicionFinal, tablero, usuario)) {
                return new ResultSet(tablero, "Movimiento invalido", false, true);
            }
        }
        return new ResultSet(tablero, "Movimiento valido", false, false);
    }
}
