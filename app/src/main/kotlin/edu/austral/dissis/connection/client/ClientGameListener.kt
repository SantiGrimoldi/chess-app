package edu.austral.dissis.connection.client

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move

class ClientGameListener(private var client: ClientManager) :  GameEventListener {
    override fun handleMove(move: Move) {
        client.sendMessage(move)
    }
}