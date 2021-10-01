package da.p1;

import java.util.Comparator;
/**
 * Clase para ordenar una secuencia de objetos de tipo T usando ordenacion rapida (quicksort)
 * @author Jose Luis Montaña, Cruz E. Borges, Ines Gonzalez
 * @param <T>
 * @param <T extends Comparable<T>>
 */

public class QuickSort<T> implements Sort<T>
{
	private Comparator<T> comp;

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
    public void sort( T[] d ){
    	sort( d, 0, d.length - 1 ); 
    	}

    /**
     * método para ordenar una secuencia entre dos posiciones dadas usando inserción
     * @param d la secuencia de objetos de tipo T
     * @param start la posición inicial
     * @param end la posición final
     */
    public void sort( T[] d, int start, int end ){
        if ( start >= end )
            return;
            
        int p = partition( d, start, end );

        sort( d, start, p - 1 );
        sort( d, p + 1, end );
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
        	while ( compare( d[ ++low ], pivot ) < 0 ) ;
            // low pasa a ser la posicion mas a izda con elemento mayor que pivot
            while ( compare( pivot, d[ --high ] ) < 0 && high > low) ;
            // high pasa a ser la posicion mas a dcha (y mayor que low) con elemento menor que pivot
            if( low < high) exchange( d, low, high );// intercambiamos low y high si low<high
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