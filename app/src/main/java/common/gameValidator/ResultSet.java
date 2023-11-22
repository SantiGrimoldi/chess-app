package common.gameValidator;

import common.Board;

public class ResultSet {
    private Board board;
    private final String Explanation;
    private final Boolean returnable;
    private final Boolean invalid;
    private final Boolean win;
    private final Boolean keepTurn;

    public ResultSet(Board board, String Explanation, Boolean returnable, Boolean invalid) {
        this.board = board;
        this.Explanation = Explanation;
        this.returnable = returnable;
        this.invalid = invalid;
        this.win = false;
        this.keepTurn = false;
    }

    public ResultSet(Board board, String Explanation, Boolean returnable, Boolean invalid, Boolean keepTurn) {
        this.board = board;
        this.Explanation = Explanation;
        this.returnable = returnable;
        this.invalid = invalid;
        this.win = false;
        this.keepTurn = keepTurn;
    }

    public ResultSet(Boolean win, Board board, String Explanation) {
        this.board = board;
        this.Explanation = Explanation;
        this.returnable = true;
        this.invalid = false;
        this.win = win;
        this.keepTurn = false;
    }

    public Board getBoard() {
        return board;
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

    public void setBoard(Board board) {
        this.board = board;
    }

    public Boolean keepTurn() {
        return keepTurn;
    }
}
