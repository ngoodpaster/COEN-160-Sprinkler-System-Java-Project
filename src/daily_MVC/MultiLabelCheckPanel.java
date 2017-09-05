package daily_MVC;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MultiLabelCheckPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel endTimeLabel, startTimeLabel, flowFieldLabel , tempLabel;
	JTextField endTimeTextField, startTimeTextField, flowTextField , tempTextField;
	JCheckBox checkBox;
	
	public MultiLabelCheckPanel(String checkLabelText , String fieldLabelOneText, String fieldLabelTwoText, String flowRateText){
		this.setLayout(new FlowLayout());
		
		checkBox = new JCheckBox(checkLabelText);
		checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
		checkBox.addActionListener(new Checked());
		
		endTimeLabel = new JLabel(fieldLabelTwoText);
		endTimeLabel.setEnabled(false);
		
		startTimeLabel = new JLabel(fieldLabelOneText);
		startTimeLabel.setEnabled(false);
		
		startTimeTextField = new JTextField();
		startTimeTextField.setEnabled(false);
		startTimeTextField.setPreferredSize(new Dimension(35 , 20));
		
		endTimeTextField = new JTextField();
		endTimeTextField.setEnabled(false);
		endTimeTextField.setPreferredSize(new Dimension(35 , 20));
		
		flowFieldLabel = new JLabel(flowRateText);
		flowFieldLabel.setEnabled(false);
		
		flowTextField = new JTextField();
		flowTextField.setEnabled(false);
		flowTextField.setPreferredSize(new Dimension(45 , 20));
		
		this.add(checkBox);
		
		this.add(startTimeLabel);
		this.add(startTimeTextField);
		this.add(endTimeLabel);
		this.add(endTimeTextField);
		this.add(flowFieldLabel);
		this.add(flowTextField);
		this.setVisible(true);
	}
	
	public MultiLabelCheckPanel(String checkLabelText , String fieldLabelText){
		this.setLayout(new FlowLayout());
		
		checkBox = new JCheckBox(checkLabelText);
		checkBox.setHorizontalTextPosition(SwingConstants.LEFT);
		checkBox.addActionListener(new OverrideChecked());
		
		tempLabel = new JLabel(fieldLabelText);
		tempLabel.setEnabled(false);
		
		tempTextField = new JTextField();
		tempTextField.setEnabled(false);
		tempTextField.setPreferredSize(new Dimension(30 , 20));
		
		this.add(checkBox);
		this.add(tempLabel);
		this.add(tempTextField);
		this.setVisible(true);
	}
	
	public class Checked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean next = false;
			if (checkBox.isSelected()){
				next = true;
			}
			flowFieldLabel.setEnabled(next);
			flowTextField.setEnabled(next);
			startTimeLabel.setEnabled(next);
			startTimeTextField.setEnabled(next);
			endTimeLabel.setEnabled(next);
			endTimeTextField.setEnabled(next);
		}
		
	}
	
	public class OverrideChecked implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean next = false;
			if (checkBox.isSelected()){
				next = true;
			}
			tempLabel.setEnabled(next);
			tempTextField.setEnabled(next);
		}
		
	}
}
