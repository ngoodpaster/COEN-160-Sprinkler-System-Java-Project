package environment_MVC;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

/*
 * The EnvironmentPanel will be the VIEW for the Environment MVC. It will have it's own view 
 * and it's own controller. The controller will handle the buttons for when the user adjusts
 * the temperature as well as for when the user enables or disables the system. 
 * 
 * Should be an Observer of the environment model as well, because it will change it's days on the calendar depending
 * on where the time is at.
 * 
 */

public class EnvironmentPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton increaseButton, decreaseButton, systemEnableButton, systemDisableButton;
	JLabel temperatureLabel, sunny, partlycloudy, cloudy, rainy, snowy, dayTimeLabel, dayLabel, hourLabel, hourTimeLabel , weekLabel , weekNumLabel;
	JPanel adjustTempPanel, selectorPanel, eastSelectorPanel, systemPanel, weatherIconPanel, timePanel;
	int initTemperatureValue;
	EnvironmentModel environmentModel;

	Rectangle calendar1;

	public EnvironmentPanel(EnvironmentModel environmentModel) {
		super();
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new BorderLayout());

		this.environmentModel = environmentModel;
		
		// load weather image files to JLabels
		sunny = new JLabel(addImage("../resources/sun.png", 50, 50));
		partlycloudy = new JLabel(addImage("../resources/partlycloudy.png", 60, 60));
		cloudy = new JLabel(addImage("../resources/cloudy.png", 40, 40));
		rainy = new JLabel(addImage("../resources/rain.png", 40, 40));
		snowy = new JLabel(addImage("../resources/snow.png", 40, 40));

		// Making an adjustTemperature Panel and adding the label and buttons to
		// it
		adjustTempPanel = new JPanel();
		adjustTempPanel.setLayout(new BorderLayout());
		adjustTempPanel.setBackground(Color.black);
		adjustTempPanel.setPreferredSize(new Dimension(150, 100));

		increaseButton = new JButton("+");
		increaseButton.setFont(new Font("Impact", Font.BOLD, 24));
		increaseButton.setPreferredSize(new Dimension(20, 20));

		decreaseButton = new JButton("-");
		decreaseButton.setFont(new Font("Impact", Font.BOLD, 24));
		decreaseButton.setPreferredSize(new Dimension(20, 20));

		temperatureLabel = new JLabel(environmentModel.temperature + "F");
		temperatureLabel.setFont(new Font("Georgia", Font.BOLD, 24));
		temperatureLabel.setForeground(Color.white);

		// makes a separate panel for increase and decrease buttons
		eastSelectorPanel = new JPanel();
		eastSelectorPanel.setPreferredSize(new Dimension(30, 70));
		eastSelectorPanel.setLayout(new FlowLayout());
		eastSelectorPanel.setBackground(Color.black);
		eastSelectorPanel.add(increaseButton);
		eastSelectorPanel.add(decreaseButton);

		// SelectorPanel holds the temperature label and the eastSelector(which
		// contains the +/-)
		selectorPanel = new JPanel();
		selectorPanel.setBackground(Color.black);
		selectorPanel.setLayout(new FlowLayout());
		selectorPanel.add(temperatureLabel);
		selectorPanel.add(eastSelectorPanel);

		// weatherIconPanel will hold the weather icon
		weatherIconPanel = new JPanel();
		weatherIconPanel.setLayout(new BorderLayout());
		weatherIconPanel.setBackground(Color.black);
		weatherIconPanel.setPreferredSize(new Dimension(60, 70));
		weatherIconPanel.add(partlycloudy, BorderLayout.NORTH);

		adjustTempPanel.add(weatherIconPanel, BorderLayout.WEST);
		adjustTempPanel.add(selectorPanel, BorderLayout.EAST);

		// create a Panel to hold "Start" and "Stop" Buttons for the System
		systemPanel = new JPanel();
		systemPanel.setLayout(new FlowLayout());
		systemPanel.setBackground(Color.black);
		systemPanel.setPreferredSize(new Dimension(180, 100));

		systemEnableButton = new JButton("Start");
		systemEnableButton.setFont(new Font("Impact", Font.PLAIN, 24));
		systemEnableButton.setPreferredSize(new Dimension(80, 50));

		systemDisableButton = new JButton("Stop");
		systemDisableButton.setFont(new Font("Impact", Font.PLAIN, 24));
		systemDisableButton.setPreferredSize(new Dimension(80, 50));

		systemPanel.add(systemEnableButton);
		systemPanel.add(systemDisableButton);
		
		timePanel = new JPanel();
		timePanel.setLayout(new FlowLayout());
		timePanel.setBackground(Color.black);
		
		weekLabel = new JLabel("Week: ");
		weekLabel.setForeground(Color.WHITE);
		weekLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		weekNumLabel = new JLabel(environmentModel.weeks.toString());
		weekNumLabel.setForeground(Color.WHITE);
		weekNumLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		dayLabel = new JLabel("Day:");
		dayLabel.setForeground(Color.WHITE);
		dayLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		dayTimeLabel = new JLabel(environmentModel.days.toString());
		dayTimeLabel.setForeground(Color.WHITE);
		dayTimeLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		hourLabel = new JLabel(" Hour: ");
		hourLabel.setForeground(Color.WHITE);
		hourLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		hourTimeLabel = new JLabel(environmentModel.getHours().toString());
		hourTimeLabel.setForeground(Color.WHITE);
		hourTimeLabel.setFont(new Font("Georgia", Font.BOLD, 18));
		
		timePanel.add(dayLabel);
		timePanel.add(dayTimeLabel);
		timePanel.add(hourLabel);
		timePanel.add(hourTimeLabel);
		timePanel.add(weekLabel);
		timePanel.add(weekNumLabel);
		
		this.add(timePanel,BorderLayout.CENTER);
		this.add(systemPanel, BorderLayout.WEST);
		this.add(adjustTempPanel, BorderLayout.EAST);
		this.setVisible(true);
	}

	public ImageIcon addImage(String imgURL, int w, int h) {
		ImageIcon image = new ImageIcon(getClass().getResource(imgURL));
		Image scaledImage = image.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		image = new ImageIcon(scaledImage);
		return image;
	}

	void addIncreaseTempListener(ActionListener listenForIncreaseButton) {
		increaseButton.addActionListener(listenForIncreaseButton);
	}

	void addDecreaseTempListener(ActionListener listenForDecreaseButton) {
		decreaseButton.addActionListener(listenForDecreaseButton);
	}

	void addEnableSystemListener(ActionListener listenForEnableButton) {
		systemEnableButton.addActionListener(listenForEnableButton);
	}

	void addDisableSystemListener(ActionListener listenForDisableButton) {
		systemDisableButton.addActionListener(listenForDisableButton);
	}
}
