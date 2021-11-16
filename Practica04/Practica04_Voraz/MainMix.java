package Practica04_Voraz;

import java.util.*;

/**
* Clase para comparar los dos algoritmos de la Practica 4 de Disenho de Algoritmos.
* @author Jairo Gonzalez, Alvaro Lopez, Nicolas Rodrigo
* @version 12-11-2021
*/
public class MainMix {

// Constante con la lista de valores del numero de matrices con los que se realizara el experimento
private static final int[] NUM_MATRICES_EXP = {3, 4, 5, 6, 7, 8};

// Constante con el maximo valor que pueden tomar la lista de dimensiones
private static final int MAX = 100;

public static void main(String[] args){

    Random rand = new Random();

    int[] dims_aux = new int[9];

    for (int n = 0; n < 9; n++) {
        dims_aux[n] = rand.nextInt(MAX - 2 + 1) + 2;
    }

    // Iteramos el numero de matrices con las que se va a operar en cada experimento
    for (int num : NUM_MATRICES_EXP) {
        Queue<Integer> dims_prior = new PriorityQueue<Integer>(Collections.reverseOrder());
        int[] dims = new int[num + 1];

        // Inicializacion aleatoria de las dimensiones de las matrices con las que se va a experimentar
        for (int n = 0; n < num + 1; n++) {
            dims[n] = dims_aux[n];
            dims_prior.add(dims[n]);
        }

        System.out.println("--------------PROG. DINAMICA--------------");
        System.out.println(Arrays.toString(dims));

        // Queremos calcular el elemento M[0][n -1]
        int[][] M = new int[num][num];

        // Calculo de para s = 0
        for (int i = 0; i < num; i++) {
            M[i][i] = 0;
        }

        // Solo para hacer debugging
        ArrayList<int[]> minPath = new ArrayList<int[]>();

        // Calculo para 1 < s < n
        for (int s = 1; s < num; s++) {
            for (int i = 0; i < num - s; i++) {
                M[i][i+s] = (int) Double.POSITIVE_INFINITY;
                for (int k = i; k < i + s; k++) {
                    int tmp_min = M[i][k] + M[k+1][i+s] + dims[i]*dims[k+1]*dims[i+s+1];
                    if (M[i][i+s] >= tmp_min) {
                        M[i][i+s] = tmp_min;
                        try { // Debugging
                            minPath.set(s, new int[]{i, k+1, i+s+1});
                        } catch (IndexOutOfBoundsException e) {
                            minPath.add(new int[]{i, k+1, i+s+1});
                        }
                    }
                }
            }
        }

        // Solo para hacer debugging
        for (int[] j : minPath) {
            System.out.println("Multiplicar M(" + dims[j[0]] + " x " + dims[j[1]] + ") " +
                    "por M(" + dims[j[1]] + " x " + dims[j[2]] + ")");
        }

        /*System.out.println("\n[");
        for (int[] fila : M) {
            System.out.println(Arrays.toString(fila));
        }
        System.out.println("]\n");*/

        System.out.println("SOLUCION: " + M[0][num - 1]);

        System.out.println("--------------ALG. VORAZ--------------");
        System.out.println(Arrays.toString(dims));

        // Numero de multiplicaciones
        int mults = 0;

        // Array con dimensiones
        int dims_size = num + 1;

        // Implementacion voraz del algorirmo: En cada iteracion multiplica aquellas matrices de dimensiones mxn y
        // nxr tal que n -> max
       while (dims_size > 3) {
            int max_dim = dims_prior.poll();
            boolean encontrado = false;

            for (int j = 1; j < dims_size ; j++) {
                if (dims[j] == max_dim && j < dims_size - 1 && !encontrado) {
                    mults += dims[j - 1] * dims[j] * dims[j + 1];
                    System.out.println("Multiplicar M(" + dims[j - 1] + " x " + dims[j] + ") " +
                            "por M(" + dims[j] + " x " + dims[j+1] + ")");
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
