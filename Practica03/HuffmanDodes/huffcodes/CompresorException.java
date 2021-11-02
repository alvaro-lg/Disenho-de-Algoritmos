package huffcodes;

/**
 * La clase <code>CompresorException</code> representa la
 * excepción asociada a la clase <code>Compresor</code>.
 * 
 * @author Inés González y José Luis Montaña (original)
 * @author Camilo Palazuelos y Noelia Ruiz (adaptación)
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
	 * @param text Texto a mostrar por la excepción.
	 */
	public CompresorException(String text)
	{
		super(text);
	}
}
