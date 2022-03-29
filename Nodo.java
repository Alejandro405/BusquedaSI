public class Nodo implements Comparable<Nodo> {

    private int corX;
    private int corY;

    private int g;
    private int h;
    private int f;

    private Nodo padre;

    public Nodo (int cordX, int cordY, Nodo padre){
        this.corX=cordX;
        this.corY=cordY;

    }

    @Override
    public int compareTo(Nodo o) {
        // TODO Auto-generated method stub
        return 0;
    }
}