package sprinkler_MVC;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SprinklerController {
	public SprinklerGroupModel sprinklerGroupModel;
	public SprinklerPanel sprinklerGroupView;

	public SprinklerController(SprinklerGroupModel sprinklerGroupModel, SprinklerPanel sprinklerGroupView) {
		this.sprinklerGroupModel = sprinklerGroupModel;
		this.sprinklerGroupView = sprinklerGroupView;
		this.sprinklerGroupView.middlePanel.addMouseListener(new ShowStatusListener());
		this.sprinklerGroupView.userOverrideButton.addActionListener(new UserOverrideListener());
	}
	

	public void groupOff() {
		this.sprinklerGroupModel.setCurrentStatus(false);
		this.sprinklerGroupView.setBackground(Color.red.brighter());
	}

	public void groupOn() {
		this.sprinklerGroupModel.setCurrentStatus(true);
		this.sprinklerGroupView.setBackground(Color.green.darker());
	}

	public void desiredStatusOn() {
		this.sprinklerGroupModel.setDesiredStatus(true);
	}

	public void desiredStatusOff() {
		this.sprinklerGroupModel.setDesiredStatus(false);
	}

	class ShowStatusListener extends MouseAdapter {
		public void mouseClicked(MouseEvent evt) {
			sprinklerGroupView.changeStatus();
		}
	}

	class UserOverrideListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e2) {
			if (sprinklerGroupModel.isUserOverride()) {
				sprinklerGroupModel.setUserOverride(false);
				sprinklerGroupView.userOverrideButton.setText("Deactivate");
				sprinklerGroupView.status.setText("Active");
				sprinklerGroupView.hideStatus();
			} else {
				sprinklerGroupModel.setUserOverride(true);
				groupOff();
				sprinklerGroupView.userOverrideButton.setText("Activate");
				sprinklerGroupView.status.setText("Inactive");
				sprinklerGroupView.hideStatus();
			}
		}
	}
}
