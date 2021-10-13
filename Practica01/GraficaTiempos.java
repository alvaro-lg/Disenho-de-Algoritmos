package Practica01;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraficaTiempos extends JPanel {

	private int width = 800;
	private int heigth = 400;
	private int padding = 25;
	private int labelPadding = 25;
	private Color lineColor = new Color(44, 102, 230, 180);
	private Color pointColor = new Color(100, 100, 100, 180);
	private Color gridColor = new Color(200, 200, 200, 200);
	private Color auxLinesColor = new Color(150, 0, 0);

	private static final Stroke GRAPH_STROKE = new BasicStroke(2f);
	private int pointWidth = 4;
	private int numberYDivisions = 10;
	private List<Linea> lineas = new ArrayList<Linea>();

	public GraficaTiempos(List<Integer> scores) {
		this.lineas.add(new Linea(scores));
	}

	public GraficaTiempos() { }

	public void addList(List<Integer> scores) {
		this.lineas.add(new Linea(scores));
	}

	@Override
	protected void paintComponent(Graphics g) {;

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		for (Linea l : lineas) {

			double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (l.size() - 1);
			double yScale = ((double) getHeight() - 2 * padding - labelPadding) / (getMaxScore() - getMinScore());

			List<Point> graphPoints = new ArrayList<>();
			for (int i = 0; i < l.size(); i++) {
				int x1 = (int) (i * xScale + padding + labelPadding);
				int y1 = (int) ((getMaxScore() - l.get(i)) * yScale + padding);
				graphPoints.add(new Point(x1, y1));
			}

			// create hatch marks and grid lines for y axis.
			for (int i = 0; i < numberYDivisions + 1; i++) {
				int x0 = padding + labelPadding;
				int x1 = pointWidth + padding + labelPadding;
				int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding
						+ labelPadding);
				int y1 = y0;
				if (l.size() > 0) {
					g2.setColor(gridColor);
					g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
					g2.setColor(Color.BLACK);
					String yLabel = (int) ((int) ((getMinScore()
							+ (getMaxScore() - getMinScore()) * ((i * 1.0) / numberYDivisions)) * 100)) / 100 + "";
					FontMetrics metrics = g2.getFontMetrics();
					int labelWidth = metrics.stringWidth(yLabel);
					g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
				}
				g2.drawLine(x0, y0, x1, y1);
			}

			// and for x axis
			for (int i = 0; i < l.size(); i++) {
				if (l.size() > 1) {
					int x0 = i * (getWidth() - padding * 2 - labelPadding) / (l.size() - 1) + padding
							+ labelPadding;
					int x1 = x0;
					int y0 = getHeight() - padding - labelPadding;
					int y1 = y0 - pointWidth;
					if ((i % ((int) ((l.size() / 20.0)) + 1)) == 0) {
						g2.setColor(gridColor);
						g2.drawLine(x0, getHeight() - padding - labelPadding - 1 - pointWidth, x1, padding);
						g2.setColor(Color.BLACK);
						String xLabel = i + "";
						FontMetrics metrics = g2.getFontMetrics();
						int labelWidth = metrics.stringWidth(xLabel);
						g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
					}
					g2.drawLine(x0, y0, x1, y1);
				}
			}

			// create x and y axes
			g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
			g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding,
					getHeight() - padding - labelPadding);

			Stroke oldStroke = g2.getStroke();
			if (l instanceof LineaAuxiliar) {
				g2.setColor(auxLinesColor);
			} else {
				g2.setColor(lineColor);
			}
			g2.setStroke(GRAPH_STROKE);
			for (int i = 0; i < graphPoints.size() - 1; i++) {
				int x1 = graphPoints.get(i).x;
				int y1 = graphPoints.get(i).y;
				int x2 = graphPoints.get(i + 1).x;
				int y2 = graphPoints.get(i + 1).y;
				g2.drawLine(x1, y1, x2, y2);
			}

			g2.setStroke(oldStroke);
			g2.setColor(pointColor);
			for (int i = 0; i < graphPoints.size(); i++) {
				int x = graphPoints.get(i).x - pointWidth / 2;
				int y = graphPoints.get(i).y - pointWidth / 2;
				int ovalW = pointWidth;
				int ovalH = pointWidth;
				g2.fillOval(x, y, ovalW, ovalH);
			}
		}
	}

	private double getMinScore() {
		double minScore = Double.MAX_VALUE;
		for (Linea l : lineas) {
			for (Integer score : l.getPuntos()) {
				minScore = Math.min(minScore, score);
			}
		}
		return minScore;
	}

	private double getMaxScore() {
		double maxScore = Double.MIN_VALUE;
		for (Linea l : lineas) {
			for (Integer score : l.getPuntos()) {
				maxScore = Math.max(maxScore, score);
			}
		}

		return maxScore;
	}

	public void setScores(List<Integer> scores) {
		this.lineas.clear();
		this.lineas.add(new Linea(scores));

		invalidate();
		this.repaint();
	}

	public void createAndShowGui() {

		this.setPreferredSize(new Dimension(500, 300));
		// frame global
		JFrame frame = new JFrame("Grafica de tiempos medios");
		// a�adir paneles al frame
		frame.add(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public void pintanpow2(int k, int maxX) {

		List<Integer> puntos = new ArrayList<Integer>();

		for (int x = 0; x < maxX; x++) {
			puntos.add((int) (k * Math.pow(x, 2)));
		}

		System.out.println(puntos);

		this.lineas.add(new LineaAuxiliar(puntos));
	}

	public void pintanlogn(int k, int maxX) {
		List<Integer> puntos = new ArrayList<Integer>();

		for (int x = 0; x < maxX; x++) {
			puntos.add((int) (k * x * Math.log(x)));
		}

		System.out.println(puntos);

		this.lineas.add(new LineaAuxiliar(puntos));
	}

	private class Linea {

		private List<Integer> puntos;

		public Linea (List<Integer> puntos) {
			this.puntos = puntos;
		}

		public int get(int pos) {
			return this.puntos.get(pos);
		}

		public int size() {
			return this.puntos.size();
		}

		public void set(int pos, int value) {
			this.puntos.set(pos,value);
		}

		// Getters and Setters

		public List<Integer> getPuntos() {
			return puntos;
		}

		public void setPuntos(List<Integer> puntos) {
			this.puntos = puntos;
		}
	}

	private class LineaAuxiliar extends Linea {

		public LineaAuxiliar(List<Integer> puntos) {
			super(puntos);
		}
	}
}