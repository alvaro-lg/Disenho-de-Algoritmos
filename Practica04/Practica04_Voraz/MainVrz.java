package Practica04_Voraz;

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
    private static final int MAX = 100;

    public static void main(String[] args){
        int[] dims_aux = new int[9];

        Random rand = new Random();
        for (int n = 0; n < 9; n++) {
            dims_aux[n] = rand.nextInt(MAX - 2 + 1) + 2;
        }

        // Iteramos el numero de matrices con las que se va a operar en cada experimento
        for (int num : NUM_MATRICES_EXP) {
            Queue<Integer> dims_prior = new PriorityQueue<Integer>(Collections.reverseOrder());
            // Array con dimensiones
            int[] dims = new int[num + 1];

            // Inicializacion aleatoria de las dimensiones de las matrices con las que se va a experimentar
            for (int n = 0; n < num + 1; n++) {
                dims[n] = dims_aux[n];
                dims_prior.add(dims[n]);
            }


            System.out.println(Arrays.toString(dims));

            int mults = 0;
            int dims_size = num + 1;

            // Implementacion voraz del algorirmo: En cada iteracion multiplica aquellas matrices de dimensiones mxn y
            // nxr tal que n -> max
            while(dims_size > 3) {
                int max_dim = dims_prior.poll();
                boolean encontrado = false;
                System.out.println();

                for (int j = 1; j < dims_size ; j++) {
                    if (dims[j] == max_dim && j < dims_size - 1 && !encontrado) {
                        mults += dims[j - 1] * dims[j] * dims[j + 1];

                        encontrado = true;
                        dims_size--;
                    }
                    if (encontrado) {
                        dims[j] = dims[j+1];
                    }
                }

            }
            mults += dims[0] * dims[1] * dims[2];

            System.out.println("SOLUCION: " + mults);
        }
    }
}