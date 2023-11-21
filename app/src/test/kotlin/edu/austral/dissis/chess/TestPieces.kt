package edu.austral.dissis.chess

import common.Movimientos.Diagonal
import common.Movimientos.Horizontal
import common.Movimientos.L
import common.Movimientos.Pawn
import common.Movimientos.OneSquare
import common.Movimientos.Vertical
import common.PieceNames
import common.Piece
import common.User
import edu.austral.dissis.chess.gui.PlayerColor

class TestPieces {

    val owner = User("Jhon Doe", PlayerColor.WHITE)
    fun testKnight() {
        val piece = Piece(PieceNames.CABALLO, listOf(L()), owner, "blanco")
        val pieceTester = PieceTester()
        val path = "app/src/test/kotlin/edu/austral/dissis/chess/textFiles/tu_archivo.txt"
        println(pieceTester.testMovimiento(piece, path))

    }

    fun testBishop() {
        val piece = Piece(PieceNames.ALFIL, listOf(Diagonal(false)), owner, "blanco")
        val pieceTester = PieceTester()
        val path = "app/src/test/kotlin/edu/austral/dissis/chess/textFiles/Bishop.txt"
        println(pieceTester.testMovimiento(piece, path))
    }

    fun testVertical() {
        val piece = Piece(PieceNames.TORRE, listOf(Vertical(true)), owner, "blanco")
        val pieceTester = PieceTester()
        val path = "app/src/test/kotlin/edu/austral/dissis/chess/textFiles/Vertical.txt"
        println(pieceTester.testMovimiento(piece, path))
    }

    fun testHorizontal() {
        val piece = Piece(PieceNames.TORRE, listOf(Horizontal(true)), owner, "blanco")
        val pieceTester = PieceTester()
        val path = "app/src/test/kotlin/edu/austral/dissis/chess/textFiles/Horizontal.txt"
        println(pieceTester.testMovimiento(piece, path))
    }

    fun testUnCuadrado() {
        val piece = Piece(PieceNames.TORRE, listOf(OneSquare()), owner, "blanco")
        val pieceTester = PieceTester()
        val path = "app/src/test/kotlin/edu/austral/dissis/chess/textFiles/UnCuadrado.txt"
        println(pieceTester.testMovimiento(piece, path))
    }

    fun testPeon() {
        val piece = Piece(PieceNames.PEON, listOf(Pawn()), owner, "blanco")
        val pieceTester = PieceTester()
        val path = "app/src/test/kotlin/edu/austral/dissis/chess/textFiles/Peon.txt"
        println(pieceTester.testMovimiento(piece, path))

    }
}
fun main(){
    val test = TestPieces()
    test.testKnight()
    test.testBishop()
    test.testVertical()
    test.testHorizontal()
    test.testUnCuadrado()
    test.testPeon()
}