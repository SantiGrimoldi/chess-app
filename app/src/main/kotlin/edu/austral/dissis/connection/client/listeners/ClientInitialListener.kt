package edu.austral.dissis.connection.client.listeners

import edu.austral.dissis.chess.gui.InitialState
import edu.austral.dissis.connection.client.ClientHandler
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener


class ClientInitialListener(private val clientHandler: ClientHandler) :  MessageListener<InitialState>{
    override fun handleMessage(message: Message<InitialState>) {
        clientHandler.handleInitialState(message.payload)
    }

}