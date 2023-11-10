package edu.austral.dissis.connection.server

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move
import edu.austral.ingsis.clientserver.Message
import edu.austral.ingsis.clientserver.MessageListener

class ServerMessageListener (private val listener: GameEventListener): MessageListener<Move>{

    override fun handleMessage(message: Message<Move>) {
        listener.handleMove(message.payload)
    }
}