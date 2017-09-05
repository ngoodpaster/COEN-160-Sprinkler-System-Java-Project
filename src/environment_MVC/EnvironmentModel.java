package environment_MVC;

import java.util.Observable;

public class EnvironmentModel extends Observable{
	
	public int temperature;
	boolean systemEnabled;
	public Integer weeks, days, hours , timerCount;
	
	
	public EnvironmentModel(int temperature, int startTime){
		this.setTemperature(temperature);
		this.setStartTime(startTime);
		this.systemEnabled = false;
	}
	
	public void setTemperature(int temperature){
		this.temperature = temperature;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void increaseTemperature(){
		this.temperature ++;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void decreaseTemperature(){
		this.temperature --;
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public void setStartTime(int startTime){
		this.timerCount = startTime;
		this.weeks = this.timerCount / (24*7);
		this.days = (this.timerCount / 24) % 7;
		this.setHours(this.timerCount % 24);
	}
	
	public int getStartTime(){
		return timerCount;
	}

	public Integer getHours() {
		return hours;
	}

	public void setHours(Integer hours) {
		this.hours = hours;
	}
}
