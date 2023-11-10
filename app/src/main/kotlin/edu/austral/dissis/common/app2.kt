package edu.austral.dissis.common

import edu.austral.dissis.chess.gui.CachedImageResolver
import edu.austral.dissis.chess.gui.DefaultImageResolver
import edu.austral.dissis.chess.gui.GameView
import edu.austral.dissis.connection.client.ClientManager
import edu.austral.ingsis.clientserver.netty.client.NettyClientBuilder
import javafx.application.Application
import javafx.application.Application.launch
import javafx.scene.Scene
import javafx.stage.Stage

fun main() {
    launch(app2::class.java)
}

class app2  :  Application() {
    private val imageResolver = CachedImageResolver(DefaultImageResolver())
    private val root = GameView(imageResolver)
    private val builder = NettyClientBuilder.createDefault()
    private val client = ClientManager(root, builder)

    override fun start(primaryStage: Stage) {
        primaryStage.title = "Client"
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }
}