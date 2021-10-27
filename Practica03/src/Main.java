import java.util.*;

/**
 * Clase principal de la Practica 3 de diseño de algoritmos
 * @author Jairo Gonzalez, Alvaro Lopez, Nicolas Rodrigo
 * @version 27-10-2021
 */
public class Main {

    //Texto sobre el que se realizara la compresion
    static final String TXT = "En un lugar de...";

    public static void main(String[] args) {

        //Crea el vector de frecuencias y el arbol de Huffman
        Map<Character, Integer> vectorFrecuencias = getFrecuencias(TXT);
        Node huffmanTree = huffman(vectorFrecuencias);
        System.out.println("----Frecuencias----");
        for (char c: vectorFrecuencias.keySet()) {
            System.out.println(c + ": " + vectorFrecuencias.get(c));
        }

        //crea el diccionario de compresion
        Map<Character, String> compresiones = diccionarioCompresion(huffmanTree);

        //Muestra los resultados por pantalla
        System.out.println("\n----Compresiones----");
        for (char c: compresiones.keySet()) {
            System.out.println(c + ": " + compresiones.get(c));
        }

        System.out.println("\n----Texto Comprimido----");
        System.out.println(comprime(TXT, compresiones));
    }


    /**
     * Funcion que, dado un texto y un diccionario de compresion, retorna el texto comprimido
     * @param text el texto original
     * @param dictCompresiones el diccionario de caracteres con sus compresiones
     * @return el texto comprimido
     */
    public static String comprime(String text, Map<Character, String> dictCompresiones) {
        String s = "";
        for (char c: text.toCharArray()) {
            s += dictCompresiones.get(c);
        }
        return s;
    }


    /**
     * Funcion que genera el diccionario de los caracteres, con su equivalencia comprimida
     * @param root Nodo raiz del arbol de huffman
     * @return el diccionario con los caracteres y sus compresiones
     */
    public static Map<Character, String> diccionarioCompresion (Node root) {
        String s = "";
        Map<Character, String> diccionarioCompresion = new HashMap<>();
        diccionarioCompresionAux(root, s, diccionarioCompresion);

        return diccionarioCompresion;
    }


    /**
     * Funcion auxiliar recursiva para recorrer el arbol de huffman en preorden y generar las compresiones de los caracteres
     * @param nodoActual nodo actual que se evalua en la funcion recursiva
     * @param s string de las compresiones
     * @param dict el diccionario donde se van guardando las compresiones
     */
    public static void diccionarioCompresionAux(Node nodoActual, String s, Map<Character, String> dict) {

        //Si no tiene hijos, es un nodo hoja, por lo que añadimos el caracter y su compresion al diccionario
        if (nodoActual.getHijoIzq() == null && nodoActual.getHijoDer() == null) {
            dict.put(nodoActual.getC(), s);
        } else {
            //Si no es un nodo hoja, sigue recorriendo en preorden los hijos izquierdo y derecho si es que tienen
            if (nodoActual.getHijoIzq() != null) diccionarioCompresionAux(nodoActual.getHijoIzq(), s + '0', dict);
            if (nodoActual.getHijoDer() != null)diccionarioCompresionAux(nodoActual.getHijoDer(), s + '1', dict);
        }
    }


    /**
     * Funcion que calcula el arbol de Huffman para unos caracteres y frecuencias
     * @param cf mapa con los caracteres y sus respectivas frecuencias
     * @return el arbol de huffman
     */
    private static Node huffman(Map<Character, Integer> cf) {
        PriorityQueue<Node> q = new PriorityQueue<>();

        //Crea todos los nodos de Huffman con sus caracteres y frecuencias y les añade a la cola de prioridad
        for (char c: cf.keySet()) {
            Node n = new Node(c, cf.get(c));
            q.add(n);
        }

        //Establece una raiz del arbol, que mas tarde sera asignada
        Node root = null;

        //Cuando la cola de prioridad solo tenga un elemento, ese elemento sera el arbol de Huffman
        while (q.size() > 1) {

            //Mientras quede mas de un elemento, va sacando de la cola los arboles con menor frecuencia y les va juntando
            Node x = q.poll();
            Node y = q.poll();

            Node z = new Node(Character.MIN_VALUE, x.getF() + y.getF());

            z.setHijoIzq(x);
            z.setHijoDer(y);
            root = z;

            q.add(z);
        }
        return root;
    }


    /**
     * Metodo para obtener un mapa cuya clave es un caracter y su valor es la frecuencia
     * @param s Texto en el que se busca la frecuencia
     * @param conjuntoLetras Conjunto de letras
     * @return vectorFrecuencias, un mapa con las frecuencias de los caracteres
     */
    private static Map<Character, Integer> getFrecuencias(String s) {
        //Creamos un mapa vacio
        Map<Character, Integer> vectorFrecuencias = new HashMap<>();
        int f;

        for (char c: s.toCharArray()) {
            if (!vectorFrecuencias.containsKey(c)) {
                //Si no esta en el mapa, se le añade con frecuencia = 1
                vectorFrecuencias.put(c, 1);
            } else {
                //Si ya estaba en el mapa, se le suma 1 a la frecuencia
                f = vectorFrecuencias.get(c);
                vectorFrecuencias.put(c, f+1);
            }
        }

        return vectorFrecuencias;

    }
}
