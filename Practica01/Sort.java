package Practica01;

/**
 * Interfaz para ordenar una secuencia de objetos de tipo T
 * (los objetos de tipo T han de poder compararse entre s?)
 * @author Ines Gonzalez, Jose Luis Monta?a
 */
public interface Sort<T>
{
    public int sort( T[] d, int start, int end );
    public int sort( T[] d );


    /**
     * Retorna una estimacion del tiempo medio de ordenar vectores de n
     * componentes usando num ejemplos aleatoriamente generados
     */
    public int tMedSort(int n, int num);
}