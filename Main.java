import java.util.ArrayList;

public class Main {
    public static void main (String [ ] args) {
        
        Laberinto prueba = new Laberinto (5,5);
        Astar h0 = new Astar(prueba, 2);

        //h0.mostrarSolucion(h0.resolver());

        
        prueba.generarLaberinto();
        prueba.mostrarLaberinto();
        //Nodo a = new Nodo (0,0,null, 0, 50);
       // Nodo b = new Nodo (0,0,null, 4, 50);
        //System.out.println(a.equals(b));
        System.out.println("-------------------------------------");
        
        //Astar distManh = new Astar(prueba, 2);
        //Astar distEu = new Astar(prueba, 3);
        ArrayList<Nodo> trampa = new ArrayList<>();
        trampa.add(new Nodo(2, 3, null, 0, 0));
        trampa.add(new Nodo(1, 3, null, 0, 0));
        trampa.add(new Nodo(0, 3, null, 0, 0));
        trampa.add(new Nodo(0, 2, null, 0, 0));
        prueba.pintarSolucion(h0.resolver());

    }
} 