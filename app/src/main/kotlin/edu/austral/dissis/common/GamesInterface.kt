package edu.austral.dissis.common

import common.Board
import common.User
import common.validadoresDeJuego.gameValidator
import edu.austral.dissis.chess.gui.GameEngine

interface GamesInterface : GameEngine {
    var board : Board
    var players : ArrayList<User>
    var turn : Int
    var validator : gameValidator
}