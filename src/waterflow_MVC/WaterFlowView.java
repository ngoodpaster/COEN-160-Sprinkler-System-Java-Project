package waterflow_MVC;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class WaterFlowView extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GraphPanel graphPanel;
	JPanel graphTitlePanel;
	JLabel graphTitleLabel1, graphTitleLabel2;

	public WaterFlowView(ArrayList<Integer> weeklyConsumption , int totalWaterConsumption){
		this.setLayout(new BorderLayout());
		this.setBackground(Color.gray);
		
		graphTitlePanel = new JPanel(new FlowLayout());
		graphTitlePanel.setBackground(Color.gray);
		graphTitlePanel.setPreferredSize(new Dimension(500, 30));
		
		graphTitleLabel1 = new JLabel("Water Consumption Graph");
		graphTitleLabel1.setFont(new Font("Georgia", Font.BOLD, 16));
		graphTitleLabel1.setForeground(Color.white);
		graphTitleLabel2 = new JLabel("(Volume/Week)");
		graphTitleLabel2.setFont(new Font("Georgia", Font.PLAIN, 14));
		graphTitleLabel2.setForeground(Color.red);
		
		graphTitlePanel.add(graphTitleLabel1);
		graphTitlePanel.add(graphTitleLabel2);
		
		
		graphPanel = new GraphPanel(weeklyConsumption , totalWaterConsumption);
		this.add(graphTitlePanel, BorderLayout.NORTH);
		this.add(graphPanel, BorderLayout.CENTER);
	}
	
	public void swapGraph(GraphPanel graphPanel){
		this.removeAll();
		this.add(graphTitlePanel , BorderLayout.NORTH);
		this.add(graphPanel, BorderLayout.CENTER);
		this.repaint();
		this.revalidate();
	}
}
