package edu.austral.dissis.connection.client.listeners

import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.connection.client.ClientHandler
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ClientInvalidMoveListener(private val clientHandler: ClientHandler) : MessageListener<InvalidMove> {
    override fun handleMessage(message: Message<InvalidMove>) {
        clientHandler.handleNewState(message.payload)
    }
}