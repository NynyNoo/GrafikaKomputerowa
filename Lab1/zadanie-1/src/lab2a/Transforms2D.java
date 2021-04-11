



package lab2a;


import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.Toolkit;
public class Transforms2D extends JPanel {
	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // wyœrodkowanie.
			int whichTransform = transformSelect.getSelectedIndex();
			Stroke stroke = new BasicStroke(2.0f, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER);
			g2.setStroke(stroke);
			int kat = 15;
			Point[] points = new Point[kat];			
			double alpha = (2 * Math.PI) / kat,phiK = 0;
			int radius = 150;
			for(int k = 0; k < kat; k++)
			{
				points[k] = new Point();
				phiK = k * alpha;
				points[k].x =(int)(radius * Math.cos(phiK));
				points[k].y = (int)(radius * Math.sin(phiK));
			}		
			GeneralPath polygon = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.length);
			polygon.moveTo(points[0].x, points[0].y);
			for(int i = 1; i < points.length; i++)
			{
				polygon.lineTo(points[i].x, points[i].y);
			}
			polygon.closePath();
			float scaleXBy = 1,scaleYBy = 1, shearXBy = 0,shearYBy = 0;	
			double obrot;
			switch (whichTransform)
			{
				default: 
				{
					g2.draw(polygon);
					break;
				}
				case 1:
				{
					scaleXBy = 0.5f;
					scaleYBy = 0.5f;
					g2.scale(scaleXBy, scaleYBy);
					g2.draw(polygon);
					break;
				}
					
				case 2:
				{
					obrot = 45;
					g2.rotate(Math.toRadians(obrot));
					g2.draw(polygon);
					break;
				}
				
				case 3:
				{
					obrot = 180;
					g2.rotate(Math.toRadians(obrot));
					scaleXBy = -0.5f;
					g2.scale(scaleXBy, scaleYBy);
					g2.draw(polygon);
					break;
				}
				
				case 4:
				{
					shearXBy = 0.3f;
					g2.shear(shearXBy, shearYBy);
					g2.draw(polygon);
					break;
				}
				
				case 5:
				{
					g2.translate(0,-225);
					scaleYBy = 0.5f;
					g2.scale(scaleXBy, scaleYBy);
					g2.draw(polygon);
					break;
				}
				
				case 6:
				{
					obrot = 90;
					g2.rotate(Math.toRadians(obrot));
					shearXBy = 0.3f;
					g2.shear(shearXBy, shearYBy);
					g2.draw(polygon);
					break;
				}
				
				case 7:
				{
					obrot = 180;
					g2.rotate(Math.toRadians(obrot));
					scaleXBy = 0.5f;
					g2.scale(scaleXBy, scaleYBy);
					g2.draw(polygon);
					break;
				}
				
				case 8:
				{
					obrot = 35;
					g2.rotate(Math.toRadians(obrot), -150, 0);
					scaleYBy = 0.5f;
					g2.scale(scaleXBy, scaleYBy);
					g2.draw(polygon);
					break;
				}
				
				case 9:
				{
					g2.translate(150, 0);
					obrot = 180;
					g2.rotate(Math.toRadians(obrot));
					shearYBy = 0.3f;
					g2.shear(shearXBy, shearYBy);
					g2.draw(polygon);
					break;
				}	
			}		
			


			g2.drawImage(pic, -200, -150, null);
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}