package daily_MVC;

public class DailySchedule {
	// Time duration for sprinkler activation
	public double northStartTime, southStartTime, eastStartTime, westStartTime, northEndTime, southEndTime, eastEndTime,
			westEndTime, overrideMinTemp, overrideMaxTemp;
	// Booleans to hold whether sprinkler group is on or not
	private boolean north;
	private boolean south;
	private boolean east;
	private boolean west;
	public boolean overrideMin;
	public boolean overrideMax;
	// member variables to hold water consumption amounts
	public double northFlowRate, southFlowRate, eastFlowRate, westFlowRate;

	public boolean isNorth() {
		return north;
	}

	public void setNorth(boolean north) {
		this.north = north;
	}

	public boolean isSouth() {
		return south;
	}

	public void setSouth(boolean south) {
		this.south = south;
	}

	public boolean isEast() {
		return east;
	}

	public void setEast(boolean east) {
		this.east = east;
	}

	public boolean isWest() {
		return west;
	}

	public void setWest(boolean west) {
		this.west = west;
	}

	public double getNorthStartTime() {
		return northStartTime;
	}

	public void setNorthStartTime(double northStartTime) {
		this.northStartTime = northStartTime;
	}

	public double getNorthEndTime() {
		return northEndTime;
	}

	public void setNorthEndTime(double northEndTime) {
		this.northEndTime = northEndTime;
	}

	public double getSouthEndTime() {
		return southEndTime;
	}

	public void setSouthEndTime(double southEndTime) {
		this.southEndTime = southEndTime;
	}

}
