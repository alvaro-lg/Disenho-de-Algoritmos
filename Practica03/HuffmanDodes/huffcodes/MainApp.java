package huffcodes;

import huffcodes.Compresor;
import huffcodes.Diccionario;
import huffcodes.Tree;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * La clase MainApp lanza la aplicación.
 * 
 */
public class MainApp
{
	/**
	 * Método que crea la interfaz gráfica del árrbol.
	 * 
	 * @param tree arbol a partir del que crear al interfaz grafica.
	 * @return     Ventana con la interfaz grafica del arbol.
	 */
	private static JFrame createTreeGUI(Tree tree)
	{
		// Ventana a retornar.
		JFrame frame=new JFrame("arbol de Huffman");

		// Dimensi�n de la ventana.
		Dimension size=Toolkit.getDefaultToolkit().getScreenSize();
		size=new Dimension(
				(int)(size.getWidth()*0.9),
				(int)(size.getHeight()*0.9));

		// Se a�ade el �rbol.
		frame.getContentPane().add(
				new huffcodes.JTreePanel(
						tree,
						size),
						BorderLayout.CENTER);

		frame.pack();

		// Opciones finales de la ventana.
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(size);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		frame.setIconImage(new ImageIcon(
				MainApp.class.getResource("/images/Arbol.png")).getImage());

		return frame;
	}
	
	/**
	 * Metodo que crea un cuadro de diaogo con un area de texto.
	 * 
	 * @param owner Ventana principal de la aplicacion.
	 * @param text  Texto a insertar en el area de texto.
	 * @param title Titulo del cuadro de diaogo.
	 * @return      La ventana con el area de texto.
	 */
	private static JDialog createTextArea(
			JFrame owner, String text, String title)
	{
		// Di�logo a retornar.
		JDialog dialog=new JDialog(owner,text,true);
		dialog.setTitle(title);
		dialog.setResizable(false);
		
		// Se inserta el �rea de texto.
		JTextArea textArea=new JTextArea(text);
		textArea.setEditable(false);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		
		JScrollPane areaScrollPane = new JScrollPane(textArea);
		areaScrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		areaScrollPane.setPreferredSize(new Dimension(800,600));
		
		dialog.add(areaScrollPane);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		
		return dialog;
	}
	
