package system_MVC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import daily_MVC.DailySchedule;
import display_MVC.DisplayModel;
import environment_MVC.EnvironmentModel;
import waterflow_MVC.WaterFlowModel;

public class SprinklerSystem extends Observable {
	// Schedules
	public DailySchedule nextSchedule;
	public DailySchedule currentSchedule;

	// Models
	public EnvironmentModel environmentModel;
	public WaterFlowModel waterFlowModel;
	public DisplayModel displayModel;


	private boolean overriddenMin;
	private boolean overriddenMax;
	private static final double defaultFlowRate = 15;
	private int startTime;

	public SprinklerSystem() {
		readFromFile("currentScheduleFile.txt");
		setOverriddenMin(false);
		setOverriddenMax(false);
		this.startTime = readTimeFromFile("timeFile.txt");
		this.environmentModel = new EnvironmentModel(70, this.startTime);
		this.waterFlowModel = new WaterFlowModel();
		this.displayModel = new DisplayModel(this.currentSchedule);

	}

	public void readFromFile(String fileName) {
		currentSchedule = new DailySchedule();
		BufferedReader reader = null;
		try {
			File inFile = new File(fileName);
			reader = new BufferedReader(new FileReader(inFile));

			String line = null;
			try {
					for (int i = 0; i < 4; i++) {
						line = reader.readLine();
						
						// split each line into tokens
						String[] fields = line.split("[:]+");

						// the String to int conversion happens here
						String label = fields[0];
						boolean enabled = Boolean.parseBoolean(fields[1].trim());
						double startTime = Double.parseDouble(fields[2].trim());
						double endTime = Double.parseDouble(fields[3].trim());
						double flowRate = Double.parseDouble(fields[4].trim());

						switch (label) {
						case "north":
							currentSchedule.setNorth(enabled);
							currentSchedule.setNorthStartTime(startTime);
							currentSchedule.setNorthEndTime(endTime);
							if (enabled == false) {
								currentSchedule.northFlowRate = defaultFlowRate;
							} else {
								currentSchedule.northFlowRate = flowRate;
							}
							break;
						case "south":							
							currentSchedule.setSouth(enabled);
							currentSchedule.southStartTime = startTime;
							currentSchedule.setSouthEndTime(endTime);
							if (enabled == false) {
								currentSchedule.southFlowRate = defaultFlowRate;
							} else {
								currentSchedule.southFlowRate = flowRate;
							}
							break;
						case "east":
							currentSchedule.setEast(enabled);
							currentSchedule.eastStartTime = startTime;
							currentSchedule.eastEndTime = endTime;
							if (enabled == false) {
								currentSchedule.eastFlowRate = defaultFlowRate;
							} else {
								currentSchedule.eastFlowRate = flowRate;
							}
							break;
						case "west":
							currentSchedule.setWest(enabled);
							currentSchedule.westStartTime = startTime;
							currentSchedule.westEndTime = endTime;
							if (enabled == false) {
								currentSchedule.westFlowRate = defaultFlowRate;
							} else {
								currentSchedule.westFlowRate = flowRate;
							}
							break;
						default:
							throw (new Exception());
						}
					}
					line = reader.readLine();
					String[] minFields = line.split("[:]+");
					boolean overrideMinEnabled = Boolean.parseBoolean(minFields[1].trim());
					currentSchedule.overrideMin = overrideMinEnabled;
					if (overrideMinEnabled == true) {
						currentSchedule.overrideMinTemp = Double.parseDouble(minFields[2].trim());
					}
					line = reader.readLine();
					String[] maxFields = line.split("[:]+");
					boolean overrideMaxEnabled = Boolean.parseBoolean(maxFields[1].trim());
					currentSchedule.overrideMax = overrideMaxEnabled;
					if (overrideMaxEnabled == true) {
						currentSchedule.overrideMaxTemp = Double.parseDouble(maxFields[2].trim());
					}
				} 

			catch (Exception ex) {
				System.err.println(ex);
			} finally {
				this.setChanged();
				this.notifyObservers(currentSchedule);
				reader.close();
			}
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}



	public void writeToFile(DailySchedule schedule) {
		
		this.nextSchedule = schedule;
		File file = new File("nextScheduleFile.txt");
		// Will clear the file to allow for us to overwrite it
		file.delete();

		File fileNew = new File("nextScheduleFile.txt");

		try {
				DailySchedule ds = (DailySchedule) schedule;
				BufferedWriter bw = new BufferedWriter(new FileWriter(fileNew));
				
				bw.write("north:" + ds.isNorth() + ":" + ds.getNorthStartTime() + ":" + ds.getNorthEndTime() + ":" + ds.northFlowRate);
				bw.newLine();
				bw.write("south:" + ds.isSouth() + ":" + ds.southStartTime + ":" + ds.getSouthEndTime() + ":" + ds.southFlowRate);
				bw.newLine();
				bw.write("east:" + ds.isEast() + ":" + ds.eastStartTime + ":" + ds.eastEndTime + ":" + ds.eastFlowRate);
				bw.newLine();
				bw.write("west:" + ds.isWest() + ":" + ds.westStartTime + ":" + ds.westEndTime + ":" + ds.westFlowRate);
				bw.newLine();
				bw.write("overrideMin:" + ds.overrideMin + ":" + ds.overrideMinTemp);
				bw.newLine();
				bw.write("overrideMax:" + ds.overrideMax + ":" + ds.overrideMaxTemp);
				bw.close();
		
			

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			System.out.println(fileNew.getAbsolutePath());
		}
	}
	
	private int readTimeFromFile(String filename){
		File file = new File(filename);
			try{
				Integer time = 0;
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line = br.readLine();
				if(line != null){
					time = Integer.parseInt(line);
				}
				br.close();
				return time;
			}catch(IOException e){
				e.printStackTrace();
				return 0;
			}
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public boolean isOverriddenMin() {
		return overriddenMin;
	}

	public void setOverriddenMin(boolean overriddenMin) {
		this.overriddenMin = overriddenMin;
	}

	public boolean isOverriddenMax() {
		return overriddenMax;
	}

	public void setOverriddenMax(boolean overriddenMax) {
		this.overriddenMax = overriddenMax;
	}
}