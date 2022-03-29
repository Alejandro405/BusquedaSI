public class Nodo implements Comparable<Nodo>{

    private int corX;
    private int corY;
    private int g;
    private double h;
    private int f;

    private Nodo padre;

    public Nodo (int cordX, int cordY, Nodo padre, int coste, double estimacion){
        this.corX=cordX;
        this.corY=cordY;

        h = estimacion;
        g = coste;
    }

    public int getX () {
        return corX;
    }

    public int getY () {
        return corY;
    }

    public int getCoste () {
        return g;
    }

    
    public boolean equals(Nodo o){
        return o.corX == this.corX && o.corY == this.corY;
            
    }
	@Override
	public int compareTo(Nodo o) {
		// TODO Auto-generated method stub
		return this.f - o.f;
	}
}