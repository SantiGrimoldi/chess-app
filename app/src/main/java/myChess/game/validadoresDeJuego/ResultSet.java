package myChess.game.validadoresDeJuego;

import myChess.game.Tablero;

public class ResultSet {
    private final Tablero tablero;
    private final String Explanation;
    private final Boolean returnable;
    private final Boolean invalid;

    public ResultSet(Tablero tablero, String Explanation, Boolean returnable, Boolean invalid) {
        this.tablero = tablero;
        this.Explanation = Explanation;
        this.returnable = returnable;
        this.invalid = invalid;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public String getExplanation() {
        return Explanation;
    }

    public Boolean getReturnable() {
        return returnable;
    }

    public Boolean getInvalid() {
        return invalid;
    }
}
