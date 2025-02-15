package edu.austral.dissis.connection.client.listeners


import edu.austral.dissis.chess.gui.NewGameState
import edu.austral.dissis.connection.client.ClientHandler
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ClientNewStateListener(private val client: ClientHandler) : MessageListener<NewGameState> {
    override fun handleMessage(message: Message<NewGameState>) {
        client.handleNewState(message.payload)
    }
}