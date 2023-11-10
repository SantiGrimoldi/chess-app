package edu.austral.dissis.connection.client

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move

class ClientGameListener(private var client: ClientHandler) :  GameEventListener {
    override fun handleMove(move: Move) {
        client.sendMessage(move)
    }
}