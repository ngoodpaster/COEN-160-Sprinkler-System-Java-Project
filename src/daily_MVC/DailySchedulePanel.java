package daily_MVC;

import java.awt.*;
import java.awt.event.*;


//org.jdesktop.swingx.JXDatePicker;
import javax.swing.*;


public class DailySchedulePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton updateButton;
	MultiLabelCheckPanel northRegion, southRegion, eastRegion, westRegion, minTemp, maxTemp;


	public DailySchedulePanel() {
		// BoxLayout with Y-AXIS specification allows us to layout components
		// from top to bottom
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		northRegion = new MultiLabelCheckPanel("North Region", "Start", "End" , "Flowrate");
		southRegion = new MultiLabelCheckPanel("South Region", "Start", "End", "Flowrate");
		eastRegion = new MultiLabelCheckPanel("East Region", "Start", "End", "Flowrate");
		westRegion = new MultiLabelCheckPanel("West Region", "Start", "End", "Flowrate");

		minTemp = new MultiLabelCheckPanel("Min Temp Override", "Min Temp");
		maxTemp = new MultiLabelCheckPanel("Max Temp Override", "Max Temp");

		updateButton = new JButton("Update");
		updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);


		// With Box Layout add components in desired top to bottom order
		this.add(northRegion);
		this.add(southRegion);
		this.add(eastRegion);
		this.add(westRegion);
		this.add(new JSeparator());
		this.add(minTemp);
		this.add(maxTemp);
		this.add(new JSeparator());
		this.add(updateButton);
		this.setVisible(true);

	}
	

	void addUpdateListener(ActionListener listenForUpdateButton) {
		updateButton.addActionListener(listenForUpdateButton);
	}

	void displayErrorMessage(String errorMessage) {
		JOptionPane.showMessageDialog(this, errorMessage);
	}
	
	

}
