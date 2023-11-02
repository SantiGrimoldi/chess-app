package common;

public class Posicion {
    private int x;
    private int y;

    public Posicion (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Posicion posicion) {
        return this.x == posicion.getX() && this.y == posicion.getY();
    }
}
