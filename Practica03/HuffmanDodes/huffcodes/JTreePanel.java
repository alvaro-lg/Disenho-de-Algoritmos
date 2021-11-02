package huffcodes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import huffcodes.Tree;

import javax.swing.JPanel;

/**
 * La clase <code>JTreePanel</code> permite mostrar un arbol
 * binario graficamente.
 */
public class JTreePanel extends JPanel
{
	/**
	 * Serial Version Unique Identifier.
	 */
	private static final long serialVersionUID=
		5823780552243071612L;
	
	/** arbol binario a dibujar */
	private Tree arbolBin;
	
	/** Niveles del arbol. */
	private int niveles;
	
	/** Dimension de la ventana. */
	private Dimension dim;
	
	/**
	 * Constructor de objetos de la clase.
	 * 
	 * @param arbolBin arbol binario a dibujar.
	 * @param dim      Dimension del panel.
	 */
	public JTreePanel(Tree arbolBin, Dimension dim)
	{
		this.arbolBin=arbolBin;
		this.niveles=arbolBin.height()+1;
		this.dim=dim;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		// Down-cast a Graphics2D.
		Graphics2D g2=(Graphics2D)g;

		// Se establecen las opciones de renderizado.
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		// Elementos del arbol.
		int numElemsUltimaFila='z'-'a'+1;
		
		// Se dibuja el �rbol binario.
		g2.setColor(Color.BLACK);
		drawBinaryTree(
				g2,arbolBin,(int)dim.getWidth()/2,50,
				dim.getWidth()/numElemsUltimaFila*niveles);
	}
	
	/**
	 *  Metoodo que dibuja un arbol binario.
	 *  
	 * @param g2    Objeto que proporciona las operaciones de
	 *              dibujado.
	 * @param nodo  Nodo actual del arbol.
	 * @param x     Coordenada <i>X</i> actual.
	 * @param y     Coordenada <i>Y</i> actual.
	 * @param separ Separacion actual entre nodos.
	 */
	private void drawBinaryTree(
			Graphics2D g2, Tree nodo, int x, int y, double separ)
	{
		if (nodo!=null)
		{
			// Se trata el hijo izquierdo.
			if (nodo.getHijoIzquierdo()!=null)
			{
				int xPrima=x-(int)separ;
				int yPrima=y+(int)(dim.getHeight()-50)/niveles;
				
				g2.setColor(Color.BLACK);
				g2.drawLine(
						x,y,
						xPrima,yPrima);
				
				drawBinaryTree(
						g2,nodo.getHijoIzquierdo(),
						xPrima,yPrima,separ/2);
			}
			
			// Se trata el hijo derecho.
			if (nodo.getHijoDerecho()!=null)
			{
				int xPrima=x+(int)separ;
				int yPrima=y+(int)(dim.getHeight()-50)/niveles;
				
				g2.setColor(Color.BLACK);
				g2.drawLine(
						x,y,
						xPrima,yPrima);
				
				drawBinaryTree(
						g2,nodo.getHijoDerecho(),
						xPrima,yPrima,separ/2);
			}
			
			// Se dibuja el nodo actual.
			if (nodo.getData()=='\0')
			{
				g2.setColor(Color.RED);
				g2.fillOval(x-10,y-10,25,25);
				
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Monospaced",Font.BOLD,14));
				g2.drawString(
						String.valueOf(nodo.getKey()),
						nodo.getKey()>9 ? x-5 : x-1,y+5);
			} else {
				g2.setColor(Color.GREEN.darker());
				g2.fillRect(x-10,y-10,35,20);
				
				g2.setColor(Color.WHITE);
				g2.setFont(new Font("Monospaced",Font.BOLD,14));
				g2.drawString(
						String.valueOf(nodo.getData())+":"+
						String.valueOf(nodo.getKey()),
						nodo.getKey()>9 ? x-10 : x-5,y+5);
			}
		}
	}
}