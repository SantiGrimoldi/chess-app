package edu.austral.dissis.chess
import common.*
import java.io.File
import edu.austral.dissis.chess.gui.PlayerColor

class PieceTester {

    data class ResultsFromTxt (val position: Position, val posibles : List<Position>, val imposibles : List<Position>, val oponent : List<Position>) {}

    fun cargarTableroDesdeArchivo(archivo: String) : ResultsFromTxt {
        val lineas = File(archivo).readLines()
        val posibles = mutableListOf<Position>()
        val imposibles = mutableListOf<Position>()
        val oponent = mutableListOf<Position>()
        var position = Position(0, 0)
        for (i in 0 until 8) {
            val fila = lineas[i].split("|").map { it.trim() }
            for (j in 0 until 8) {
                if (fila[j] == "P") {
//                    println("Pieza encontrada en la posición ($i, $j)")
                    position = Position(i, j)
                }
                else if (fila[j] == "V") {
                    posibles.add(Position(i, j))
                }
                else if (fila[j] == "O") {
                    oponent.add(Position(i, j))
                    posibles.add(Position(i, j))
                }
                else imposibles.add(Position(i, j))
            }
        }
        return ResultsFromTxt(position, posibles, imposibles, oponent)
    }

    fun analizar (piece: Piece, resultsFromTxt: ResultsFromTxt, board: Board) : Boolean {
        val posibles = resultsFromTxt.posibles
        val imposibles = resultsFromTxt.imposibles
        val posicion = resultsFromTxt.position

        for (posible in posibles) {
            if (!piece.isValidMovement(posicion, posible, board)) {
                return false
            }
        }
        for (imposible in imposibles) {
            if (piece.isValidMovement(posicion, imposible, board)) {
                return false
            }
        }
        return true
    }

    fun adaptTablero (resultsFromTxt: ResultsFromTxt) : Board {
        var board = Board(8, 8)
        val oponent = resultsFromTxt.oponent
        val owner = User("Jhon Doe", PlayerColor.BLACK)
        val OponentPiece = Piece(PieceNames.PEON, listOf(), owner, "oponent")
        for (posicion in oponent) {
            board = board.addPiece(OponentPiece, posicion)
        }
        return board
    }

    fun testMovimiento(piece : Piece, archivo: String) : Boolean {
        val resultsFromTxt = cargarTableroDesdeArchivo(archivo)
        var tablero = adaptTablero(resultsFromTxt)
        tablero = tablero.addPiece(piece, resultsFromTxt.position)
        return analizar(piece, resultsFromTxt, tablero)
    }

}