package edu.austral.dissis.chess

import common.*
import common.Movimientos.*
import edu.austral.dissis.chess.gui.PlayerColor
import common.Movimientos.MovimientosEspeciales
import java.util.stream.IntStream.range

class MyThings()  {
    private var board: Tablero = Tablero(8, 8)
    private val playersList: MutableList<User> = ArrayList()

    init {
        val player1 = User("Player 1", PlayerColor.WHITE)
        playersList.add(player1)
        val player2 = User("Player 2", PlayerColor.BLACK)
        playersList.add(player2)

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

        for (i in range(0,8)){
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

    }

    val tablero: Tablero
        get() = board

    val jugadores: List<User>
        get() = playersList

}