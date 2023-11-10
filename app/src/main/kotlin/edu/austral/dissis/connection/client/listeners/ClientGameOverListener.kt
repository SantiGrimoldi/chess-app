package edu.austral.dissis.connection.client.listeners

import edu.austral.dissis.chess.gui.GameOver
import edu.austral.dissis.connection.client.ClientManager
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ClientGameOverListener(private val clientManager: ClientManager) : MessageListener<GameOver> {
    override fun handleMessage(message: Message<GameOver>) {
        clientManager.handleNewState(message.payload)
    }
}