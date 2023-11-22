package edu.austral.dissis.chess

import common.*
import common.Movimientos.*
import common.validadoresDeJuego.ValidadorAnd
import common.validadoresDeJuego.GameValidator
import common.validadoresDeJuego.MoveYourPieceValidator
import common.validadoresDeJuego.SpecialMoveValidator
import common.validadoresDeJuego.NormalMoveValidator
import common.validadoresDeJuego.ValidadorOr
import edu.austral.dissis.chess.gui.PlayerColor
import myChess.game.specialMovements.Enroque
import myChess.game.specialMovements.PrimeroPeon
import myChess.game.validadoresDeJuego.Jaque
import myChess.game.validadoresDeJuego.JaqueMate
import java.util.stream.IntStream

class ChessFactory {

    fun createClasssicValidators() : GameValidator {
        val movEsp = specialMoveValidator()
        var myValidator  = ValidadorAnd(listOf(
            MoveYourPieceValidator()
//            ,FinalSquareValidator()
        ))
        val movimientos = ValidadorOr(listOf(
            NormalMoveValidator(),
            SpecialMoveValidator(listOf(Enroque(), PrimeroPeon()))
        ))
        myValidator = myValidator.addValidador(Jaque())
        myValidator = myValidator.addValidador(movimientos)
        myValidator = myValidator.addValidador(movEsp)
        return myValidator.addValidador(JaqueMate())
    }

    private fun specialMoveValidator() : SpecialMoveValidator {
        val player1 = User("jugador", PlayerColor.WHITE)
        val player2 = User("jugador2", PlayerColor.BLACK)
        val blancaActual = Piece(PieceNames.PEON, listOf(Pawn()), player1, "b1")
        val negraActual = Piece(PieceNames.PEON, listOf(Pawn(-1)), player2, "b2")
        val reyBlanca = Piece(
            PieceNames.REINA,
            listOf(Vertical(false), Horizontal(false), Diagonal(false)),
            player1,
            "r1"
        )
        val reyNegra = Piece(
            PieceNames.REINA,
            listOf(Vertical(false), Horizontal(false), Diagonal(false)),
            player2,
            "r2"
        )
        val movEsp = SpecialMoveValidator(
            listOf(
                Transformations(blancaActual, reyBlanca, 7),
                Transformations(negraActual, reyNegra, 0)
            )
        )
        return movEsp
    }

