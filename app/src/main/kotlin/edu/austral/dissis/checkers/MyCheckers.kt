package edu.austral.dissis.checkers

import common.*
import common.Position
import edu.austral.dissis.chess.gui.*
import common.conectUI.UIAdapter
import common.gameValidator.ResultSet
import common.gameValidator.GameValidator
import edu.austral.dissis.common.GamesInterface

class MyCheckers :  GamesInterface {
    private val factory : CheckersFactory = CheckersFactory()
    override var board: Board = Board(8, 8)
    override var players: ArrayList<User> = ArrayList()
    override var turn = 0
    override var validator : GameValidator = factory.classicCheckersValidator()


    override fun applyMove(move: Move): MoveResult {
        val from  = Position(move.from.row - 1, move.from.column - 1)
        val to = Position(move.to.row - 1, move.to.column - 1)
        val nextColor : PlayerColor = if (players[turn].color == PlayerColor.WHITE) PlayerColor.BLACK else PlayerColor.WHITE
        return validateMovement(from, to, nextColor)
    }

    private fun validateMovement(from: Position, to: Position, nextColor: PlayerColor): MoveResult {
            var result: ResultSet = validator.validateGame(from, to, board, players[turn].color)
            if (result.returnable) {
                board = result.board
                if (result.win) return GameOver(players[turn].color)
                return returnResult(result, nextColor)
            }
            if (result.invalid) return InvalidMove(result.explanation)
        return InvalidMove("No se pudo realizar el movimiento")
    }

    private fun returnResult(result: ResultSet,nextColor: PlayerColor): NewGameState {
        if (result.keepTurn()) return NewGameState(UIAdapter.adaptPieces(result.board), players[turn].color)
        turn = (turn + 1) % players.size
        return NewGameState(UIAdapter.adaptPieces(result.board), nextColor)
    }


    override fun init(): InitialState {
        return InitialState(
            UIAdapter.adaptBoard(board),
            UIAdapter.adaptPieces(board), PlayerColor.WHITE)
    }

    init {
        board = factory.createClassicalCheckers()
//        tablero = factory.createWinCheckers()
        players = factory.createPlayers().toList().toMutableList() as ArrayList<User>
    }




}