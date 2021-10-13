package Practica01;

import java.util.Comparator;
import java.util.Random;

/**
 * Clase para ordenar una secuencia de objetos de tipo T usando ordenacion rapida (quicksort)
 * @author Jose Luis Montaña, Cruz E. Borges, Ines Gonzalez
 * @param <T>
 * @param <T extends Comparable<T>>
 */

public class QuickSort<T> implements Sort<T> {

	private Comparator<T> comp;
    private int coste = 0;

    /**
     * Constructor por defecto
     */
    public QuickSort() {
    	comp=null;
    }

    /**
     * Constructor con un comparador distinto al predefinido para el tipo T
     * (para ordenar según distintos criterios)
     * @param c el comparador de objetos de tipo T
     */
    public QuickSort( Comparator<T> c ){
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
        Sort<Integer> in = new QuickSort<Integer>(c);

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
    public int sort( T[] d, int start, int end ){
        if ( start >= end )
            return 0;
            
        int p = partition( d, start, end );

        sort( d, start, p - 1 );
        sort( d, p + 1, end );
        return coste;
    }
    
    /**
     * Método privado para obtener un pivote entre las posiciones start y end
     * y reordenar la secuencia (según Hoare partition)
     * @param d la secuencia de elementos
     * @param start la posicion inicial
     * @param end la posicion final
     * @return pivote la posicion del pivote que "parte" la secuencia en dos
     */
    private int partition( T[] d, int start, int end ){
        T pivot = d[ end ];
        int low = start - 1;
        int high = end;
        
        while ( low < high ){
        	while ( compare( d[ ++low ], pivot ) < 0 ) coste++;

            // low pasa a ser la posicion mas a izda con elemento mayor que pivot
            while ( compare( pivot, d[ --high ] ) < 0 && high > low) coste++;

            // high pasa a ser la posicion mas a dcha (y mayor que low) con elemento menor que pivot
            if( low < high) {
                exchange( d, low, high );// intercambiamos low y high si low<high
                coste++;
            }
        }
        exchange( d, low, end );// coloca el pivote en el "centro"
        return low;// retorna posicion del pivote
    }
 
    /**
     * Método privado para intercambiar 2 elementos del array
     * @param T el array
     * @param start posicion del primer elemento
     * @param end la posicion del segundo elemento
     */
    private void exchange( T [] a, int i, int j ){
        // inercambia los elementos low y high del array a
        T o = a[ i ];
        a[ i ] = a[ j ];
        a[ j ] = o;
    }
    
    /**
     * método para comparar objetos de tipo T usando el comparador adecuado
     * @param a un objeto
     * @param b otro objeto
     */
    private int compare( T a, T b ){
        if ( comp == null )
        {
            return (((Comparable<T>)a).compareTo( b ));
        }
        else
        {
            return comp.compare( a, b );
        }
    }
}