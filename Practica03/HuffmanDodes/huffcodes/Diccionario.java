package huffcodes;

import java.util.Vector;

/**
 * La clase <code>Diccionario</code> es un vector de letras y
 * codigos de letras. Estos ultimos se representan como vectores
 * de booleanos.
 */
public class Diccionario
{
	/**
	 * La clase <code>TablaCodigoItem</code> representa cada uno
	 * de los elementos del diccionario.
	 */
	private class TablaCodigoItem
	{
		char letra;
		Boolean[] codigo;

		TablaCodigoItem(char letra, Boolean[] codigo)
		{
			this.letra=letra;
			this.codigo=codigo;
		}

		char getLetra() { return letra; }
		Boolean[] getCodigo() { return codigo; }
	}

	/**
	 * Diccionario.
	 */
	private Vector<TablaCodigoItem> diccionario;

	/**
	 * Constructor de objetos de la clase.
	 * 
	 * @param size Tamañoo del diccionario.
	 */
	public Diccionario(int size)
	{
		diccionario=new Vector<TablaCodigoItem>(size);
	}
	
	/**
	 * Metodo que añade una letra y su codigo al diccionario.
	 * 
	 * @param letra  Letra a añadir.
	 * @param codigo Codigo a añadir.
	 */
	public void setEntradaDeDiccionario(char letra, Boolean[] codigo)
	{
		diccionario.addElement(new TablaCodigoItem(letra,codigo));
	}

	/**
	 * Metodo que obtiene la codificacion binaria de la letra.
	 * 
	 * @param letra Letra de la que obtener su codificacion
	 *              binaria.
	 * @return      La codificacion binaria de la letra.
	 */
	public Boolean[] getCodificacion(char letra)
	{
		for (int i=0; i<diccionario.size(); i++)
		{
			if (diccionario.elementAt(i).getLetra()==letra)
				return diccionario.elementAt(i).getCodigo();
		}
		
		return null;
	}
	
	/**
	 * Metodo que obtiene la representacion grafica binaria
	 * del codigo.
	 * 
	 * @param codigo Codigo del que obtener su representacion
	 *               grafica binaria.
	 * @return       La representacion grafica binaria del
	 *               codigo.
	 */
	public static String boolArraytoString(Boolean[] codigo)
	{
		String res="";

		for (int j=0; j<codigo.length; j++)
		{
			if (codigo[j].booleanValue())
				res=res+"1";
			else
				res=res+"0";
		}

		res=res+"\r\n";

		return res;
	}
	
	/**
	 * Metodo que muestra la representacion grafica del
	 * diccionario.
	 * 
	 * @return La representacion grafica del diccionario.
	 */
	@Override
	public String toString()
	{
		String res="";
		TablaCodigoItem item;
		
		for (int i=0; i<diccionario.size(); i++)
		{
			item=diccionario.elementAt(i);
			res=res+item.getLetra()+" : ";

			res+=Diccionario.boolArraytoString(item.getCodigo());
		}
		
		return res;
	}
}
