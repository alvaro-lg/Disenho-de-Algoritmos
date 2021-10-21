import fundamentos.Grafica;
import java.util.Scanner;

public class MainHanoi {

	private static Grafica grafica = new Grafica("Discos / Movimientos", "No discos", "No movimientos");
	private static int movimientos = 0;
	private static boolean muestraMovimientos = true;
	private static final int NUM_DISCOS_ANALISIS_EFICIENCIA = 20;

	public static void main(String[] args) {
		//Entrada por consola del numero de discos
		System.out.println("Introduce el numero de discos para las Torres de Hanoi: \n");
		Scanner input = new Scanner(System.in);
		int num_discos = input.nextInt();

		// Atributos del problema
		// Creamos las torres y llenamos la primera
		TorreHanoi torre1 = new TorreHanoi("torre A", num_discos);
		TorreHanoi torre2 = new TorreHanoi("torre B");
		TorreHanoi torre3 = new TorreHanoi("torre C");

		// Muestra situacion inicial
		System.out.println("Numero de discos: " + num_discos + "\n");
		System.out.println(torre1 + "\n" + torre2 + "\n" + torre3 + "\n");

		// Llamamos al algoritmo recursivo
		juega(torre1, torre2, torre3, num_discos);
		muestraMovimientos = false;

		//Prueba el algoritmo aumentando el numero de discos y contando los movimientos que se hacen en cada ejecucion
		for (int i = 1; i <= NUM_DISCOS_ANALISIS_EFICIENCIA; i++) {
			movimientos = 0;
			torre1 = new TorreHanoi("torre A", i);
			torre2 = new TorreHanoi("torre B");
			torre3 = new TorreHanoi("torre C");
			juega(torre1, torre2, torre3, i);
			grafica.inserta(i, Math.pow(2, i) - 1);
		}

		// Parametros de la grafica
		grafica.ponSimbolo(true);
		grafica.ponColor(Grafica.azul);

		// Realiza y muestra grafica que muestra las operaciones realizadas para distinto numero de discos
		grafica.pinta();

		System.exit(0);
	}

	/**
	 * Algoritmo recursivo que implementa el juego de Torres de Hanoi.
	 *
	 * @param torre1 torre origen
	 * @param torre2 torre auxiliar
	 * @param torre3 torre destino
	 * @param discos n�mero de discos
	 */
	public static void juega(TorreHanoi torre1, TorreHanoi torre2, TorreHanoi torre3, int discos) {
		// caso base
		if (discos == 1) {
			if (muestraMovimientos) System.out.println("Se mueve disco " + torre1.discoArriba() + " de " + torre1.nombre() + " a " + torre3.nombre());
			TorreHanoi.moverATorre(torre1, torre3);
			movimientos++;
			if (muestraMovimientos) System.out.println(torre1 + "\n" + torre2 + "\n" + torre3 + "\n");

		} else {
			discos--;
			juega(torre1, torre3, torre2, discos);
			if (muestraMovimientos) System.out.println("Se mueve disco " + torre1.discoArriba() + " de " + torre1.nombre() + " a " + torre3.nombre());
			movimientos++;
			TorreHanoi.moverATorre(torre1, torre3);
			if (muestraMovimientos) System.out.println(torre1 + "\n" + torre2 + "\n" + torre3 + "\n");
			juega(torre2, torre1, torre3, discos);
		}
	}
}