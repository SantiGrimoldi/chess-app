package edu.austral.dissis.chess

import common.conectUI.UIAdapter

import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.chess.gui.PlayerColor.BLACK
import edu.austral.dissis.chess.gui.PlayerColor.WHITE
import common.Position
import common.Board
import common.User
import common.validadoresDeJuego.ResultSet
import common.validadoresDeJuego.gameValidator
import edu.austral.dissis.common.GamesInterface

class Mychess: GamesInterface {
    override var board : Board = MyThings().board
    override var players : ArrayList<User> = MyThings().players as ArrayList<User>
    override var turn = 0
    override var validator : gameValidator = ChessFactory().createClasssicValidators()


    override fun applyMove(move: Move) : MoveResult {
        val from  = Position(move.from.row - 1, move.from.column - 1)
        val to = Position(move.to.row - 1, move.to.column - 1)
        val nextColor : PlayerColor = if (players[turn].color == WHITE) BLACK else WHITE
        val thisColor : PlayerColor = players[turn].color
        val result : ResultSet = validator.validateGame(from,to, board, thisColor )
        if (result.win){
            return GameOver(players[turn].color)
        }
        if (result.invalid){
            return InvalidMove(result.explanation)
        }
        if (result.returnable == true){
            board = result.board
            turn = (turn + 1) % players.size
            return NewGameState(UIAdapter.adaptPieces(result.board), nextColor)
        }

        return InvalidMove("No se pudo realizar el movimiento")
    }

    override fun init(): InitialState {
        return InitialState(
            UIAdapter.adaptBoard(board),
            UIAdapter.adaptPieces(board), WHITE)
    }
}