	/**
	 * M�todo que crea los botones de la interfaz gr�fica de
	 * usuario.
	 * 
	 * @param owner Ventana principal de la aplicaci�n.
	 * @param tree  �rbol a partir del que crear al interfaz gr�fica.
	 * @param dicc  Diccionario.
	 * @param texto Texto sin comprimir.
	 * @param compr Texto comprimido.
	 * @return     Los botones de la interfaz gr�fica de usuario.
	 */
	private static JButton[] createButtons(
			final JFrame owner,
			final Tree tree, final Diccionario dicc,
			final String texto, final Vector<Boolean> compr)
	{
		// BOT�N: �RBOL DE HUFFMAN.
		JButton bArbol=new JButton("�rbol de Huffman");
		bArbol.setIcon(new ImageIcon(
				MainApp.class.getResource("/images/Arbol.png")));
		bArbol.setHorizontalTextPosition(SwingConstants.CENTER);
		bArbol.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		bArbol.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				createTreeGUI(tree).setVisible(true);
			}
		});
		
		// BOT�N: ALFABETO.
		JButton bAlfabeto=new JButton("Alfabeto");
		bAlfabeto.setIcon(new ImageIcon(
				MainApp.class.getResource("/images/Alfabeto.png")));
		bAlfabeto.setHorizontalTextPosition(SwingConstants.CENTER);
		bAlfabeto.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		bAlfabeto.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				String text=dicc.toString();
				createTextArea(owner,text,"Alfabeto").setVisible(true);
			}
		});
		
		// BOT�N: COMPARATIVA.
		JButton bComparativa=new JButton("Comparativa de compresi�n");
		bComparativa.setIcon(new ImageIcon(
				MainApp.class.getResource("/images/Comparativa.png")));
		bComparativa.setHorizontalTextPosition(SwingConstants.CENTER);
		bComparativa.setVerticalTextPosition(SwingConstants.BOTTOM);
		
		bComparativa.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				// Texto completo.
				String text="";
				
				// Se a�ade el texto sin compresi�n de Huffman.
				text="TEXTO ORIGINAL CODIFICADO:\n";
				String textoOriginalCod=
					Compresor.codificacionTextoEnBinario(texto);
				
				text+="\n"+textoOriginalCod;
				text+="\n\nTAMA�O: "+textoOriginalCod.length();
				
				// Se a�ade el texto con compresi�n de Huffman.
				text+="\n\n\nTEXTO COMPRIMIDO CODIFICADO:\n\n";

				for (int i=0; i<compr.size(); i++)
					text+=(compr.get(i) ? "1" : "0");
				
				text+="\n\nTAMA�O: "+compr.size();

				// Se a�ade el ratio de compresi�n.
				double ratio=100.0-compr.size()*100.0/textoOriginalCod.length();
				text+=String.format("\n\n\nRATIO DE COMPRESI�N: %6.2f",ratio);
				text+="%";
				
				createTextArea(owner,text,"Comparativa").setVisible(true);
			}
		});
		
		return new JButton[] {
				bArbol, bAlfabeto, bComparativa
		};
	}

	/**
	 * M�todo que crea la interfaz gr�fica de la aplicaci�n..
	 * 
	 * @param tree �rbol a partir del que crear al interfaz gr�fica.
	 * @param dicc Diccionario.
	 * @param text Texto sin comprimir.
	 * @param comp Texto comprimido.
	 * @return     Ventana con la interfaz gr�fica de la aplicaci�n.
	 */
	private static JFrame createGUI(
			Tree tree, Diccionario dicc, String text, Vector<Boolean> comp)
	{
		// Ventana a retornar.
		JFrame frame=new JFrame("C�digos de Huffman");

		// Paneles de opciones.
		JPanel panel=new JPanel(new GridLayout(1,0));
		JPanel flowP;

		// Se a�aden los botones.
		JButton[] botones=createButtons(frame,tree,dicc,text,comp);
		
		for (JButton b : botones)
		{
			flowP=new JPanel(new FlowLayout());
			flowP.add(b);
			panel.add(flowP);
		}
		
		// Opciones finales de la ventana.
		frame.add(panel,BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		frame.setIconImage(new ImageIcon(
				MainApp.class.getResource("/images/Icon.png")).getImage());
		
		return frame;
	}

	/**
	 * M�todo principal que lanza la aplicaci�n.
	 * 
	 * @param args Par�metros de cabecera de la aplicaci�n.
	 */
	public static void main(String[] args)
	{
		// Se establece el Look & Feel.
		try {
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Se pide el texto al usuario.
		String texto=JOptionPane.showInputDialog(
				null,
				"Introduzca el texto a codificar:",
				"C�digos de Huffman",
				JOptionPane.QUESTION_MESSAGE);
		
		if (texto==null)
			System.exit(0);
		
		// Se obtienen las frecuencias de las letras.
		Vector<Tree> vector=
			Compresor.obtenerComponentes(texto);

		vector=Compresor.ordenarMenorAMayorComponentes(vector);
		Tree huffman=Compresor.crearArbolDeCodigos(vector);

		// Se construye el diccionario.
		Diccionario dicc=
			Compresor.construirDiccionarioDesdeArbol(
					Compresor.obtenerAlfabeto(texto),
					huffman);

		// Se comprime el texto.
		Vector<Boolean> compr=
			Compresor.comprime(texto,dicc);

		// Se muestra toda la informaci�n.
		JFrame mainGUI=createGUI(huffman,dicc,texto,compr);
		mainGUI.setVisible(true);
	}
}