    fun addPieces(player1: User, player2: User) : Board {
        var board = Board(8, 8)
        board = board.addPiece(
            Piece(
                PieceNames.REY,
                listOf(OneSquare()),
                player1,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k1"
            ), Position(0, 3)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REINA,
                listOf(
                    Vertical(false),
                    Horizontal(false),
                    Diagonal(false)
                ),
                player1,
                "q1"
            ), Position(0, 4)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REY,
                listOf(OneSquare()),
                player2,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k2"
            ), Position(7, 3)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REINA,
                listOf(
                    Vertical(false),
                    Horizontal(false),
                    Diagonal(false)
                ),
                player2,
                "q2"
            ), Position(7, 4)
        )
        board = board.addPiece(
            Piece(PieceNames.CABALLO, listOf(L()), player1, "c1"),
            Position(0, 1)
        )
        board = board.addPiece(
            Piece(PieceNames.CABALLO, listOf(L()), player1, "c2"),
            Position(0, 6)
        )
        board = board.addPiece(
            Piece(PieceNames.CABALLO, listOf(L()), player2, "c3"),
            Position(7, 1)
        )
        board = board.addPiece(
            Piece(PieceNames.CABALLO, listOf(L()), player2, "c4"),
            Position(7, 6)
        )
        board = board.addPiece(
            Piece(
                PieceNames.ALFIL,
                listOf(Diagonal(false)),
                player1,
                "a1"
            ), Position(0, 2)
        )
        board = board.addPiece(
            Piece(
                PieceNames.ALFIL,
                listOf(Diagonal(false)),
                player1,
                "a2"
            ), Position(0, 5)
        )
        board = board.addPiece(
            Piece(
                PieceNames.ALFIL,
                listOf(Diagonal(false)),
                player2,
                "a3"
            ), Position(7, 2)
        )
        board = board.addPiece(
            Piece(
                PieceNames.ALFIL,
                listOf(Diagonal(false)),
                player2,
                "a4"
            ), Position(7, 5)
        )
        board = board.addPiece(
            Piece(
                PieceNames.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player1,
                listOf(MovimientosEspeciales.ENROQUE),
                "t1"
            ), Position(0, 0)
        )
        board = board.addPiece(
            Piece(
                PieceNames.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player1,
                listOf(MovimientosEspeciales.ENROQUE),
                "t2"
            ), Position(0, 7)
        )
        board = board.addPiece(
            Piece(
                PieceNames.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player2,
                listOf(MovimientosEspeciales.ENROQUE),
                "t3"
            ), Position(7, 0)
        )
        board = board.addPiece(
            Piece(
                PieceNames.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player2,
                listOf(MovimientosEspeciales.ENROQUE),
                "t4"
            ), Position(7, 7)
        )

        for (i in IntStream.range(0, 8)) {
            board = board.addPiece(
                Piece(
                    PieceNames.PEON,
                    listOf(Pawn()),
                    player1,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pb" + i
                ), Position(1, i)
            )
            board = board.addPiece(
                Piece(
                    PieceNames.PEON,
                    listOf(Pawn(-1)),
                    player2,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pn" + i
                ), Position(6, i)
            )
        }
        return board
    }

    fun simplifiedChess(player1: User, player2: User) : Board {
        var board = Board(8, 8)
        board = board.addPiece(
            Piece(
                PieceNames.REY,
                listOf(OneSquare()),
                player1,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k1"
            ), Position(0, 3)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REY,
                listOf(OneSquare()),
                player2,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k2"
            ), Position(7, 3)
        )

        board = board.addPiece(
            Piece(
                PieceNames.ESPECIAL,
                listOf(
                    Vertical(true, 3),
                    Horizontal(true, 3),
                    Diagonal(false, 3)
                ),
                player1,
                "q1"
            ),
            Position(0, 4)
            )
        board = board.addPiece(
            Piece(
                PieceNames.ESPECIAL,
                listOf(
                    Vertical(true, 3),
                    Horizontal(true, 3),
                    Diagonal(false, 3)
                ),
                player2,
                "q2"
            ),
            Position(7, 4)
        )

        for (i in IntStream.range(0, 8)) {
            board = board.addPiece(
                Piece(
                    PieceNames.PEON,
                    listOf(Pawn()),
                    player1,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pb" + i
                ), Position(1, i)
            )
            board = board.addPiece(
                Piece(
                    PieceNames.PEON,
                    listOf(Pawn(-1)),
                    player2,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pn" + i
                ), Position(6, i)
            )
        }

        return board
    }

    fun winChess(player1: User, player2: User) : Board {
        var board = Board(8, 8)
        board = board.addPiece(
            Piece(
                PieceNames.REY,
                listOf(OneSquare()),
                player2,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k1"
            ), Position(0, 3)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REY,
                listOf(OneSquare()),
                player1,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k3"
            ), Position(7, 3)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REINA,
                listOf(
                    Vertical(false),
                    Horizontal(false),
                    Diagonal(false)
                ),
                player1,
                "q1"
            ), Position(1, 0)
        )
        board = board.addPiece(
            Piece(
                PieceNames.REINA,
                listOf(
                    Vertical(false),
                    Horizontal(false),
                    Diagonal(false)
                ),
                player1,
                "q2"
            ), Position(1, 1)
        )
        return board
    }

    fun createPlayers(): Pair<User, User> {
        val player1 = User("Player 1", PlayerColor.WHITE)

        val player2 = User("Player 2", PlayerColor.BLACK)
        return Pair(player1, player2)
    }
}