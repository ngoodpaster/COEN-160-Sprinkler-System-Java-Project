package environment_MVC;

import java.awt.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import system_MVC.MyTimer;
import system_MVC.SprinklerSystem;

public class EnvironmentController implements Observer {
	private EnvironmentPanel environmentView;
	private EnvironmentModel environmentModel;
	SprinklerSystem systemModel;
	MyTimer myTimer;

	public EnvironmentController(EnvironmentPanel environmentView, EnvironmentModel environmentModel,
			SprinklerSystem systemModel, MyTimer myTimer) {
		this.environmentView = environmentView;
		this.environmentModel = environmentModel;
		this.systemModel = systemModel;
		this.myTimer = myTimer;

		this.environmentView.addIncreaseTempListener(new IncreaseTempListener());
		this.environmentView.addDecreaseTempListener(new DecreaseTempListener());
		this.environmentView.addEnableSystemListener(new EnableSystemListener());
		this.environmentView.addDisableSystemListener(new DisableSystemListener());
	}

	public void changeImage() {
		if (environmentModel.temperature > 79) {
			environmentView.weatherIconPanel.removeAll();
			environmentView.weatherIconPanel.add(environmentView.sunny, BorderLayout.NORTH);
			environmentView.weatherIconPanel.revalidate();
			environmentView.weatherIconPanel.repaint();
		} else if (environmentModel.temperature <= 79 && environmentModel.temperature > 59) {
			environmentView.weatherIconPanel.removeAll();
			environmentView.weatherIconPanel.add(environmentView.partlycloudy, BorderLayout.NORTH);
			environmentView.weatherIconPanel.revalidate();
			environmentView.weatherIconPanel.repaint();
		} else if (environmentModel.temperature <= 59 && environmentModel.temperature > 44) {
			environmentView.weatherIconPanel.removeAll();
			environmentView.weatherIconPanel.add(environmentView.cloudy, BorderLayout.NORTH);
			environmentView.weatherIconPanel.revalidate();
			environmentView.weatherIconPanel.repaint();
		} else if (environmentModel.temperature <= 44 && environmentModel.temperature > 31) {
			environmentView.weatherIconPanel.removeAll();
			environmentView.weatherIconPanel.add(environmentView.rainy, BorderLayout.NORTH);
			environmentView.weatherIconPanel.revalidate();
			environmentView.weatherIconPanel.repaint();
		} else if (environmentModel.temperature <= 31) {
			environmentView.weatherIconPanel.removeAll();
			environmentView.weatherIconPanel.add(environmentView.snowy, BorderLayout.NORTH);
			environmentView.weatherIconPanel.revalidate();
			environmentView.weatherIconPanel.repaint();
		}
	}

	class IncreaseTempListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e1) {
			environmentModel.increaseTemperature();
			environmentView.temperatureLabel.setText(Integer.toString(environmentModel.temperature) + "F");
			changeImage();
		}

	}

	class DecreaseTempListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e2) {
			environmentModel.decreaseTemperature();
			environmentView.temperatureLabel.setText(Integer.toString(environmentModel.temperature) + "F");
			changeImage();
		}

	}

	class EnableSystemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e3) {
			systemModel.readFromFile("currentScheduleFile.txt");
			environmentModel.systemEnabled = true;
			environmentView.systemPanel.setBackground(Color.green);
			myTimer.start();
		}

	}

	class DisableSystemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e4) {
			environmentModel.systemEnabled = false;
			environmentView.systemPanel.setBackground(Color.red.darker());
			myTimer.stop();
		}

	}

	@Override
	public void update(Observable o, Object arg) {
		this.environmentModel.timerCount = (Integer) arg;
		this.environmentModel.weeks = (this.environmentModel.timerCount / (24 * 7));
		this.environmentModel.days = (this.environmentModel.timerCount / 24) % 7;
		this.environmentModel.setHours(this.environmentModel.timerCount % 24);
		environmentView.dayTimeLabel.setText(environmentModel.days.toString());
		environmentView.hourTimeLabel.setText(environmentModel.getHours().toString());
		environmentView.weekNumLabel.setText(environmentModel.weeks.toString());
		if (environmentModel.days == 6 && environmentModel.getHours() == 23) {
			environmentView.systemDisableButton.doClick();
			transferFile();
			environmentView.systemEnableButton.doClick();
		}
	}

	public void transferFile() {
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			File inFile = new File("nextScheduleFile.txt");
			File outFile = new File("currentScheduleFile.txt");

			inStream = new FileInputStream(inFile);
			outStream = new FileOutputStream(outFile);

			byte[] buff = new byte[1024];

			int length;

			while ((length = inStream.read(buff)) > 0) {
				outStream.write(buff, 0, length);
			}

			// Closing the streams
			inStream.close();
			outStream.close();

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
