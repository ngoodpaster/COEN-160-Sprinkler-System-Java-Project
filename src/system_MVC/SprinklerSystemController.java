package system_MVC;

import java.awt.event.*;
import java.io.*;

import daily_MVC.DailyController;
import display_MVC.DisplayController;
import environment_MVC.EnvironmentController;
import waterflow_MVC.WaterFlowController;

public class SprinklerSystemController {
	
	WaterFlowController waterFlowController;
	EnvironmentController environmentController;
	DailyController dailyController;
	DisplayController displayController;
	SprinklerSystem systemModel; 
	SystemWindow systemView;
	MyTimer timer;
	
	int startTime;
	
	public SprinklerSystemController(SprinklerSystem systemModel, SystemWindow systemView){
		this.startTime = systemModel.getStartTime();
		this.timer = new MyTimer(500, startTime);
		this.systemModel = systemModel;
		this.systemView = systemView;
		
		this.systemModel.environmentModel.setStartTime(this.startTime);
		this.dailyController = new DailyController(this.systemView.dailyScheduleView, this.systemModel);
		this.environmentController = new EnvironmentController(this.systemView.environmentView, this.systemModel.environmentModel, this.systemModel, this.timer);
		this.waterFlowController = new WaterFlowController(this.systemModel.waterFlowModel, this.systemView.waterFlowView, this.systemModel);
		this.displayController = new DisplayController(this.systemView.displayPanel , this.systemModel);
		
		this.timer.addObserver(this.environmentController);
		this.timer.addObserver(this.displayController);
		this.timer.addObserver(this.waterFlowController);
		this.systemModel.environmentModel.addObserver(displayController);
		this.systemModel.addObserver(displayController);
		
		systemView.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent windowEvent){
				systemModel.waterFlowModel.weeklyConsumption.add(systemModel.waterFlowModel.getCurrentWeekConsumption());
				systemModel.waterFlowModel.writeToFile();
				writeTimeToFile("timeFile.txt");
			}
		});
	}
	
	private void writeTimeToFile(String filename){
		File file = new File(filename);
		file.delete();
		File updateFile = new File(filename);
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(updateFile));
			bw.write(String.valueOf(this.timer.timerCount));
			bw.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
}
