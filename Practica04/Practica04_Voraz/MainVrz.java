import java.util.*;

/**
 * Clase principal del apartado complementario de la Practica 4 de Disenho de Algoritmos.
 * @author Jairo Gonzalez, Alvaro Lopez, Nicolas Rodrigo
 * @version 12-11-2021
 */
public class MainVrz {

    // Constante con la lista de valores del numero de matrices con los que se realizara el experimento
    private static final int[] NUM_MATRICES_EXP = {3, 4, 5, 6, 7, 8};

    // Constante con el maximo valor que pueden tomar la lista de dimensiones
    private static final int MAX = 100; // TODO: Averigual cual se especifica que sea en el enunciado

    public static void main(String[] args){

        Random rand = new Random();

        // Iteramos el numero de matrices con las que se va a operar en cada experimento
        for (int num : NUM_MATRICES_EXP) {

            // Numero de multiplicaciones
            int mults = 0;

            // Lista con dimensiones
            ArrayList<Integer> dims = new ArrayList<Integer>();

            // Cola de prioridad con dimensiones
            Queue<Integer> dims_prior = new PriorityQueue<Integer>(Collections.reverseOrder());

            // TODO: Cambiar si se quiere que en cada iteracion las dimensiones sean distintas
            // Inicializacion aleatoria de las dimensiones de las matrices con las que se va a experimentar
            for (int n = 0; n < num + 1; n++) {
                int aux = rand.nextInt(MAX - 2 + 1) + 2;
                dims.add(aux);
                dims_prior.add(aux);
            }


            for (int i = 0; i < num - 1; i++) {
                int max_dim = dims_prior.poll();
                System.out.println(dims.toString());
                System.out.println(max_dim);
                for (int j = 1; j < dims.size() - 1; j++) {
                    if (dims.get(j) == max_dim) {
                        mults += dims.get(j - 1) * dims.get(j) * dims.get(j + 1);
                        dims.remove(j);
                    }
                }
            }
            mults += dims.get(0) * dims.get(1) * dims.get(2);
            System.out.println(dims.toString());

            System.out.println("SOLUCION: " + mults);

        }
    }
}