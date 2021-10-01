package da.p1;

/**
 * Interfaz para ordenar una secuencia de objetos de tipo T
 * (los objetos de tipo T han de poder compararse entre s�)
 * @author Ines Gonzalez, Jose Luis Monta�a
 */
public interface Sort<T>
{
    public void sort( T[] d, int start, int end );
    public void sort( T[] d );
}