import java.util.*;

public class Laberinto {
    private static final char SALIDA = 'I';
    private static final char FIN = 'G';
    private static final char OBSTACULO = '*';
    private static final char LIBRE = ' ';
    private static final char VISITADO = '+';
    private static final char ABIERTO = 'A';
    private static final char CERRADO = 'C';

    private int dimensionX, dimensionY;
    private char[][] matriz;
    private int prob = 30;
    private int iniX, iniY, finX, finY;
    private long seed;

    public Laberinto(int x, int y) {
        this.dimensionX = x;
        this.dimensionY = y;
        matriz = new char[dimensionX][dimensionY];

        for (int i = 0; i < dimensionX; i++) {

            for (int j = 0; j < dimensionY; j++) {
                matriz[i][j] = LIBRE;
            }

        }
    }

    public int getProbabilidad() {
        return prob;
    }

    public int getDimX() {
        return dimensionX;
    }

    public int getDimY() {
        return dimensionY;
    }

    public int getInicX() {
        return this.iniX;
    }

    public int getInicY() {
        return this.iniY;
    }

    public int getFinX() {
        return finX;
    }

    public int getFinY() {
        return finY;
    }

    public void generarLaberinto() {
        Random pos = new Random(seed);
        int tamTotal = dimensionX * dimensionY;
        int numObstculos = (int) (tamTotal * (prob / 100.0));
        int obsX, obsY;

        iniX = pos.nextInt(dimensionX - 1);
        iniY = pos.nextInt(dimensionY - 1);
        matriz[iniX][iniY] = SALIDA; // Establecemos el inicio

        finX = pos.nextInt(dimensionX - 1);
        finY = pos.nextInt(dimensionY - 1);

        while (iniX == finX && iniY == finY) { // Evitamos que el inicio y el fin sean la misma pos
            finX = pos.nextInt(dimensionX - 1);
            finY = pos.nextInt(dimensionY - 1);
        }

        matriz[finX][finY] = FIN; // Establecemos el fin

        for (int i = 0; i < numObstculos; i++) { // Ponemos todos los obstaculos

            do {
                obsX = pos.nextInt(dimensionX - 1);
                obsY = pos.nextInt(dimensionY - 1);
            } while (!estaLibre(obsX, obsY));

            matriz[obsX][obsY] = OBSTACULO;
        }
    }

    public void marcarAbierto(int x, int y) {
        if(estaLibre(x, y)) {
            matriz[x][y] = ABIERTO;
        }
    }

    public void marcarCerrado(int x, int y) {
        if (matriz[x][y] == ABIERTO) {
            matriz[x][y] = CERRADO;
        }
    }

    public boolean estaLibre(int x, int y) {

        if (matriz[x][y] == LIBRE || matriz[x][y] == FIN) {
            return true;
        } else {
            return false;
        }

    }

    public void actualizarObstaculo(int newProb) {
        prob = newProb;
        this.generarLaberinto();
    }

    public void mostrarLaberinto() {
        System.out.println(this.toString());
    }

    public boolean esObjetivo(Nodo node) {
        return node.getX() == finX && node.getY() == finY;
    }

    public void pintarSolucion(ArrayList<Nodo> solucion) {

        for (int i = 0; i < solucion.size(); i++) {

            Nodo n = solucion.get(i);
            matriz[n.getX()][n.getY()] = VISITADO;
            
        }

        System.out.println("-------------------------------------");
        mostrarLaberinto();

    }

    @Override
    public String toString() {
        StringJoiner res = new StringJoiner("\n");

        for (int i = 0; i < dimensionX; i++) {

            String linea = "";

            for (int j = 0; j < dimensionY; j++) {
                linea += "[" + matriz[j][i] + "]"; // Analiza por columnas, permutamos los indices
            }

            res.add(linea);

        }

        return res.toString();
    }

}
