package edu.austral.dissis.connection.server


import edu.austral.ingsis.clientserver.ServerConnectionListener

class ClientConectsToServerListener (private val serverHandler : ServerHandler): ServerConnectionListener {
    override fun handleClientConnection(clientId: String) {
//        println("Client connected: $clientId")
        serverHandler.sendInitialState(clientId)

    }

    override fun handleClientConnectionClosed(clientId: String) {
        TODO("Not yet implemented")
    }
}