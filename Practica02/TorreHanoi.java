/**
 * Clase que modela una torre de hanoi 
 */
import java.util.Stack; //importamos el paquete de pilas

public class TorreHanoi {
	
	//atributos
	private Stack<Integer> pilaTorre = new Stack<Integer>(); //pila de discos 
	private String nombreTorre; //nombre de la Torre
	
	/**
	 * Constructor al que se le pasa la Torre y crea una torre vacÃ­a 
	 */
	public TorreHanoi(String nombreTorre) { 
		this.nombreTorre = nombreTorre;
	}
	
	/**
	 * Crea una Torre de Hanoi con el nombre que se le pasa y los n discos 
	 */
	public TorreHanoi (String nombre, int n){ 
		nombreTorre = nombre;
		for (int i = n; i >= 1; i--){
			pilaTorre.push(i);
		} 
	}
	
	/**
	 * Mover disco de una torre a otra.
	 * @param origen torre origen
	 * @param destino torre destino
	 */
	public static void moverATorre(TorreHanoi origen, TorreHanoi destino) { 
		destino.pilaTorre.push(origen.pilaTorre.pop());
	}
	
	/**
	 * Observador de nombre.
	 * @return nombre de la torre
	 */
	public String nombre() {
		return nombreTorre;
	}
	
	/**
	 * Observador de cima de la torre.
	 * @return cima de la pila
	 */
	public int discoArriba() {
		return pilaTorre.peek();
	}
	
	/**
	 * Observador de número de discos de la torre.
	 * @return numero de discos
	 */
	public int numDiscos() {
		return pilaTorre.size();
	}
	
	@Override
	public String toString() {
		StringBuilder estadoTorre = new StringBuilder(); 
		estadoTorre.append("La torre "); 
		estadoTorre.append(nombreTorre); 
		estadoTorre.append(" contiene: "); 
		estadoTorre.append(pilaTorre.toString()); 
		return estadoTorre.toString();
	} 
}


