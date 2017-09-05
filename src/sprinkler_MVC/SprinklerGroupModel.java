package sprinkler_MVC;

public class SprinklerGroupModel {
		private boolean currentStatus, desiredStatus;
		public double flowRate;
		private boolean userOverride;
		
		public SprinklerGroupModel(double flowRate){
			this.setCurrentStatus(false);
			this.setDesiredStatus(false);
			this.flowRate = flowRate;
		}

		public boolean isDesiredStatus() {
			return desiredStatus;
		}

		public void setDesiredStatus(boolean desiredStatus) {
			this.desiredStatus = desiredStatus;
		}

		public boolean isCurrentStatus() {
			return currentStatus;
		}

		public void setCurrentStatus(boolean currentStatus) {
			this.currentStatus = currentStatus;
		}

		public boolean isUserOverride() {
			return userOverride;
		}

		public void setUserOverride(boolean userOverride) {
			this.userOverride = userOverride;
		}	
}
