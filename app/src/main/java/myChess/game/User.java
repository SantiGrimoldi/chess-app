package myChess.game;

import edu.austral.dissis.chess.gui.PlayerColor;

public class User {
    private String name;
    private Partida partida;
    private PlayerColor color;


//    String __repr__ () {
//        return "User: " + this.name;
//    }

    public User (String name, PlayerColor color) {
        this.name = name;
        this.color = color;
    }

    public User (String name, Partida partida) {
        this.name = name;
        this.partida = partida;
    }

    public User setPartida(Partida partida) {
        return new User(this.name, partida);
    }

    public Partida getPartida() {
        return this.partida;
    }

    public String getName () {
        return this.name;
    }

    public PlayerColor getColor () {
        return this.color;
    }
}

