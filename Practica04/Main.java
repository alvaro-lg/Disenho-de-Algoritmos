import java.util.Arrays;
import java.util.Random;

/**
 * Clase principal de la Practica 4 de Disenho de Algoritmos
 * @author Jairo Gonzalez, Alvaro Lopez, Nicolas Rodrigo
 * @version 12-11-2021
 */
public class Main {

    // Constante con la lista de valores del numero de matrices con los que se realizara el experimento
    private static final int[] NUM_MATRICES_EXP = {3, 4, 5, 6, 7, 8};

    // Constante con el maximo valor que pueden tomar la lista de dimensiones
    private static final int MAX = 100; // TODO: Averigual cual se especifica que sea en el enunciado

    public static void main(String[] args){

        Random rand = new Random();

        // Iteramos el numero de matrices con las que se va a operar en cada experimento
        for (int num : NUM_MATRICES_EXP) {

            // Array con dimensiones
            int[] dims = new int[num + 1];

            // TODO: Cambiar si se quiere que en cada iteracion las dimensiones sean distintas
            // Inicializacion aleatoria de las dimensiones de las matrices con las que se va a experimentar
            for (int n = 0; n < num + 1; n++) {
                dims[n] = rand.nextInt(MAX - 2 + 1) + 2;
            }

            System.out.println(Arrays.toString(dims));

            // Queremos calcular el elemento M[0][n -1]
            int[][] M = new int[num][num];

            // Calculo de para s = 0
            for (int i = 0; i < num; i++) {
                M[i][i] = 0;
            }

            // Calculo para 1 < s < n
            for (int s = 1; s < num; s++) {
                for (int i = 0; i < num - s; i++) {
                    M[i][i+s] = (int) Double.POSITIVE_INFINITY;
                    for (int k = i; k < i + s; k++) {
                        int tmp_min = M[i][k] + M[k+1][i+s] + dims[i]*dims[k+1]*dims[i+s+1];
                        if (M[i][i+s] >= tmp_min) {
                            M[i][i+s] = tmp_min;
                        }
                    }
                }
            }

            System.out.println("\n[");
            for (int[] fila : M) {
                System.out.println(Arrays.toString(fila));
            }
            System.out.println("]\n");

            System.out.println("SOLUCION: " + M[0][num - 1]);

        }
    }
}