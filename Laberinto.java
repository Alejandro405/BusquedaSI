import java.util.Random;

public class Laberinto {
	private int x, y;
	private char [][] matriz;
	private int prob;
	private int iniX, iniY, finX, finY;
	
	public Laberinto (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void generarLaberinto () {
		Random inicioX = new Random(x-1);
        Random inicioY = new Random(y-1);
        
	}

}
