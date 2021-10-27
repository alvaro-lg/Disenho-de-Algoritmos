/**
 * Clase que implementa un nodo de Huffman, donde c es el caracter y f es la frecuencia
 * En caso de que no sea un nodo hoja, c recibe el valor Character.MIN_VALUE
 */
public class Node implements Comparable<Node> {
    // Atributos
    char c;
    int f;
    Node hijoIzq;
    Node hijoDer;

    /**
     * Constructor de la clase
     * @param c caracter
     * @param f frecuencia
     */
    public Node(char c, int f) {
        this.c = c;
        this.f = f;
    }

    /*
     * Getters y Setters
     */

    public Node getHijoIzq() {
        return hijoIzq;
    }

    public Node getHijoDer() {
        return hijoDer;
    }

    public void setHijoIzq(Node n) {
        this.hijoIzq = n;
    }

    public void setHijoDer(Node n) {
        this.hijoDer = n;
    }

    public char getC() {
        return c;
    }

    public int getF() {
        return f;
    }

    /**
     * Metodo compareTo que utiliza las frecuencias para establecer una prioridad en una PriorityQueue
     * @param n Nodo con el que se compara el nodo actual
     * @return <0 si el nodo actual tiene menor frecuencia, 0 si tienen la misma y >0 si tiene mas frecuencia
     */
    @Override
    public int compareTo(Node n) {
        return this.f - n.f;
    }
}

