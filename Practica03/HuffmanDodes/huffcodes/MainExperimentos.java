import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Vector;

import static huffcodes.Compresor.*;

/**
 * Clase que realiza los experimentos requeridos
 */
public class MainExperimentos {

    //Variables para la ejecucion del experimento, se pueden cambiar para comprobar distintos rangos
    private static final int MAX_EXPERIMENTOS = 1000;
    private static final int MAX_LEN = 1000;
    private static final int MIN_LEN = 10;


    private static final Random rand = new Random();
    private static final int SIZE_OF_CHAR = 8;

    public static void main(String[] args) {

        //Crea un array de bytes que representara el texto original
        byte[] byteArr;

        int original_size_count = 0, compressed_size_count = 0;

        //Experimento 1
        for (int i = 0; i < MAX_EXPERIMENTOS; i++) {

            //Generamos aleatoriamente el texto de longitud fija a comprimir
            byteArr = new byte[MAX_LEN];

            rand.nextBytes(byteArr);
            String texto_original = new String(byteArr, StandardCharsets.UTF_8);
            String texto_comprimido = textToBin(texto_original);

            original_size_count += texto_original.length() * SIZE_OF_CHAR;
            compressed_size_count += texto_comprimido.length();
        }
        //Calculamos la compresion media
        double compresion_media = 100.0 * compressed_size_count / original_size_count;
        System.out.println("Compresion media para strings de " + MAX_LEN + " caracteres: " + compresion_media + "%");

        original_size_count = 0;
        compressed_size_count = 0;


        //Experimento 2
        for (int i = 0; i < MAX_EXPERIMENTOS; i++) {

            //Generamos aleatoriamente el texto a comprimir, asi como su longitud
            byteArr = new byte[rand.nextInt(MAX_LEN - MIN_LEN) + MIN_LEN];

            rand.nextBytes(byteArr);
            String texto_original = new String(byteArr, StandardCharsets.UTF_8);
            String texto_comprimido = textToBin(texto_original);

            original_size_count += texto_original.length() * SIZE_OF_CHAR;
            compressed_size_count += texto_comprimido.length();
        }
        compresion_media = 100.0 * compressed_size_count / original_size_count;
        System.out.println("Compresion media para strings de longitud aleatoria entre 10 y 1000: " + compresion_media + "%");
    }

    /**
     * Comprime un texto mediante el algoritmo de Huffman, utilizando las herramientas proporcionadas
     * @param texto texto a comprimir
     * @return un String binario que representa el texto comprimido
     */
    public static String textToBin(String texto) {

        // Se obtienen las frecuencias de las letras.
        Vector<huffcodes.Tree> vector= huffcodes.Compresor.obtenerComponentes(texto);

        vector= huffcodes.Compresor.ordenarMenorAMayorComponentes(vector);
        huffcodes.Tree huffman= huffcodes.Compresor.crearArbolDeCodigos(vector);

        // Se construye el diccionario.
        huffcodes.Diccionario dicc= huffcodes.Compresor.construirDiccionarioDesdeArbol(huffcodes.Compresor.obtenerAlfabeto(texto), huffman);

        // Se comprime el texto.
        Vector<Boolean> compr= huffcodes.Compresor.comprime(texto,dicc);

        return booleanVectorToString(compr);
    }
}
