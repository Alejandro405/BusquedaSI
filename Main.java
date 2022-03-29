import java.util.ArrayList;

public class Main {
    public static void main (String [ ] args) {

        Laberinto prueba = new Laberinto (5,5);

        prueba.generarLaberinto();
        prueba.mostrarLaberinto();

        System.out.println("Resultado-------------------------------------");
        Astar h0 = new Astar(prueba, 1);
        //Astar distManh = new Astar(prueba, 2);
        //Astar distEu = new Astar(prueba, 3);
        ArrayList<Nodo> trampa = new ArrayList<>();
        trampa.add(new Nodo(2, 3, null, 0, 0));
        trampa.add(new Nodo(1, 3, null, 0, 0));
        trampa.add(new Nodo(0, 3, null, 0, 0));
        trampa.add(new Nodo(0, 2, null, 0, 0));
        prueba.pintarSolucion(h0.resolver());
        prueba.mostrarLaberinto();
        h0.sucesores(new Nodo(2, 3, null, 0, 0));

    }
} 