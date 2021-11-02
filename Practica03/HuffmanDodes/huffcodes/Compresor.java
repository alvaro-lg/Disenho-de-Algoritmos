package huffcodes;

import java.text.StringCharacterIterator;
import java.util.Iterator;
import java.util.Vector;

/**
 * La clase <code>Compresor</code> implementa las operaciones
 * de compresion a travas de <i>Codigos de Huffman</i>.

 * @author In�s Gonz�lez y Jos� Luis Monta�a (original)
 * @author Camilo Palazuelos y Noelia Ruiz (adaptaci�n)
 * @version 10 de junio de 2010
 */
public class Compresor
{
	/**
	 * M�todo que determina si un texto contiene una
	 * sola letra.
	 * 
	 * @param texto Texto a comprobar si contiene una sola
	 *              letra.
	 * @return      <code>true</code> en caso de que el texto
	 *              contenga una sola letra o <code>false</code>
	 *              en caso contrario.
	 */
	private static boolean esLetra(String texto)
	{
		return texto.length()==1;
	}

	/**
	 * M�todo que cuenta las repeticiones de una letra en un
	 * texto.
	 * 
	 * @param textoCompleto Texto del que contar las repeticiones
	 *                      de la letra.
	 * @param letra         Letra de la que contar sus repeticiones.
	 * @return              El n�mero de repeticiones de la letra en
	 *                      el texto.
	 * @throws CompresorException
	 *                      En caso de que el texto contenga una sola
	 *                      letra.
	 */
	public static int cuentaRepeticiones(
			String textoCompleto, String letra) throws CompresorException
	{
		if (!esLetra(letra))
			throw new CompresorException("Texto a buscar no v�lido.");

		return cuentaRepeticiones(textoCompleto,letra.charAt(0));
	}

	/**
	 * M�todo que cuenta las repeticiones de una letra en un
	 * texto.
	 * 
	 * @param textoCompleto Texto del que contar las repeticiones
	 *                      de la letra.
	 * @param letra         Letra de la que contar sus repeticiones.
	 * @return              El n�mero de repeticiones de la letra en
	 *                      el texto.
	 */
	private static int cuentaRepeticiones(
			String textoCompleto, char letra)
	{
		int cont=0;
		StringCharacterIterator it=
			new StringCharacterIterator(textoCompleto);

		do {
			if (it.current()==letra)
				cont++;
		} while (it.next()!=StringCharacterIterator.DONE);

		return cont;
	}
	
	/**
	 * M�todo que determina si una letra est� en la cadena.
	 * 
	 * @param cadena Cadena a comprobar.
	 * @param letra  Letra a comprobar si est� en la cadena.
	 * @return       <code>true</code> si la letra est� en la
	 *               cadena y <code>false</code> en caso
	 *               contrario.
	 */
	private static boolean letraEnCadena(String cadena, char letra)
	{
		StringCharacterIterator it=
			new StringCharacterIterator(cadena);

		do {
			if (it.current()==letra)
				return true;
		} while (it.next()!=StringCharacterIterator.DONE);

		return false;
	}
	
	/**
	 * M�todo que obtiene el alfabeto de un texto.
	 * 
	 * @param textoCompleto Texto del que obtener su alfabeto.
	 * @return              El alfabeto del texto.
	 */
	public static String obtenerAlfabeto(String textoCompleto)
	{
		String letras="";
		StringCharacterIterator it=
			new StringCharacterIterator(textoCompleto);

		do {
			if (!letraEnCadena(letras,it.current()))
				letras=letras+it.current();
		} while (it.next()!=StringCharacterIterator.DONE);

		return letras;
	}
	
	/**
	 * M�todo que hace un vector con �rboles de letras y frecuencias
	 * a partir del texto. El vector puede estar desordenado.
	 * 
	 * @param texto Texto del que hacer un vector de �rboles
	 *              y frecuencias.
	 * @return      El vector con �rboles y frecuencias.
	 */
	public static Vector<Tree> obtenerComponentes(String texto)
	{
		Vector<Tree> componentes =new Vector<Tree>();
		String letras=obtenerAlfabeto(texto);

		for (int i=0; i<letras.length(); i++)
		{
			try {
				componentes.add(new Tree(
						cuentaRepeticiones(texto,letras.charAt(i)),
						letras.charAt(i),
						null,
						null));
			} catch (Exception e) {
				System.out.println("Exception :: "+e.getMessage());
			}
		}

		return componentes;
	}
	
	/**
	 * M�todo que ordena el vector de �rboles de letras y frecuencias
	 * de menor a mayor.
	 * 
	 * @param desordenado Vector de �rboles y frecuencias a ordenar.
	 * @return            El vector de �rboles y frecuencias ordenado.
	 */
	public static Vector<Tree> ordenarMenorAMayorComponentes(Vector<?> desordenado)
	{
		Vector<Tree> ordenado=new Vector<Tree>();
		int actual=0;
		int minimoCount=Integer.MAX_VALUE;
		Tree minimoItem=null;
		Tree item=null;
		Iterator<?> it=null;

		while (desordenado.size()!=0)
		{
			// Se busca el m�ximo.
			it=desordenado.iterator();

			do {
				item=(Tree)it.next();
				actual=(int)item.getKey();

				if (actual<minimoCount)
				{
					minimoCount=actual;
					minimoItem=item;
				}
			} while (it.hasNext());

			ordenado.add(minimoItem);
			desordenado.remove(minimoItem);

			actual=0;
			minimoCount=Integer.MAX_VALUE;
			minimoItem=null;
		}

		return ordenado;
	}
	
