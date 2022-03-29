<<<<<<< Updated upstream
public class Nodo implements Comparable<Nodo> {

    private int corX;
    private int corY;

=======
public class Nodo implements <Comparable>{

    private int corX;
    private int corY;
>>>>>>> Stashed changes
    private int g;
    private int h;
    private int f;

    private Nodo padre;

    public Nodo (int cordX, int cordY, Nodo padre){
        this.corX=cordX;
        this.corY=cordY;
    }

    public int getX () {
        return corX;
    }

    public int getY () {
        return corY;
    }

    @Override
    public int compareTo(Nodo o) {
        // TODO Auto-generated method stub
        return 0;
    }
}