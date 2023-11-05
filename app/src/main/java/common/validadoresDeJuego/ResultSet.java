package common.validadoresDeJuego;

import common.Tablero;

public class ResultSet {
    private Tablero tablero;
    private final String Explanation;
    private final Boolean returnable;
    private final Boolean invalid;
    private final Boolean win;
    private final Boolean keepTurn;

    public ResultSet(Tablero tablero, String Explanation, Boolean returnable, Boolean invalid) {
        this.tablero = tablero;
        this.Explanation = Explanation;
        this.returnable = returnable;
        this.invalid = invalid;
        this.win = false;
        this.keepTurn = false;
    }

    public ResultSet(Tablero tablero, String Explanation, Boolean returnable, Boolean invalid, Boolean keepTurn) {
        this.tablero = tablero;
        this.Explanation = Explanation;
        this.returnable = returnable;
        this.invalid = invalid;
        this.win = false;
        this.keepTurn = keepTurn;
    }

    public ResultSet(Boolean win, Tablero tablero, String Explanation) {
        this.tablero = tablero;
        this.Explanation = Explanation;
        this.returnable = true;
        this.invalid = false;
        this.win = win;
        this.keepTurn = false;
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

    public Boolean getWin() {
        return win;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public Boolean keepTurn() {
        return keepTurn;
    }
}