	/**
	 * M�todo que da la representaci�n gr�fica de los componentes
	 * del vector.
	 * 
	 * @param componentes Componentes de los que obtener su
	 *                    representaci�n gr�fica.
	 * @return            La representaci�n gr�fica de los
	 *                    componentes del vector.
	 */
	public static String componentesToString(Vector<Tree> componentes)
	{
		String retL="";
		String retC="";
		String tmp="";
		String res="";
		res=res+"--------------------------------------------------------"+"\r\n";

		for (int i=0; i<componentes.size(); i++)
		{
			tmp=""+((Tree)componentes.elementAt(i)).getKey();

			for (int j=tmp.length(); j<6; j++)
				retC=retC+" ";

			retC=retC+tmp+"|";
			retL=retL+"     "+((char)(
					(Tree)componentes.elementAt(i)).getData())+"|";

			if ((i+1)%8==0)
			{
				res=res+retL+"\r\n"+retC+"\r\n";
				res=res+"________________________________________________________"+"\r\n";
				retL="";
				retC="";
			}
		}

		res=res+retL+"\r\n"+retC+"\r\n";
		res=res+"--------------------------------------------------------"+"\r\n";

		return res;
	}
	
	/**
	 * M�todo que crea el �rbol de c�digos a partir del vector
	 * ordenado de �rboles con frecuencias y letras.
	 * 
	 * @param componentesOrdenados Vector del que crear su �rbol
	 *                             de c�digos.
	 * @return                     El �rbol de c�digos del vector
	 *                             ordenado de �rboles.
	 */
	public static Tree crearArbolDeCodigos(Vector<Tree> componentesOrdenados)
	{
		Tree iz=componentesOrdenados.get(0);
		Tree dr=componentesOrdenados.get(1);

		// Caso b�sico: �rbol con 2 � menos componentes.
		if (componentesOrdenados.size()<=2)
		{
			return new Tree(
					iz.getKey()+dr.getKey(),
					'\0',
					iz,
					dr);
		}

		// Caso recursivo.
		else
		{
			// Vector de �rboles.
			Vector<Tree> vector=new Vector<Tree>();

			// �rbol producto de los dos primeros componentes.
			Tree arbol=new Tree(
					iz.getKey()+dr.getKey(),
					'\0',
					iz,
					dr);

			// Se a�ade el �rbol al vector.
			vector.add(arbol);

			// Se a�aden los dem�s componentes.
			for (int i=2; i<componentesOrdenados.size(); i++)
				vector.add(componentesOrdenados.get(i));

			// Se ordena el vector.
			vector=ordenarMenorAMayorComponentes(vector);

			return crearArbolDeCodigos(vector);
		}
	}
	
	/**
	 * M�todo que comprime el texto utilizando el diccionario y lo
	 * obtiene en un vector de booleanos.
	 * 
	 * @param texto Texto a comprimir.
	 * @param dicc  Diccionario con que comprimir el texto.
	 * @return      El vector de booleanos (representaci�n binaria)
	 *              del texto comprimido.
	 */
	public static Vector<Boolean> comprime(String texto, Diccionario dicc)
	{
		Vector<Boolean> aux=new Vector<Boolean>();
		StringCharacterIterator it=
			new StringCharacterIterator(texto);
		char letra;
		Boolean[] codigo;

		do {
			letra=it.current();
			codigo=dicc.getCodificacion(letra);

			for (int i=0; i<codigo.length; i++)
				aux.add(codigo[i]);
		} while (it.next()!=StringCharacterIterator.DONE);

		return aux;
	}
	
	/**
	 * M�todo que obtiene la representaci�n gr�fica del texto
	 * codificado en binario.
	 * 
	 * @param texto Texto del que obtener su representaci�n
	 *              gr�fica en binario.
	 * @return      La representaci�n gr�fica del texto
	 *              codificado en binario.
	 */
	public static String codificacionTextoEnBinario(String texto)
	{
		String res="";
		StringCharacterIterator it=
			new StringCharacterIterator(texto);
		int caracter=0;

		do {
			caracter=(int)it.current();
			res+=Herramientas.int2binS(caracter);
		} while (it.next()!=StringCharacterIterator.DONE);

		return res;
	}
	
	/**
	 * M�todo que obtiene la representaci�n binaria del vector de
	 * booleanos que codifica un texto.
	 * 
	 * @param a Vector de booleanos que codifica un texto.
	 * @return  Representaci�n binaria del vector de booleanos.
	 */
	public static String booleanVectorToString(Vector<Boolean> a)
	{
		String res="";
		if (a.size()>0)
		{
			if (a.elementAt(0) instanceof Boolean)
				for (int i=0; i<a.size(); i++)
					res+=((Boolean)a.elementAt(i)).booleanValue() ? "1" : "0";
			else
				return null;
		}
		return res;
	}
	
	/**
	 * M�todo que construye el diccionario una vez hecho el �rbol.
	 * 
	 * @param alfabeto Alfabeto a partir del que construir el
	 *                 diccionario.
	 * @param arbol    �rbol de frecuencias y letras.
	 * @return         El diccionario del �rbol y del alfabeto.
	 */
	public static Diccionario construirDiccionarioDesdeArbol(String alfabeto, Tree arbol)
	{
		Diccionario dic=new Diccionario(alfabeto.length());
		char letra;
		Boolean[] code;

		for (int i=0; i<alfabeto.length(); i++)
		{
			letra=alfabeto.charAt(i);
			code=arbol.searchCode(letra);
			dic.setEntradaDeDiccionario(letra,code);
		}

		return dic;
	}
}
