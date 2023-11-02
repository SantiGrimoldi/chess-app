package common;

import edu.austral.dissis.chess.gui.PlayerColor;

public class User {
    private String name;
    private PlayerColor color;


//    String __repr__ () {
//        return "User: " + this.name;
//    }

    public User (String name, PlayerColor color) {
        this.name = name;
        this.color = color;
    }



    public String getName () {
        return this.name;
    }

    public PlayerColor getColor () {
        return this.color;
    }
}

