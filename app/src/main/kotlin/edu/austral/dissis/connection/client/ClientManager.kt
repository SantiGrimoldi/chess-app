package edu.austral.dissis.connection.client

import com.fasterxml.jackson.core.type.TypeReference
import edu.austral.dissis.chess.gui.*
import edu.austral.dissis.connection.client.listeners.ClientGameOverListener
import edu.austral.dissis.connection.client.listeners.ClientInitialListener
import edu.austral.dissis.connection.client.listeners.ClientInvalidMoveListener
import edu.austral.dissis.connection.client.listeners.ClientNewStateListener
import edu.austral.ingsis.clientserver.Client
import edu.austral.ingsis.clientserver.ClientBuilder
import edu.austral.ingsis.clientserver.Message
import javafx.application.Platform
import java.net.InetSocketAddress

class ClientManager (private val gameView: GameView, private val builder : ClientBuilder) {
    private val messanger : Client

    init{
        messanger = builder()
        start()
    }

    fun handleInitialState(state: InitialState) {
        Platform.runLater {
            gameView.handleInitialState(state)
        }
    }

    fun handleNewState(state: MoveResult) {
        Platform.runLater {
            gameView.handleMoveResult(state)
        }
    }


    fun sendMessage(message : Move) {
        messanger.send(messageMoveParser(message))
    }

    fun messageMoveParser(message : Move) : Message<Move> {
        return Message("move", message)
    }

    fun builder() : Client {
        val socketAdress = InetSocketAddress("localhost", 8080)
        val initListener = ClientInitialListener(this)
        return builder
            .withAddress(socketAdress)
            .addMessageListener("initial-state", object : TypeReference<Message<InitialState>>() {},initListener)
            .addMessageListener("new-state", object : TypeReference<Message<NewGameState>>() {}, ClientNewStateListener(this))
            .addMessageListener("game-over", object : TypeReference<Message<GameOver>>() {}, ClientGameOverListener(this))
            .addMessageListener("invalid-move", object : TypeReference<Message<InvalidMove>>() {}, ClientInvalidMoveListener(this))
            .build()
    }

    fun start() {
        gameView.addListener(ClientGameListener(this))
        messanger.connect()
    }

}