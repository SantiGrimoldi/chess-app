package edu.austral.dissis.connection.client.listeners

import edu.austral.dissis.chess.gui.InvalidMove
import edu.austral.dissis.connection.client.ClientManager
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ClientInvalidMoveListener(private val clientManager: ClientManager) : MessageListener<InvalidMove> {
    override fun handleMessage(message: Message<InvalidMove>) {
        clientManager.handleNewState(message.payload)
    }
}