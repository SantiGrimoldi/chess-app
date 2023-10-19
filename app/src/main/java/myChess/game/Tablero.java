package myChess.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Tablero {
    private List<List<Pieza>> tablero;

    public Tablero (int x, int y) {
        tablero = new ArrayList<>();
        for (int i=0; i<x;i++){
            List<Pieza> columnas = new ArrayList<>();
            tablero.add(columnas);
            for (int j=0; j<y;j++){
                columnas.add(null);
            }
        }
    }
    public Tablero (List<List<Pieza>> tablero) {
        this.tablero = tablero;
    }

    public Tablero agregarPieza (Pieza pieza, Posicion posicion) {
        List<List<Pieza>> tableroAux = clonar();
        tableroAux.get(posicion.getX()).set(posicion.getY(), pieza);
        return new Tablero(tableroAux);
    }

    public Boolean tienePieza (Posicion posicion) {
        return tablero.get(posicion.getX()).get(posicion.getY()) != null;
    }

    public Tablero eliminarPieza(Posicion posicion) {
        List<List<Pieza>> tableroAux = clonar();
        tableroAux.get(posicion.getX()).set(posicion.getY(), null);
        return new Tablero(tableroAux);
    }

    public Pieza obtenerPieza (Posicion posicion) {
        return tablero.get(posicion.getX()).get(posicion.getY());
    }

//    public Tablero moverPieza (Posicion posicionInicial, Posicion posicionFinal) {
//        Pieza pieza = obtenerPieza(posicionInicial);
//        return createSiguienteTablero(posicionInicial, posicionFinal, pieza);
//    }

    public Tablero moverPieza(Posicion posicionInicial, Posicion posicionFinal, Pieza pieza) {
        List<List<Pieza>> nuevaLista = clonar();
        Tablero nuevoTablero = new Tablero(nuevaLista);
        nuevoTablero = nuevoTablero.eliminarPieza(posicionInicial);
        nuevoTablero = nuevoTablero.agregarPieza(pieza, posicionFinal);
        return nuevoTablero;
    }

    public boolean estaDentoDeLosLimites(Posicion posicion) {
        return tablero.size() < posicion.getX() || tablero.get(posicion.getX()).size() < posicion.getY();
    }

    public Tablero forzarMovimiento(Pieza pieza, Posicion posicionFinal) {
        List<List<Pieza>> nuevaLista = clonar();
        Tablero nuevoTablero = new Tablero(nuevaLista);
        nuevoTablero = nuevoTablero.agregarPieza(pieza, posicionFinal);
        return nuevoTablero;
    }

    public List<Posicion> reyes (User jugador) {
        List<Posicion> reyes = new ArrayList<>();
        for (int i=0; i<tablero.size();i++){
            for (int j=0; j<tablero.get(i).size();j++){
                if (tablero.get(i).get(j) != null && tablero.get(i).get(j).isPiezaGanadora()) {
                    reyes.add(new Posicion(i,j));
                }
            }
        }
        return reyes;
    }

    private List<List<Pieza>> getTablero() {
        return tablero;
    }

    public int getFilas(){
        return tablero.size();
    }

    public int getColumnas(){
        return tablero.get(0).size();
    }

    private List<List<Pieza>> clonar() {
        List<List<Pieza>> nuevaLista = new ArrayList<>();
        for (int i=0; i<tablero.size();i++){
            List<Pieza> columnas = new ArrayList<>();
            nuevaLista.add(columnas);
            for (int j=0; j<tablero.get(i).size();j++){
                columnas.add(tablero.get(i).get(j));
            }
        }
        return nuevaLista;
    }

    public Map<Posicion, Pieza> getTodasLasPiezas() {
        Map<Posicion, Pieza> piezas = Map.of();
        for (int i=0; i<getFilas();i++){
            for (int j=0; j<getColumnas();j++){
                if (tablero.get(i).get(j) != null) {
                    piezas.put(new Posicion(i,j), tablero.get(i).get(j));
                }
            }
        }
        return piezas;
    }

    public String imprimirTablero(){
        String tableroString = "";
        for (int i=0; i<tablero.size();i++){
            for (int j=0; j<tablero.get(i).size();j++){
                tableroString += "|";
                if (tablero.get(i).get(j) == null) tableroString += "       ";
                else tableroString += " " + tablero.get(i).get(j).getNombre().toString() + "  ";
            }
            tableroString += "|\n";
        }
        return tableroString;
    }
}
