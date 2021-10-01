package da.p1;

/**
 * Interfaz para ordenar una secuencia de objetos de tipo T
 * (los objetos de tipo T han de poder compararse entre sí)
 * @author Ines Gonzalez, Jose Luis Montaña
 */
public interface Sort<T>
{
    public void sort( T[] d, int start, int end );
    public void sort( T[] d );
}