package da.p1;
/**
 * Clase para ordenar una secuencia de objetos de tipo T usando ordenacion por insercion
 * @author Jose Luis Monta�a, Cruz E. Borges, Ines Gonzalez
 * @param <T extends Comparable<T>>
 */
import java.util.Comparator;

public class InsertionSort<T> implements Sort<T> {
    
	private Comparator<T> comp = null;
    
    /**
     * Constructor por defecto
     */
    public InsertionSort(){
    	comp=null;
    }
    
    /**
     * Constructor con un comparador distinto al predefinido para el tipo T
     * (para ordenar seg�n distintos criterios)
     * @param c el comparador de objetos de tipo T
     */
    public InsertionSort( Comparator<T> c ){
    	comp = c; 
    }
    
    /**
     * m�todo para ordenar una secuencia de principio a fin por inserci�n
     * @param d la secuencia de objetos de tipo T
     */
    public void sort( T[] d ){
    	sort( d, 0, d.length - 1 );
    	}
    
    /**
     * m�todo para ordenar una secuencia entre dos posiciones dadas usando inserci�n
     * @param d la secuencia de objetos de tipo T
     * @param start la posici�n inicial
     * @param end la posici�n final
     */
    public void sort( T[] d, int start, int end ) {
        T key;
        int i, j;
        
        // j indexa el elemento que se va a insertar
        // i indexa las posibles posiciones en las que se podr�a insertar el elemento j
        for ( j = start + 1; j <= end; j++ ){
            key = d[ j ];
            for ( i = j - 1; i >= 0 && compare( key, d[ i ] ) < 0; i-- )
                d[ i + 1 ] = d[ i ];
            d[ i + 1] = key;
        }
    }
    
    /**
     * m�todo para comparar objetos de tipo T usando el comparador adecuado
     * @param a un objeto
     * @param b otro objeto
     */
    private int compare( T a, T b ) {
        if ( comp == null ){
            return ((Comparable<T>)a).compareTo( b );
        }
        else {
            return comp.compare( a, b );
        }
    }
}