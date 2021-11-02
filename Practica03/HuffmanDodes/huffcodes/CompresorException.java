package huffcodes;

/**
 * La clase <code>CompresorException</code> representa la
 * excepci�n asociada a la clase <code>Compresor</code>.
 * 
 * @author In�s Gonz�lez y Jos� Luis Monta�a (original)
 * @author Camilo Palazuelos y Noelia Ruiz (adaptaci�n)
 * @version 10 de junio de 2010
 */
public class CompresorException extends Exception
{
	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de objetos de la clase.
	 * 
	 * @param text Texto a mostrar por la excepci�n.
	 */
	public CompresorException(String text)
	{
		super(text);
	}
}
