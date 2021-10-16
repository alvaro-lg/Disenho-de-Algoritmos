import fundamentos.Grafica;

public class MainHanoi {
	
	// Atributos del problema
	private final static int N_DISCOS = 20;
	private static Grafica grafica = new Grafica("Discos / Movimientos", "No discos", "No movimientos");
	
	public static void main(String[] args) {
	
		// Creamos las torres y llenamos la primera
		TorreHanoi torre1 = new TorreHanoi("torre A", N_DISCOS);
		TorreHanoi torre2 = new TorreHanoi("torre B");
		TorreHanoi torre3 = new TorreHanoi("torre C");
		
		// Muestra situaci�n inicial
		System.out.println("Numero de discos: "+N_DISCOS+"\n");
		System.out.println(torre1.toString()+"\n"+torre2.toString()+"\n"+torre3.toString()+"\n");
		
		// Llamamos al algoritmo recursivo
		juega(torre1, torre2, torre3, N_DISCOS);
		
		// Par�metros de la gr�fica
		grafica.ponSimbolo(true);
		grafica.ponColor(Grafica.negro);
		
		// Realiza y muestra gr�fica
		puntos(grafica);
		grafica.pinta();
	}
	
	/**
	 * Algoritmo recursivo que implementa el juego de Torres de Hanoi.
	 * @param torre1 torre origen
	 * @param torre2 torre auxiliar
	 * @param torre3 torre destino
	 * @param discos n�mero de discos 
	 */
	public static void juega(TorreHanoi torre1, TorreHanoi torre2, TorreHanoi torre3, int discos) {
		// caso base
		if (discos == 1) {
			System.out.println("Se mueve disco " +torre1.discoArriba()+ " de "+torre1.nombre()+" a "+torre3.nombre());
			TorreHanoi.moverATorre(torre1, torre3);
			System.out.println(torre1.toString()+"\n"+torre2.toString()+"\n"+torre3.toString()+"\n");
			
		} else {
			discos--;
			juega(torre1, torre3, torre2, discos);
			System.out.println("Se mueve disco " +torre1.discoArriba() + " de "+torre1.nombre()+" a "+torre3.nombre());
			TorreHanoi.moverATorre(torre1, torre3);
			System.out.println(torre1.toString()+"\n"+torre2.toString()+"\n"+torre3.toString()+"\n");
			juega(torre2, torre1, torre3, discos);
		}
	}
	
	/**
	 * Anhade puntos a la gr�fica en funci�n del n�mero de discos.
	 * @param g gr�fica
	 */
	public static void puntos(Grafica g) {
		for (int i = 1; i <= N_DISCOS; i++) {
			g.inserta(i, Math.pow(2, i) - 1);
		}
	}
}
