import java.util.*;
import java.lang.*;

public class Astar{
    
    //Los heurísticos son monótonos, no hay redir de punteros
    private Laberinto problema;
    private int heuristico;// 1 -> h = 0 |  2 -> h = distManhatan | 3 -> h = distEuclidç
    TreeSet<Nodo> Abiertos;
    TreeSet<Nodo> Cerrados;

    public Astar(Laberinto laberinto, int tipoHeuristico){
        problema = laberinto;
        heuristico = tipoHeuristico;
        Abiertos = new TreeSet();
        Cerrados = new TreeSet();
    }

    public double h(int x, int y){// 1 -> h = 0 |  2 -> h = distManhatan | 3 -> h = distEuclidç
        int finX = problema.getFinX();
        int finY = problema.getFinY();
        double res = 0;
        if (heuristico == 2){
            res = Math.abs(finX - x) - Math.abs(finY - y); 
        } else if (heuristico == 3){
            res = Math.sqrt(((finX - x)^2) - ((finY - y)^2));
        }

        return res;
    }

    public ArrayList<Nodo> resolver(){
        boolean encontradaSolucion = false;
        Abiertos.clear();
        Cerrados.clear();
        ArrayList<Nodo> sol = new ArrayList<>();  

        Abiertos.add(new Nodo(this.problema.getInicX(), problema.getInicY(), null, 0, 0));
        while (Abiertos.isEmpty() && !encontradaSolucion){
            Nodo n = Abiertos.pollFirst();
            Cerrados.add(n);
            if (!problema.esObjetivo(n)){
                Set<Nodo> m = sucesores(n);
                for (Nodo x : m){
                    if (!Abiertos.contains(x) && !Cerrados.contains(x)){
                        Abiertos.add(x);
                    }
                }
            } else {
                encontradaSolucion = true;
                sol = reconstruirSolucion();
            }
        }

        return sol;
    }

    private ArrayList<Nodo> reconstruirSolucion() {
        return null;
    }



    public Set<Nodo> sucesores (Nodo actual) {
        Set<Nodo> m = null;
        int posX = actual.getX();
        int posY = actual.getY();
        Nodo nuevo;

        if(posX+1 < problema.getDimX() && problema.estaLibre(posX+1, posY)) { //Arriba
            Abiertos.add(new Nodo(posX+1, posY, actual, actual.getCoste() + 1, h(posX + 1, posY)));
        }else if(posX-1 < problema.getDimX() && problema.estaLibre(posX-1, posY)) { //Abajo
            Abiertos.add(new Nodo(posX-1, posY, actual, actual.getCoste() + 1, h(posX - 1, posY)));
        }else if(posY+1 < problema.getDimY() && problema.estaLibre(posX, posY+1)) { //Derecha
            Abiertos.add(new Nodo(posX, posY+1, actual, actual.getCoste() + 1, h(posX, posY+1)));
        }else if(posY-1 < problema.getDimX() && problema.estaLibre(posX, posY-1)) { //Izquierda
            Abiertos.add(new Nodo(posX, posY-1, actual, actual.getCoste() + 1, h(posX, posY-1)));
        }

        return m;

    }
}