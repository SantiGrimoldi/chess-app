package edu.austral.dissis.common

import common.Tablero
import common.User
import common.validadoresDeJuego.ValidadorDeJuego
import edu.austral.dissis.chess.gui.GameEngine

interface GamesInterface : GameEngine {
    var tablero : Tablero
    var jugadores : ArrayList<User>
    var turno : Int
    var validador : ValidadorDeJuego
}