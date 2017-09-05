package system_MVC;

import java.awt.*;
import javax.swing.*;

import daily_MVC.DailySchedulePanel;
import display_MVC.DisplayPanel;
import environment_MVC.EnvironmentPanel;
import waterflow_MVC.WaterFlowView;

public class SystemWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Views
	DisplayPanel displayPanel;
	WaterFlowView waterFlowView;
	EnvironmentPanel environmentView;
	DailySchedulePanel dailyScheduleView;
	
	JTabbedPane scheduleSelector;
	JPanel westPanel, eastPanel, northWestPanel, configurationButtonPanel, titlePanel;
	JLabel title;
	
	SprinklerSystem systemModel;
	
	ImageIcon bee;

	public SystemWindow(SprinklerSystem systemModel) {
		super("Sprinkler System Simulator");
		setPreferredSize(new Dimension(1000, 600));
		setResizable(false);
		
		this.systemModel = systemModel;
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());

		bee = new ImageIcon(getClass().getResource("../resources/bee.png"));
		Image image = bee.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH); // transform
		bee = new ImageIcon(image); // transform it back

		title = new JLabel("HummingBee Home Garden Sprinkler System");
		title.setFont(new Font("Georgia", Font.PLAIN, 20));
		title.setIcon(bee);

		titlePanel = new JPanel();
		titlePanel.setLayout(new FlowLayout());
		titlePanel.setPreferredSize(new Dimension(500, 40));
		titlePanel.setBackground(Color.ORANGE);
		titlePanel.add(title);

		westPanel = new JPanel();
		westPanel.setPreferredSize(new Dimension(500, 600));

		eastPanel = new JPanel();
		eastPanel.setPreferredSize(new Dimension(500, 600));

		dailyScheduleView = new DailySchedulePanel();
		
		scheduleSelector = new JTabbedPane();
		scheduleSelector.addTab("Daily Schedule", dailyScheduleView);
		scheduleSelector.setPreferredSize(new Dimension(450, 290));

		westPanel.add(titlePanel, BorderLayout.NORTH);
		westPanel.add(scheduleSelector, BorderLayout.CENTER);

		waterFlowView = new WaterFlowView(systemModel.waterFlowModel.weeklyConsumption , systemModel.waterFlowModel.getTotalWaterConsumption());
		waterFlowView.setPreferredSize(new Dimension(500, 270));
		westPanel.add(waterFlowView, BorderLayout.SOUTH);
		
		displayPanel = new DisplayPanel(systemModel);
		displayPanel.setPreferredSize(new Dimension(500, 510));
		
		System.out.println();
		
		eastPanel.add(displayPanel, BorderLayout.NORTH);

		environmentView = new EnvironmentPanel(this.systemModel.environmentModel);
		environmentView.setPreferredSize(new Dimension(500, 90));
		eastPanel.add(environmentView, BorderLayout.SOUTH);

		((FlowLayout) westPanel.getLayout()).setVgap(0);
		((FlowLayout) eastPanel.getLayout()).setVgap(0);
		container.add(eastPanel, BorderLayout.EAST);
		container.add(westPanel, BorderLayout.WEST);

		
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("SprinklerSystemSimulator");
		setVisible(true);
	}

}
