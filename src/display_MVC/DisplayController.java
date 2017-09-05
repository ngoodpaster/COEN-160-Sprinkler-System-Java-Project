package display_MVC;

import java.util.*;

import daily_MVC.*;
import environment_MVC.*;
import sprinkler_MVC.*;
import system_MVC.*;

public class DisplayController implements Observer {
	DisplayPanel displayView;
	DisplayModel displayModel;
	SprinklerSystem systemModel;
	SprinklerController northGroup, southGroup, eastGroup, westGroup;

	public DisplayController(DisplayPanel displayView, SprinklerSystem systemModel) {
		this.displayModel = systemModel.displayModel;
		this.displayView = displayView;
		this.systemModel = systemModel;

		this.northGroup = new SprinklerController(this.displayModel.northModel, this.displayView.northPanel);
		this.southGroup = new SprinklerController(this.displayModel.southModel, this.displayView.southPanel);
		this.eastGroup = new SprinklerController(this.displayModel.eastModel, this.displayView.eastPanel);
		this.westGroup = new SprinklerController(this.displayModel.westModel, this.displayView.westPanel);
	}

	public void setDesiredStatus() {
		if (displayModel.currentSchedule.isNorth() == true) {
			if (displayModel.currentSchedule.getNorthEndTime() - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.getNorthStartTime() - 1 > systemModel.environmentModel.hours ) {
				northGroup.desiredStatusOff();
			}
		}
		if (displayModel.currentSchedule.isSouth() == true) {
			if (displayModel.currentSchedule.getSouthEndTime() - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.southStartTime - 1 > systemModel.environmentModel.hours ) {
				southGroup.desiredStatusOff();
			}
		}
		if (displayModel.currentSchedule.isEast() == true) {
			if (displayModel.currentSchedule.eastEndTime - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.eastStartTime - 1 > systemModel.environmentModel.hours ) {
				eastGroup.desiredStatusOff();
			}
		}
		if (displayModel.currentSchedule.isWest() == true) {
			if (displayModel.currentSchedule.westEndTime - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.westStartTime - 1 > systemModel.environmentModel.hours ) {
				westGroup.desiredStatusOff();
			}
		}
	}

	@Override
	public void update(Observable o, Object object) {
		if (object instanceof DailySchedule) {
			displayModel.northModel.setUserOverride(false);
			displayModel.southModel.setUserOverride(false);
			displayModel.eastModel.setUserOverride(false);
			displayModel.westModel.setUserOverride(false);
			
			displayView.northPanel.userOverrideButton.setText("Deactivate");
			displayView.southPanel.userOverrideButton.setText("Deactivate");
			displayView.eastPanel.userOverrideButton.setText("Deactivate");
			displayView.westPanel.userOverrideButton.setText("Deactivate");
			
			displayView.northPanel.status.setText("Active");
			displayView.southPanel.status.setText("Active");
			displayView.eastPanel.status.setText("Active");
			displayView.westPanel.status.setText("Active");


			displayModel.currentSchedule = (DailySchedule) object;
			
			if (displayModel.currentSchedule.isNorth() == false) {
				northGroup.groupOff();
				northGroup.desiredStatusOff();
			} else {
				if (displayModel.currentSchedule.getNorthEndTime() - 1 > systemModel.environmentModel.hours  && displayModel.currentSchedule.getNorthStartTime() - 1 <= systemModel.environmentModel.hours ) {
					northGroup.groupOn();
					northGroup.desiredStatusOn();
				}
			}
			if (displayModel.currentSchedule.isSouth() == false) {
				southGroup.groupOff();
				southGroup.desiredStatusOff();
			} else {
				if (displayModel.currentSchedule.getSouthEndTime() - 1 > systemModel.environmentModel.hours  && displayModel.currentSchedule.southStartTime - 1 <= systemModel.environmentModel.hours ) {
					southGroup.groupOn();
					southGroup.desiredStatusOn();
				}
			}
			if (displayModel.currentSchedule.isEast() == false) {
				eastGroup.groupOff();
				eastGroup.desiredStatusOff();
			} else {
				if (displayModel.currentSchedule.eastEndTime - 1 > systemModel.environmentModel.hours  && displayModel.currentSchedule.eastStartTime - 1 <= systemModel.environmentModel.hours ) {
					eastGroup.groupOn();
					eastGroup.desiredStatusOn();
				}
			}
			if (displayModel.currentSchedule.isWest() == false) {
				westGroup.groupOff();
				westGroup.desiredStatusOff();
			} else {
				if (displayModel.currentSchedule.westEndTime - 1 > systemModel.environmentModel.hours  && displayModel.currentSchedule.westStartTime - 1 <= systemModel.environmentModel.hours ) {

					westGroup.groupOn();
					westGroup.desiredStatusOn();
				}
			}
			systemModel.setOverriddenMax(false);
			systemModel.setOverriddenMin(false);
			systemModel.environmentModel.increaseTemperature();
			systemModel.environmentModel.decreaseTemperature();
		} else if (object instanceof Integer) { // Every Second Update
			if (systemModel.isOverriddenMin() || systemModel.isOverriddenMax()) {
				setDesiredStatus();
			} else {
				if (displayModel.currentSchedule.isNorth() == true && !displayModel.northModel.isUserOverride()) {
					if (displayModel.currentSchedule.getNorthEndTime() - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.getNorthStartTime() - 1 > systemModel.environmentModel.hours ) {
						northGroup.groupOff();
						northGroup.desiredStatusOff();
					} else {
						northGroup.groupOn();
						northGroup.desiredStatusOn();
					}
				}
				if (displayModel.currentSchedule.isSouth() == true && !displayModel.southModel.isUserOverride()) {
					if (displayModel.currentSchedule.getSouthEndTime() - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.southStartTime - 1 > systemModel.environmentModel.hours ) {
						southGroup.groupOff();
						southGroup.desiredStatusOff();
					} else {
						southGroup.groupOn();
						southGroup.desiredStatusOn();
					}
				}
				if (displayModel.currentSchedule.isEast() == true && !displayModel.eastModel.isUserOverride()) {
					if (displayModel.currentSchedule.eastEndTime - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.eastStartTime - 1 > systemModel.environmentModel.hours ) {
						eastGroup.groupOff();
						eastGroup.desiredStatusOff();
					} else {
						eastGroup.groupOn();
						eastGroup.desiredStatusOn();
					}
				}
				if (displayModel.currentSchedule.isWest() == true && !displayModel.westModel.isUserOverride()) {
					if (displayModel.currentSchedule.westEndTime - 1 <= systemModel.environmentModel.hours  || displayModel.currentSchedule.westStartTime - 1 > systemModel.environmentModel.hours ) {
						westGroup.groupOff();
						westGroup.desiredStatusOff();
					} else {
						westGroup.groupOn();
						westGroup.desiredStatusOn();
					}
				}
			}
		} else if (object instanceof EnvironmentModel) {
			EnvironmentModel environmentModel = (EnvironmentModel) object;
			checkOverrideTemps(environmentModel.temperature);
		}
	}

