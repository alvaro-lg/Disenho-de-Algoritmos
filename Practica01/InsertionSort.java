package Practica01;
/**
 * Clase para ordenar una secuencia de objetos de tipo T usando ordenacion por insercion
 * @author Jose Luis Montaña, Cruz E. Borges, Ines Gonzalez
 * @param <T extends Comparable<T>>
 */
import java.util.Comparator;
import java.util.Random;

public class InsertionSort<T> implements Sort<T> {
    
	private Comparator<T> comp = null;
    private int coste = 0;
    
    /**
     * Constructor por defecto
     */
    public InsertionSort(){
    	comp=null;
    }
    
    /**
     * Constructor con un comparador distinto al predefinido para el tipo T
     * (para ordenar según distintos criterios)
     * @param c el comparador de objetos de tipo T
     */
    public InsertionSort( Comparator<T> c ){
    	comp = c; 
    }
    
    /**
     * método para ordenar una secuencia de principio a fin por inserción
     * @param d la secuencia de objetos de tipo T
     */
    public int sort( T[] d ){
    	return sort( d, 0, d.length - 1 );
    }

    @Override
    public int tMedSort(int n, int num) {

        //Inicializa las variables necesarias e instancia un comparador y algoritmo de ordenacion
        int estimacion = 0;
        Integer vector[] = new Integer[n];
        Random rd = new Random();
        Comparator<Integer> c = new EnterosComparador();
        Sort<Integer> in = new InsertionSort<Integer>(c);

        //Itera desde 0 a numero de ejemplos
        for(int j = 0; j < num; j++) {

            //Inicializa el vector con numeros aleatorios
            for(int i = 0; i < n; i++) {
                vector[i] = rd.nextInt();
            }
            estimacion += in.sort(vector);
        }
        return estimacion/num;
    }

    /**
     * método para ordenar una secuencia entre dos posiciones dadas usando inserción
     * @param d la secuencia de objetos de tipo T
     * @param start la posición inicial
     * @param end la posición final
     * TODO revisar las operaciones de coste constante
     */
    public int sort( T[] d, int start, int end ) {
        T key;
        int i, j;
        
        // j indexa el elemento que se va a insertar
        // i indexa las posibles posiciones en las que se podría insertar el elemento j
        for ( j = start + 1; j <= end; j++ ){
            coste++;
            key = d[ j ];
            for ( i = j - 1; i >= 0 && compare( key, d[ i ] ) < 0; i-- ) {
                coste++;
                d[i + 1] = d[i];
            }
            d[ i + 1] = key;
            coste++;
        }
        return coste;
    }
    
    /**
     * método para comparar objetos de tipo T usando el comparador adecuado
     * @param a un objeto
     * @param b otro objeto
     */
    private int compare( T a, T b ) {
        coste++;
        if ( comp == null ){
            return ((Comparable<T>)a).compareTo( b );
        }
        else {
            return comp.compare( a, b );
        }
    }
}