package edu.austral.dissis.connection.server

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move

class ServerGameListener (private var serverHandler : ServerHandler) : GameEventListener {
    override fun handleMove(move: Move) {
        serverHandler.handleMove(move)
    }

}