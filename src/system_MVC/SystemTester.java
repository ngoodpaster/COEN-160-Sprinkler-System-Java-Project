package system_MVC;

public class SystemTester {
	public static void main(String[] args) {
		SprinklerSystem systemModel = new SprinklerSystem();
		systemModel.waterFlowModel.readFromFile("waterConsumptionFile.txt");
		SystemWindow systemView = new SystemWindow(systemModel);
		@SuppressWarnings("unused")
		SprinklerSystemController systemController = new SprinklerSystemController(systemModel, systemView);
	}
}
