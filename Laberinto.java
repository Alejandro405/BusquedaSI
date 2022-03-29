import java.util.*;

public class Laberinto {
	private static final char SALIDA = 'S';
	private static final char FIN = 'F';
	private static final char OBSTACULO = '#';
	private static final char LIBRE = ' ';
	private static final char VISITADO = '+';

	private int dimensionX, dimensionY;
	private char[][] matriz;
	private int prob = 30;
	private int iniX, iniY, finX, finY;
	private long seed;
	
	public Laberinto (int x, int y) {
		this.dimensionX = x;
		this.dimensionY = y;
        matriz = new char [dimensionX][dimensionY];
        for (int i = 0; i < dimensionX; i++) {
            for (int j = 0; j < dimensionY; j++) {
                matriz[i][j] = LIBRE;
            }
        }
	}
	
	public void generarLaberinto () {
		Random pos = new Random(seed); 
        int tamTotal = dimensionX * dimensionY;
        int numObstculos = (int)(tamTotal * (prob/100.0));
        int obsX, obsY;

        iniX = pos.nextInt(dimensionX-1);
        iniY = pos.nextInt(dimensionY-1);
        matriz [iniX][iniY] = SALIDA;

        finX = pos.nextInt(dimensionX-1);
        finY = pos.nextInt(dimensionY-1);

        while (iniX == finX && iniY == finY) {
            finX = pos.nextInt(dimensionX-1);
            finY = pos.nextInt(dimensionY-1);
        }
        matriz [finX][finY] = FIN;
        
        for (int i = 0; i < numObstculos; i++) {
            do {
                obsX = pos.nextInt(dimensionX-1);
                obsY = pos.nextInt(dimensionY-1);
            } while (!estLibre(obsX, obsY));
            matriz[obsX][obsY] = OBSTACULO;
        }
	}
    
    
    private boolean estLibre (int x, int y) {
        if(matriz[x][y] == LIBRE) {
            return true;
        }else{
             return false;
        }
    }

    public void setProbabilidad (int newProb) {
        prob = newProb;
		//actualizarTablero();
    }

    public void mostrarLaberinto () {

        System.out.println(this.toString());
      
    }

    public int getProbabilidad () {
        return prob;
    }


	@Override 
	public String toString(){
		StringJoiner res = new StringJoiner("\n");
		for (int i = 0; i < dimensionX; i++) {
            String linea = "";
            for (int j = 0; j < dimensionY; j++) {
                linea += "[" + matriz[i][j] + "]";
            }
			res.add(linea);
		}

		return res.toString();
	}

}

