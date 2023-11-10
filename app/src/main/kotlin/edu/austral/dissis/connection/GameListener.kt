package edu.austral.dissis.connection

import edu.austral.dissis.chess.gui.GameEventListener
import edu.austral.dissis.chess.gui.Move
import edu.austral.dissis.connection.server.ServerHandler

class GameListener (private var serverHandler : ServerHandler) : GameEventListener {
    override fun handleMove(move: Move) {
        serverHandler.handleMove(move)
    }
    public fun setGame(serverHandler: ServerHandler){
        this.serverHandler = serverHandler
    }
}