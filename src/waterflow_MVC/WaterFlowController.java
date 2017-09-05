package waterflow_MVC;

import java.util.*;

import system_MVC.SprinklerSystem;

public class WaterFlowController implements Observer {
	WaterFlowModel waterFlowModel;
	WaterFlowView waterFlowView;
	SprinklerSystem systemModel;

	public WaterFlowController(WaterFlowModel waterFlowModel, WaterFlowView waterFlowView,
			SprinklerSystem systemModel) {
		this.waterFlowModel = waterFlowModel;
		this.waterFlowView = waterFlowView;
		this.systemModel = systemModel;
	}

	@Override
	public void update(Observable o, Object object) {
		if (object instanceof Integer) {
			if ((Integer) object != 1) {
				int updateAmount = 0;
				if (systemModel.displayModel.northModel.isCurrentStatus()) {
					updateAmount += systemModel.currentSchedule.northFlowRate;
				}
				if (systemModel.displayModel.southModel.isCurrentStatus()) {
					updateAmount += systemModel.currentSchedule.southFlowRate;
				}
				if (systemModel.displayModel.eastModel.isCurrentStatus()) {
					updateAmount += systemModel.currentSchedule.eastFlowRate;
				}
				if (systemModel.displayModel.westModel.isCurrentStatus()) {
					updateAmount += systemModel.currentSchedule.westFlowRate;
				}
				this.waterFlowModel.updateCurrentWeekConsumption(updateAmount);
				this.waterFlowModel.updateTotalWaterConsumption(updateAmount);
				if (systemModel.environmentModel.days == 6 && systemModel.environmentModel.getHours() == 23) {
					waterFlowModel.weeklyConsumption.add(waterFlowModel.getCurrentWeekConsumption());
					waterFlowModel.resetCurrentWeekConsumption();
					waterFlowModel.writeToFile();
					waterFlowView.swapGraph(new GraphPanel(waterFlowModel.weeklyConsumption , waterFlowModel.getTotalWaterConsumption()));
				}
			}
		}

	}
}
