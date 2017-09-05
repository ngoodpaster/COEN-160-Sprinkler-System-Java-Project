package waterflow_MVC;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public class WaterFlowModel{
	private int totalWaterConsumption;
	public ArrayList<Integer> weeklyConsumption = new ArrayList<Integer>();
	private int currentWeekConsumption;
	
	public WaterFlowModel(){}
	
	public void readFromFile(String fileName){
		BufferedReader reader = null;
		try {
			File inFile = new File(fileName);
			reader = new BufferedReader(new FileReader(inFile));

			String line = null;
			try {
				line = reader.readLine();
				this.totalWaterConsumption = Integer.parseInt(line);
				while ((line = reader.readLine()) != null){	
					int volume = Integer.parseInt(line);
					this.weeklyConsumption.add(volume);
				}
				currentWeekConsumption = weeklyConsumption.remove(weeklyConsumption.size() - 1);
			} catch(Exception e){
				System.err.println(e);
			} finally{
				reader.close();

			}		
		} catch(Exception e){
			System.err.println(e);
		} finally{
			
		}
	}
	
	public void writeToFile(){
		try{
			File file = new File("waterConsumptionFile.txt");
			// Will clear the file to allow for us to overwrite it
			file.delete();
			File fileNew = new File("waterConsumptionFile.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileNew));
			writer.write(String.valueOf(this.totalWaterConsumption));
			writer.newLine();
			for (int i = 0 ; i < weeklyConsumption.size() ; i++ ){
				writer.write(String.valueOf(this.weeklyConsumption.get(i)));
				writer.newLine();
			}
			writer.close();
		} catch(Exception e){
			System.out.println(e);
		}	
	}

	public int getCurrentWeekConsumption() {
		return currentWeekConsumption;
	}

	public void updateCurrentWeekConsumption(int updateAmount) {
		this.currentWeekConsumption += updateAmount;
	}
	
	public void resetCurrentWeekConsumption() {
		this.currentWeekConsumption = 0;
	}
	
	public int getTotalWaterConsumption() {
		return totalWaterConsumption;
	}

	public void updateTotalWaterConsumption(int totalWaterConsumption) {
		this.totalWaterConsumption += totalWaterConsumption;
	}
}