	public void checkOverrideTemps(int temperature) {
		if (!systemModel.isOverriddenMin() && !systemModel.isOverriddenMax()) {
			if (systemModel.currentSchedule.overrideMin == true) {
				if (systemModel.currentSchedule.overrideMinTemp >= temperature) {
						northGroup.groupOff();
						southGroup.groupOff();
						eastGroup.groupOff();
						westGroup.groupOff();
					systemModel.setOverriddenMin(true);
				}
			}
			if (systemModel.currentSchedule.overrideMax == true) {
				if (systemModel.currentSchedule.overrideMaxTemp <= temperature) {
					if (!northGroup.sprinklerGroupModel.isUserOverride()) {
						northGroup.groupOn();
					}
					if (!southGroup.sprinklerGroupModel.isUserOverride()) {
						southGroup.groupOn();
					}
					if (!eastGroup.sprinklerGroupModel.isUserOverride()) {
						eastGroup.groupOn();
					}
					if (!westGroup.sprinklerGroupModel.isUserOverride()) {
						westGroup.groupOn();
					}
					systemModel.setOverriddenMax(true);
				}
			}

		} else if (systemModel.isOverriddenMin()) {
			if (systemModel.currentSchedule.overrideMinTemp < temperature) {

				if (northGroup.sprinklerGroupModel.isDesiredStatus()) {
					northGroup.groupOn();
				}
				if (southGroup.sprinklerGroupModel.isDesiredStatus()) {
					southGroup.groupOn();
				}
				if (eastGroup.sprinklerGroupModel.isDesiredStatus()) {
					eastGroup.groupOn();
				}
				if (westGroup.sprinklerGroupModel.isDesiredStatus()) {
					westGroup.groupOn();
				}
				systemModel.setOverriddenMin(false);

			}

		} else if (systemModel.isOverriddenMax()) {
			if (systemModel.currentSchedule.overrideMaxTemp > temperature) {
				if (!northGroup.sprinklerGroupModel.isDesiredStatus()) {
					northGroup.groupOff();
				}
				if (!southGroup.sprinklerGroupModel.isDesiredStatus()) {
					southGroup.groupOff();
				}
				if (!eastGroup.sprinklerGroupModel.isDesiredStatus()) {
					eastGroup.groupOff();
				}
				if (!westGroup.sprinklerGroupModel.isDesiredStatus()) {
					westGroup.groupOff();
				}
				systemModel.setOverriddenMax(false);

			}
		}
	}
}
