package edu.austral.dissis.chess

import common.*
import common.Movimientos.*
import common.validadoresDeJuego.ValidadorAnd
import common.validadoresDeJuego.ValidadorCasilleroFinalAjedrez
import common.validadoresDeJuego.ValidadorDeJuego
import common.validadoresDeJuego.ValidadorMoverTuPieza
import common.validadoresDeJuego.ValidadorMovimientosEspeciales
import common.validadoresDeJuego.ValidadorMovimientosNormales
import common.validadoresDeJuego.ValidadorOr
import edu.austral.dissis.chess.gui.PlayerColor
import myChess.game.movimientosEspeciales.Enroque
import myChess.game.movimientosEspeciales.PrimeroPeon
import myChess.game.validadoresDeJuego.Jaque
import myChess.game.validadoresDeJuego.JaqueMate
import java.util.stream.IntStream

class ChessFactory {

    fun createClasssicValidators() : ValidadorDeJuego{
        val movEsp = validadorMovsEsp()
        var myValidator  = ValidadorAnd(listOf(ValidadorMoverTuPieza(),
            ValidadorCasilleroFinalAjedrez()
        ))
        val movimientos = ValidadorOr(listOf(ValidadorMovimientosNormales(), ValidadorMovimientosEspeciales(listOf(Enroque(), PrimeroPeon()))))
        myValidator = myValidator.addValidador(Jaque())
        myValidator = myValidator.addValidador(movimientos)
        myValidator = myValidator.addValidador(movEsp)
        return myValidator.addValidador(JaqueMate())
    }

    private fun validadorMovsEsp() : ValidadorMovimientosEspeciales {
        val player1 = User("jugador", PlayerColor.WHITE)
        val player2 = User("jugador2", PlayerColor.BLACK)
        val blancaActual = Pieza(NombrePieza.PEON, listOf(Peon()), player1, "b1")
        val negraActual = Pieza(NombrePieza.PEON, listOf(Peon(-1)), player2, "b2")
        val reyBlanca = Pieza(NombrePieza.REINA, listOf(Vertical(false), Horizontal(false), Diagonal(false)), player1, "r1")
        val reyNegra = Pieza(NombrePieza.REINA, listOf(Vertical(false), Horizontal(false), Diagonal(false)), player2, "r2")
        val movEsp = ValidadorMovimientosEspeciales(
            listOf(
                Transformaciones(blancaActual,reyBlanca,7),
                Transformaciones(negraActual,reyNegra,0)
            )
        )
        return movEsp
    }

    fun addPieces(player1: User, player2: User) : Tablero {
        var board = Tablero(8, 8)
        board = board.agregarPieza(
            Pieza(
                NombrePieza.REY,
                listOf(UnCuadrado()),
                player1,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k1"
            ), Posicion(0, 3)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.REINA,
                listOf(
                    Vertical(false),
                    Horizontal(false),
                    Diagonal(false)
                ),
                player1,
                "q1"
            ), Posicion(0, 4)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.REY,
                listOf(UnCuadrado()),
                player2,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k2"
            ), Posicion(7, 3)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.REINA,
                listOf(
                    Vertical(false),
                    Horizontal(false),
                    Diagonal(false)
                ),
                player2,
                "q2"
            ), Posicion(7, 4)
        )
        board = board.agregarPieza(
            Pieza(NombrePieza.CABALLO, listOf(L()), player1, "c1"),
            Posicion(0, 1)
        )
        board = board.agregarPieza(
            Pieza(NombrePieza.CABALLO, listOf(L()), player1, "c2"),
            Posicion(0, 6)
        )
        board = board.agregarPieza(
            Pieza(NombrePieza.CABALLO, listOf(L()), player2, "c3"),
            Posicion(7, 1)
        )
        board = board.agregarPieza(
            Pieza(NombrePieza.CABALLO, listOf(L()), player2, "c4"),
            Posicion(7, 6)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.ALFIL,
                listOf(Diagonal(false)),
                player1,
                "a1"
            ), Posicion(0, 2)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.ALFIL,
                listOf(Diagonal(false)),
                player1,
                "a2"
            ), Posicion(0, 5)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.ALFIL,
                listOf(Diagonal(false)),
                player2,
                "a3"
            ), Posicion(7, 2)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.ALFIL,
                listOf(Diagonal(false)),
                player2,
                "a4"
            ), Posicion(7, 5)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player1,
                listOf(MovimientosEspeciales.ENROQUE),
                "t1"
            ), Posicion(0, 0)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player1,
                listOf(MovimientosEspeciales.ENROQUE),
                "t2"
            ), Posicion(0, 7)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player2,
                listOf(MovimientosEspeciales.ENROQUE),
                "t3"
            ), Posicion(7, 0)
        )
        board = board.agregarPieza(
            Pieza(
                NombrePieza.TORRE,
                listOf(
                    Vertical(false),
                    Horizontal(false)
                ),
                player2,
                listOf(MovimientosEspeciales.ENROQUE),
                "t4"
            ), Posicion(7, 7)
        )

        for (i in IntStream.range(0, 8)) {
            board = board.agregarPieza(
                Pieza(
                    NombrePieza.PEON,
                    listOf(Peon()),
                    player1,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pb" + i
                ), Posicion(1, i)
            )
            board = board.agregarPieza(
                Pieza(
                    NombrePieza.PEON,
                    listOf(Peon(-1)),
                    player2,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pn" + i
                ), Posicion(6, i)
            )
        }
        return board
    }

    fun simplifiedChess(player1: User, player2: User) : Tablero {
        var tablero = Tablero(8, 8)
        tablero = tablero.agregarPieza(
            Pieza(
                NombrePieza.REY,
                listOf(UnCuadrado()),
                player1,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k1"
            ), Posicion(0, 3)
        )
        tablero = tablero.agregarPieza(
            Pieza(
                NombrePieza.REY,
                listOf(UnCuadrado()),
                player2,
                true,
                listOf(MovimientosEspeciales.ENROQUE),
                "k2"
            ), Posicion(7, 3)
        )

        tablero = tablero.agregarPieza(
            Pieza(
                NombrePieza.ESPECIAL,
                listOf(
                    Vertical(true,3),
                    Horizontal(true,3),
                    Diagonal(false,3)
                ),
                player1,
                "q1"
            ),
            Posicion(0, 4)
            )
        tablero = tablero.agregarPieza(
            Pieza(
                NombrePieza.ESPECIAL,
                listOf(
                    Vertical(true,3),
                    Horizontal(true,3),
                    Diagonal(false,3)
                ),
                player2,
                "q2"
            ),
            Posicion(7, 4)
        )

        for (i in IntStream.range(0, 8)) {
            tablero = tablero.agregarPieza(
                Pieza(
                    NombrePieza.PEON,
                    listOf(Peon()),
                    player1,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pb" + i
                ), Posicion(1, i)
            )
            tablero = tablero.agregarPieza(
                Pieza(
                    NombrePieza.PEON,
                    listOf(Peon(-1)),
                    player2,
                    listOf(MovimientosEspeciales.PRIMERO),
                    "pn" + i
                ), Posicion(6, i)
            )
        }

        return tablero
    }

    fun createPlayers(): Pair<User, User> {
        val player1 = User("Player 1", PlayerColor.WHITE)

        val player2 = User("Player 2", PlayerColor.BLACK)
        return Pair(player1, player2)
    }
}