package edu.austral.dissis.connection.client.listeners

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.connection.client.ClientHandler
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ClientGameOverListener(private val clientHandler: ClientHandler) : MessageListener<GameOver> {
    override fun handleMessage(message: Message<GameOver>) {
        clientHandler.handleNewState(message.payload)
    }
}