package display_MVC;

import daily_MVC.DailySchedule;
import sprinkler_MVC.SprinklerGroupModel;

public class DisplayModel {
	public SprinklerGroupModel northModel, southModel, eastModel, westModel;
	public DailySchedule currentSchedule;
	
	public DisplayModel(DailySchedule s){
		this.currentSchedule = s;
		this.northModel = new SprinklerGroupModel(s.northFlowRate);
		this.southModel = new SprinklerGroupModel(s.southFlowRate);
		this.eastModel = new SprinklerGroupModel(s.eastFlowRate);
		this.westModel = new SprinklerGroupModel(s.westFlowRate);
	}
}
