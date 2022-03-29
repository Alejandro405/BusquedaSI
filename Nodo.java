public class Nodo implements Comparable<Nodo>{

    private int corX;
    private int corY;
    private int coste;
    private double estimacion;

    private Nodo padre;

    public Nodo (int X, int Y, Nodo padre, int g, double h){
        corX = X;
        corY = Y;
        coste = g;
        estimacion = h;
    }

    public int getX () {
        return corX;
    }

    public int getY () {
        return corY;
    }

    public int getCoste () {
        return coste;
    }

    public Nodo getPadre () {
        return padre;
    }

    public double getEstimacion(){
        return estimacion;
    }
    
    public boolean equals(Nodo o){
        return (o.getX() == corX && o.getY() == corY);
            
    }
    
	@Override
	public int compareTo(Nodo o) {
        if (o.getX() == corX && o.getY() == corY) {
            return 0;
        }else{
            return -1;
        }
		//return (int)((coste + estimacion) - (o.getCoste() + o.getEstimacion()));
	}

    @Override
    public String toString () {
        return "x: " + corX + " /y: " + corY ;
        //+ " /Coste: " + coste + " /Estimacion: " + estimacion;
    }
}