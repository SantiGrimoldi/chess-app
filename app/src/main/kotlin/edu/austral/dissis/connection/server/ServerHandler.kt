package edu.austral.dissis.connection.server


import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.common.GamesInterface
import edu.austral.dissis.connection.GameListener
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.Server
import edu.austral.ingsis.clientserver.ServerBuilder

class ServerHandler (private val game:  GamesInterface, private val gameView: GameView, private val builder : ServerBuilder){

    private var server : Server

    init {
        server = builder()
        server.start()
    }

    fun handleMove(move: Move) {
        val result = game.applyMove(move)
        gameView.handleMoveResult(result)
        broadcastState(result)
    }

    fun getInitialState() : InitialState {
        return game.init()
    }

    fun sendInitialState(clientId : String) {
        val message = Message("initial-state", getInitialState())
        server.sendMessage(clientId, message)
    }
    fun broadcastState(state :  MoveResult) {
        val message = when(state) {
            is NewGameState -> {
                Message("new-state", state)
            }

            is GameOver -> {
                Message("game-over", state)
            }

            is InvalidMove -> {
                Message("invalid-move", state)
            }
        }
        server.broadcast(message)
    }

    fun builder() : Server {
        val gameListener = GameListener(this)
        addstatesToGameView(gameListener)
        return builder
            .withPort(8080)
            .withConnectionListener(ClientConectsToServerListener(this))
            .addMessageListener("move", object : TypeReference<Message<Move>>() {}, ServerMessageListener(gameListener))
            .build()
    }

    fun addstatesToGameView(gameListener: GameListener) {
        gameView.addListener(gameListener)
        gameView.handleInitialState(game.init())
    }

}