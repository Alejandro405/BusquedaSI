import java.util.*;

public class Astar{
    
    private static final int NUM_VECINOS = 4;
    //Los heurísticos son monótonos, no hay redir de punteros
    private Laberinto problema;
    private int heuristico;// 1 -> h = 0 |  2 -> h = distManhatan | 3 -> h = distEuclidç
    TreeSet<Nodo> Abiertos;
    TreeSet<Nodo> Cerrados;

    public Astar(Laberinto laberinto, int tipoHeuristico){
        problema = laberinto;
        heuristico = tipoHeuristico;
        Abiertos = new TreeSet<Nodo>();
        Cerrados = new TreeSet<Nodo>();
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
        Abiertos.clear();
        Cerrados.clear();
        boolean encontradaSolucion = false;
        ArrayList<Nodo> sol = new ArrayList<>();  

        Abiertos.add(new Nodo(this.problema.getInicX(), problema.getInicY(), null, 0, 0));

        while (!Abiertos.isEmpty() && !encontradaSolucion){
            Nodo n = Abiertos.pollFirst();
           // Abiertos.remove(n);
            Cerrados.add(n);
            /**problema.marcarCerrado(n.getX(), n.getY());*/
            
            if (!problema.esObjetivo(n)) {//sucesores(n);
                for (Nodo x : calcSucesores(n)){
                    if (!Abiertos.contains(x) && !Cerrados.contains(x)) {
                        Abiertos.add(x);
                    }
                }
            } else {
                encontradaSolucion = true;
                sol = reconstruirSolucion(n);
                //System.out.println(n);
            }
            /**System.out.println("-------------------------------------");
            problema.mostrarLaberinto();*/
        }
        
        problema.setSolucion(sol);
        return sol;
    }

    private ArrayList<Nodo> reconstruirSolucion(Nodo n) {

        ArrayList<Nodo> sol = new ArrayList<>();
        Nodo sig = n;

        while (sig != null) {
            sol.add(sig);
            sig = sig.getPadre();
        }

        return sol;
    }

    private void sucesores (Nodo actual) {


        int posX = actual.getX();
        int posY = actual.getY();
        Nodo nuevo;

        if(posX + 1 < problema.getDimX() && problema.estaLibre(posX+1, posY)) { //Derecha
            
            nuevo = new Nodo(posX + 1, posY, actual, actual.getCoste() + 1, h(posX + 1, posY));

            if (!Cerrados.contains(nuevo)) { //Comprueba que el nodo nuevo no se ha explorado antes
                Abiertos.add(nuevo); //Si el nodo nuevo ya esta en la lista de abiertos, no se añadirá ya que el que esta dentro de la lista de abiertos debería ser mejor
                problema.marcarAbierto(posX + 1, posY);
            }

        }

        if(posX-1 >= 0 && problema.estaLibre(posX-1, posY)) { //Izquierda
            
            nuevo = new Nodo(posX - 1, posY, actual, actual.getCoste() + 1, h(posX - 1, posY));

            if (!Cerrados.contains(nuevo)) {
                Abiertos.add(nuevo);
                problema.marcarAbierto(posX - 1, posY);
            }

        } 

        if(posY + 1 < problema.getDimY() && problema.estaLibre(posX, posY + 1)) { //Abajo
            
            nuevo = new Nodo(posX, posY + 1, actual, actual.getCoste() + 1, h(posX, posY + 1));

            if (!Cerrados.contains(nuevo)) {
                Abiertos.add(nuevo);
                problema.marcarAbierto(posX, posY + 1);
            }

        }

        if(posY - 1 >= 0 && problema.estaLibre(posX, posY - 1)) { //Arriba
            
            nuevo = new Nodo(posX, posY - 1, actual, actual.getCoste() + 1, h(posX, posY - 1));

            if (!Cerrados.contains(nuevo)) {
                Abiertos.add(nuevo);
                problema.marcarAbierto(posX, posY - 1);
            }

        }
    
    }

    private Set<Nodo> calcSucesores(Nodo n) {
        Set<Nodo> result = new HashSet<>();
        for (int i = 0; i < NUM_VECINOS; i++) {
            int n_y = n.getY();
            int n_x = n.getX();
            switch(i){
                case 0: //Arriba
                    Nodo nodoSup = new Nodo(n_x, n_y - 1, n.getPadre());
                    if (problema.enLaberinto(n_x, n_y - 1) && !this.problema.hayObstaculos(n_x, n_y - 1) /**&& !nodoSup.formaCiclo()*/){
                        result.add(new Nodo(n_x, n_y - 1, n, n.getCoste() + 1, h(n_x, n_y - 1)));
                    }
                    break;
                case 1: //Abajo 
                    Nodo nodoInf = new Nodo(n_x, n_y + 1, n.getPadre());
                    if (problema.enLaberinto(n_x, n_y + 1) && !this.problema.hayObstaculos(n_x, n_y + 1) /**&& !nodoInf.formaCiclo()*/){
                        result.add(new Nodo(n_x, n_y + 1, n, n.getCoste() + 1, h(n_x, n_y + 1)));
                    }
                    break;
                case 2: //Derecha
                    Nodo nodoDer = new Nodo(n_x + 1, n_y, n.getPadre());
                    if (problema.enLaberinto(n_x + 1, n_y) && !this.problema.hayObstaculos(n_x + 1, n_y) /**&& !nodoDer.formaCiclo()*/){
                        result.add(new Nodo(n_x + 1, n_y, n, n.getCoste() + 1, h(n_x + 1, n_y)));
                    }
                    break;
                case 3: //Izquierda
                    Nodo nodoIzq = new Nodo(n_x - 1, n_y, n.getPadre());
                    if (problema.enLaberinto(n_x - 1, n_y) && !this.problema.hayObstaculos(n_x - 1, n_y) /**&& !nodoIzq.formaCiclo()*/){
                        result.add(new Nodo(n_x - 1, n_y, n, n.getCoste() + 1, h(n_x - 1, n_y)));
                    }
                break;
            }
        }
        return result;
    }

    public void mostrarSolucion(ArrayList<Nodo> solucion){
        problema.pintarSolucion(solucion);
    }
}