package daily_MVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import system_MVC.SprinklerSystem;

/*
 * This is the CONTROLLER, so I named it DailyController to make it more clear.
 * 
 * CONTROLLER: Coordinates all interactions between the VIEW and the MODEL.
 * 				--> Contains reference to both the view and the model, so we will
 * 					implement the ActionListener in here to keep the code 
 * 					loosely coupled (lessen the dependent relationship between the MODEL
 * 					and the VIEW).
 * 
 */

public class DailyController {
	private DailySchedulePanel scheduleView;
	private SprinklerSystem systemModel;

	// Constructor for DailyController, takes in DailySchedulePanel View and
	// SprinklerSystem Model as arguments.
	public DailyController(DailySchedulePanel scheduleView, SprinklerSystem systemModel) {
		this.scheduleView = scheduleView;
		this.systemModel = systemModel;

		this.scheduleView.addUpdateListener(new UpdateListener());
	}

	class UpdateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				DailySchedule ds = new DailySchedule();
				ds.setNorth(scheduleView.northRegion.checkBox.isSelected());
				ds.setSouth(scheduleView.southRegion.checkBox.isSelected());
				ds.setEast(scheduleView.eastRegion.checkBox.isSelected());
				ds.setWest(scheduleView.westRegion.checkBox.isSelected());
				ds.overrideMin = scheduleView.minTemp.checkBox.isSelected();
				ds.overrideMax = scheduleView.maxTemp.checkBox.isSelected();

				if (ds.isNorth() == true) {
					ds.setNorthStartTime(Double.valueOf(scheduleView.northRegion.startTimeTextField.getText()));
					ds.setNorthEndTime(Double.valueOf(scheduleView.northRegion.endTimeTextField.getText()));
					ds.northFlowRate = Double.valueOf(scheduleView.northRegion.flowTextField.getText());
					if (ds.northStartTime >= ds.northEndTime || ds.northStartTime < 0 || ds.northFlowRate < 0) {
						throw new NumberFormatException();
					}
				}
				if (ds.isSouth() == true) {
					ds.southStartTime = Double.valueOf(scheduleView.southRegion.startTimeTextField.getText());
					ds.setSouthEndTime(Double.valueOf(scheduleView.southRegion.endTimeTextField.getText()));
					ds.southFlowRate = Double.valueOf(scheduleView.southRegion.flowTextField.getText());
					if (ds.southStartTime >= ds.southEndTime || ds.southStartTime < 0 || ds.southFlowRate < 0) {
						throw new NumberFormatException();
					}
				}
				if (ds.isEast() == true) {
					ds.eastStartTime = Double.valueOf(scheduleView.eastRegion.startTimeTextField.getText());
					ds.eastEndTime = Double.valueOf(scheduleView.eastRegion.endTimeTextField.getText());
					ds.eastFlowRate = Double.valueOf(scheduleView.eastRegion.flowTextField.getText());
					if (ds.eastStartTime >= ds.eastEndTime || ds.eastStartTime < 0 || ds.eastFlowRate < 0) {
						throw new NumberFormatException();
					}
				}
				if (ds.isWest() == true) {
					ds.westStartTime = Double.valueOf(scheduleView.westRegion.startTimeTextField.getText());
					ds.westEndTime = Double.valueOf(scheduleView.westRegion.endTimeTextField.getText());
					ds.westFlowRate = Double.valueOf(scheduleView.westRegion.flowTextField.getText());
					if (ds.westStartTime >= ds.westEndTime || ds.westStartTime < 0 || ds.westFlowRate < 0) {
						throw new NumberFormatException();
					}
				}
				if (ds.overrideMin == true) {
					ds.overrideMinTemp = Double.valueOf(scheduleView.minTemp.tempTextField.getText());
				}
				if (ds.overrideMax == true) {
					ds.overrideMaxTemp = Double.valueOf(scheduleView.maxTemp.tempTextField.getText());
				}
				if (ds.overrideMax == true && ds.overrideMin == true){
					if (ds.overrideMinTemp >= ds.overrideMaxTemp){
						throw new NumberFormatException();
					}
				}
				
				systemModel.writeToFile(ds);
				systemModel.nextSchedule = ds;

			} catch (NumberFormatException ex) {
				scheduleView.displayErrorMessage("Please input valid numbers into the fields.");
			}

		}

	}

}
