package myChess.game.validadoresDeJuego;

import myChess.game.Posicion;
import myChess.game.Tablero;
import myChess.game.User;

public class ValidadorCasilleroFinal implements ValidadorDeJuego{
    @Override
    public ResultSet validarJuego(Posicion posicionInicial, Posicion posicionFinal, Tablero tablero, User usuario) {
        if (tablero.tienePieza(posicionFinal) && tablero.obtenerPieza(posicionFinal).getOwner().getColor() == usuario.getColor()) {
            return new ResultSet(tablero, "No puedes comer tu propia pieza", false, true);
        }
        if (!tablero.tienePieza(posicionInicial)) return new ResultSet(tablero, "No hay pieza en el casillero inicial", false, true);
        return new ResultSet(tablero, "Ambos casilleros son validos", false, false);
    }
}
