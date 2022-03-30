public class Nodo implements Comparable<Nodo>{

    private int corX;
    private int corY;
    private int coste;
    private double estimacion;

    private Nodo padre;

    public Nodo(int x, int y, Nodo p){
        corX = x;
        corY = y;
        padre = p;
    }

    public Nodo (int X, int Y, Nodo p, int g, double h){
        corX = X;
        corY = Y;
        coste = g;
        estimacion = g + h;
        padre = p;
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

    public boolean formaCiclo(){
        boolean nodoEncontrado = false;
        Nodo next = padre;
        while (!nodoEncontrado && next != null){
            if (this.equals(next)) {
                nodoEncontrado = true;
            } else {
                next = next.padre;
            }
        }
        return nodoEncontrado;
    }
    
    @Override
    public boolean equals(Object o){
        boolean res = false;
        if (o instanceof Nodo){
            res = (((Nodo) o).getX() == corX && ((Nodo) o).getY() == corY);
        }
        
        return res;  
    }
    
	@Override
	public int compareTo(Nodo o) {
        if (o.getX() != corX || o.getY() != corY) {
            return (int)((coste + estimacion) - (o.getCoste() + o.getEstimacion()));
        }else{
            return 0;
        }
		//return (int)((coste + estimacion) - (o.getCoste() + o.getEstimacion()));
	}

    @Override
    public String toString () {
        return "(" + corX + ", " + corY + ")";
        //+ " /Coste: " + coste + " /Estimacion: " + estimacion;
    }
}