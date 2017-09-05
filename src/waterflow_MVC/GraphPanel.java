package waterflow_MVC;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class GraphPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<Integer> weeklyConsumption;
	int totalWaterConsumption;
	int weeks;

	public GraphPanel(ArrayList<Integer> weeklyConsumption, int totalWaterConsumption) {
		this.weeklyConsumption = weeklyConsumption;
		this.totalWaterConsumption = totalWaterConsumption;
		this.weeks = weeklyConsumption.size();
		
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(250, 480));
	}

	public void paintComponent(Graphics gp) {
		super.paintComponent(gp);
		Graphics2D g = (Graphics2D) gp;
		drawGraph(g);
	}

	public void drawGraph(Graphics2D g) {
		int startX = 85, startY = 170;
		g.setStroke(new BasicStroke(3));

		Font newFont = new Font("Verdana", Font.ITALIC, 11);
		g.setFont(newFont);
		g.drawString("Water", startX - 80, startY - 90);
		g.drawString("(gals)", startX - 80, startY - 70);
		g.drawString("Time (weeks)", startX + 130, startY + 40);
		if (weeklyConsumption.size() != 0) {
			g.setPaint(Color.red);
			int width = 380 / weeklyConsumption.size();
			int gap = width / 10;
			int max = Collections.max(weeklyConsumption);
			int top = (((max / 10) * 10) + 10);
			for (int i = 0; i < weeklyConsumption.size(); i++) {
				double height = (((double) weeklyConsumption.get(i) / ((double) top)) * 160);
				g.setPaint(Color.red);
				Rectangle2D.Double rectangle = new Rectangle2D.Double(startX + (i * width) + gap, startY - height,
						width - (2 * gap), height);
				g.draw(rectangle);
				g.setPaint(Color.black);
				g.drawString(((Integer) (i + 1)).toString(), startX + (width / 2) + (i * width), startY + 15);
			}
			for (int i = 0; i < 4; i++) {
				int value = (max / 4) * (i + 1);
				g.drawString(((Integer) value).toString(), startX - 35, startY - (40 * (i + 1)) + 2);
			}
			g.setFont(new Font("Verdana", Font.BOLD, 12));
			g.drawString("Total Water:", startX - 80, startY + 28);
			g.drawString(((Integer) (this.totalWaterConsumption)).toString() + " gals", startX - 80, startY + 40);

		}
		
		g.setPaint(Color.black);
		Line2D yaxis = new Line2D.Double(startX, startY, startX, startY - 160);
		Line2D xaxis = new Line2D.Double(startX, startY, startX + 380, startY);
		g.draw(yaxis);
		g.draw(xaxis);
	}
}
