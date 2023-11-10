package edu.austral.dissis.connection.client.listeners

import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.connection.client.ClientManager
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener


class ClientInitialListener(private val clientManager: ClientManager) :  MessageListener<InitialState>{
    override fun handleMessage(message: Message<InitialState>) {
        clientManager.handleInitialState(message.payload)
    }

}