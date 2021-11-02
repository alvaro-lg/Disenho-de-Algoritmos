package huffcodes;

import java.util.Vector;

/**
 * La clase <code>Tree</code> implementa la estructura de datos
 * <i>arbol binario</i>, en que puede almacenarse una letra, su
 * frecuencia, y sus hijos izquierdo y derecho.
 */
public class Tree
{
	/** Letra. */
	private char data;
	
	/** Frecuencia de aparicion. */
	private int key;
	
	/** Hijo izquierdo. */
	private Tree left;
	
	/** Hijo derecho. */
	private Tree right;

	// M�todos observadores.
	public char getData() { return data; }
	public int getKey() { return key; }
	public void setData(char data) { this.data = data; }
	public void setKey(int key) { this.key = key; }

	/**
	 * Constructor de objetos de la clase.
	 * 
	 * @param key  Frecuencia de aparicion del elemento.
	 * @param elem Letra.
	 */
	public Tree(int key, char elem)
	{
		this.key=key;
		this.data=elem;
	}

	/**
	 * Constructor de objetos de la clase.
	 * 
	 * @param key   Frecuencia de aparicion del elemento.
	 * @param elem  Letra.
	 * @param left  Hijo izquierdo.
	 * @param right Hijo derecho.
	 */
	public Tree(int key, char elem, Tree left, Tree right)
	{
		this.key=key;
		this.data=elem;
		this.left=left;
		this.right=right;
	}

	/**
	 * Metodo que obtiene el codigo de la letra en un array de
	 * booleanos.
	 * 
	 * @param letra Letra de la que obtener su codigo.
	 * @return      El codigo de la letra.
	 */
	public Boolean[] searchCode(char letra)
	{
		Vector<Boolean> resAux=
			searchCodeAux(letra,new Vector<Boolean>());
		Boolean[] res=new Boolean[resAux.size()];

		for (int i=0; i<res.length; i++)
			res[i]=resAux.elementAt(i);

		return res;
	}

	/**
	 * Metodo que obtiene recursivamente el codigo de una letra.
	 * 
	 * @param letra Letra de la que obtener su codigo.
	 * @param code  Codigo parcial de la letra.
	 * @return      El codigo de la letra.
	 */
	private Vector<Boolean> searchCodeAux(char letra, Vector<Boolean> code)
	{
		// Se crea la frecuencia que se va a devolver.
		Vector<Boolean> resRight=null;
		Vector<Boolean> resLeft=null;

		if (letra==data)
			return code;

		if (left!=null)
		{
			Vector<Boolean> codeLeft=new Vector<Boolean>(code);
			codeLeft.addElement(new Boolean(false));
			resLeft=left.searchCodeAux(letra,codeLeft);
			
			if (resLeft!=null)
				return resLeft;
		}
		
		if (right!=null)
		{
			Vector<Boolean> codeRight=new Vector<Boolean>(code);
			codeRight.addElement(new Boolean(true));
			resRight=right.searchCodeAux(letra,codeRight);
			
			if (resRight!=null)
				return resRight;
		}

		return null;
	}

	/**
	 * Metodo que escribe la representacion grafica de caracteres
	 * y frecuencias.
	 * 
	 * @param a Numero del que obtener su representacion grafica.
	 * @return  La representacion graica de caracteres y frecuencias.
	 */
	public String toStringp(int a)
	{
		String res="";
		String p="";

		if (left!=null)
			res+=left.toStringp(a-4);

		for (int i=0; i<a; i++)
			p=p+" ";
		
		res+=p+"["+key+"] "+data+"\r\n";

		if (right!=null)
			res+=right.toStringp(a-4);
		
		return res;
	}

	/**
	 * Metodo que muestra por pantalla las claves en inorden.
	 */
	public void print()
	{
		if (left!=null)
			left.print();
		
		System.out.println("["+key+"] "+data);
		
		if (right!=null)
			right.print();
	}

	/**
	 * Metodo que muestra por pantalla las claves en preorden.
	 */
	public void printPreOrder()
	{
		System.out.println("["+key+"] "+data);
		
		if (left!=null)
			left.printPreOrder();
		
		if (right!=null)
			right.printPreOrder();
	}

	/**
	 * Metodo que muestra por pantalla las claves en postorden.
	 */
	public void printPostOrder()
	{
		if (left!=null)
			left.printPostOrder();
		
		if (right!=null)
			right.printPostOrder();
		
		System.out.println("["+key+"] "+data);
	}

	/**
	 * Metodo que indica el tamaño del arbol.
	 * 
	 * @return El tamaño del arbol.
	 */
	public int size()
	{
		int size=1;
		
		if (left!=null)
			size+=left.size();
		
		if (right!=null)
			size+=right.size();
		
		return size;
	}

	/**
	 * Metodo que indica la altura del arbol.
	 * 
	 * @return La altura del arbol.
	 */
	public int height()
	{
		int heightLeft=-1;
		int heightRight=-1;
		
		if (left!=null)
			heightLeft=left.height();
		
		if (right!=null)
			heightRight=right.height();
		
		return 1+(heightLeft>heightRight ? heightLeft : heightRight);
	}
	
	/**
	 * Metodo que devuelve el hijo izquierdo del nodo
	 * actual.
	 * 
	 * @return El hijo izquierdo del nodo actual.
	 */
	public Tree getHijoIzquierdo()
	{
		return left;
	}
	
	/**
	 * Metodo que devuelve el hijo derecho del nodo
	 * actual.
	 * 
	 * @return El hijo derecho del nodo actual.
	 */
	public Tree getHijoDerecho()
	{
		return right;
	}
}
