package edu.austral.dissis.checkers

import common.*
import common.movements.MovimientosEspeciales
import common.gameValidator.ValidadorAnd
import common.gameValidator.GameValidator
import myCheckers.gameValidators.CheckMoveIfValid
import common.gameValidator.MoveYourPieceValidator
import common.gameValidator.SpecialMoveValidator
import edu.austral.dissis.chess.gui.PlayerColor
import myCheckers.Dama
import myCheckers.gameValidators.WinCheckersValidator
import myCheckers.gameValidators.CheckersMovementValidator
import myCheckers.gameValidators.ValidateFinalPosition
import myCheckers.gameValidators.IsAbleToEatValidator

class CheckersFactory {
    fun classicCheckersValidator () : GameValidator {
        val movEsp = specialMovementValidator()
        val myV = ValidadorAnd(listOf(
            MoveYourPieceValidator(),
            ValidateFinalPosition(), IsAbleToEatValidator(),
            CheckersMovementValidator(), movEsp,
            WinCheckersValidator()
        ))
        return myV
    }

    private fun specialMovementValidator() : GameValidator {
        val player1 = User("jugador", PlayerColor.WHITE)
        val player2 = User("jugador2", PlayerColor.BLACK)
        val blancaActual = Piece(PieceNames.REINA, listOf(Dama()), player1, "b1")
        val negraActual = Piece(PieceNames.REINA, listOf(Dama(-1)), player2, "b2")
        val reyBlanca = Piece(PieceNames.REY, listOf(Dama(true)), player1, "r1")
        val reyNegra = Piece(PieceNames.REY, listOf(Dama(true)), player2, "r2")
        val movEsp = SpecialMoveValidator(
            listOf(
                Transformations(blancaActual, reyBlanca, 7),
                Transformations(negraActual, reyNegra, 0)
            )
        )
        val condition = CheckMoveIfValid(movEsp)

        return condition
    }

    fun createClassicalCheckers() : Board {
        var board = Board(8, 8)
        val (player1, player2) = createPlayers()
        val movBlanco = Dama()
        val movNegro = Dama(-1)
        for (row in 0 until board.rowsSize()) {
            // Recorre las columnas del tablero
            for (col in 0 until board.columnsSize()) {
                // Inicializa las posiciones iniciales de las damas blancas en las primeras 3 filas
                if (row < 3 && (col + row) % 2 == 0) {
                    board = board.addPiece(
                        Piece(
                            PieceNames.REINA,
                            listOf(movBlanco),
                            player1,
                            listOf(MovimientosEspeciales.PRIMERO),
                            "" + col + row
                        ), Position(row, col)
                    )
                }

                // Inicializa las posiciones iniciales de las damas negras en las últimas 3 filas
                if (row >= board.columnsSize() - 3 && (col + row) % 2 == 0) {
                    board = board.addPiece(
                        Piece(
                            PieceNames.REINA,
                            listOf(movNegro),
                            player2,
                            listOf(MovimientosEspeciales.PRIMERO),
                            "" + col + row
                        ), Position(row, col)
                    )
                }
            }
        }
        return board
    }

    fun createWinCheckers(): Board {
        var board = Board(8, 8)
        val (player1, player2) = createPlayers()
        val movBlanco = Dama()
        val movNegro = Dama(-1)
        board = board.addPiece(
            Piece(
                PieceNames.REINA,
                listOf(movBlanco),
                player1,
                listOf(MovimientosEspeciales.PRIMERO),
                "D1"
            ), Position(3, 3)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REINA,
                listOf(movNegro),
                player2,
                listOf(MovimientosEspeciales.PRIMERO),
                "D2"
            ), Position(4, 4)
        )
        return board
    }
    fun createPlayers(): Pair<User, User> {
        val player1 = User("Player 1", PlayerColor.WHITE)
        val player2 = User("Player 2", PlayerColor.BLACK)
        return Pair(player1, player2)
    }
}