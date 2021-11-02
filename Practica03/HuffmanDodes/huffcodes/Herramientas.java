package huffcodes;

/**
 * La clase <code>Herramientas</code> implementa una serie
 * de utilidades que facilitan la implementaci�n de los
 * c�digos de Huffman.
 */
public class Herramientas
{
	// Textos est�ticos de prueba.
	public static final String QUIJOTE=
		"En un lugar de la Mancha, de cuyo nombre no quiero acordarme";
	public static final String HAMLET=
		"To be, or not to be: that is the question";
	public static final String DIVINA_COMMEDIA=
		"Nel mezzo del cammin di nostra vita mi ritrovai per una selva oscura";

	/**
	 * Metoodo que transforma un numero en un vector de booleanos.
	 * 
	 * @param a Numero a transformar.
	 * @return  La representacion binaria del numero.
	 */
	public static Boolean[] int2bin(int a)
	{
		String res=int2binS(a);
		Boolean[] resB=new Boolean[res.length()];

		for (int i=0; i<res.length(); i++)
		{
			resB[i]=(res.charAt(i)=='1') ? new Boolean(true) : new Boolean(false);
		}

		return resB;
	}
	
	/**
	 * Metodo que transforma un numero en su representacion
	 * binaria.
	 * 
	 * @param num Numero a transformar.
	 * @return    La representacion binaria del numero.
	 */
	public static String int2binS(int num)
	{
		if (num/2!=1)
			return int2binS(num/2)+num%2;
		else
			return "1"+num%2;
	}
}
