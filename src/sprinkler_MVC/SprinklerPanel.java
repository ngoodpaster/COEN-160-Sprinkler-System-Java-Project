package sprinkler_MVC;

import java.awt.*;
import javax.swing.*;


public class SprinklerPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel picLabel1, picLabel2, picLabel3, picLabel4, picLabel5, status;
	JPanel middlePanel;
	public JButton userOverrideButton;
	private boolean statusShowing;
	
	public SprinklerPanel(){
		setBackground(Color.RED);
		setLayout(new BorderLayout());
		statusShowing = false;
		
		picLabel1 = new JLabel(addImage("../resources/sprinkler.png"));
		picLabel2 = new JLabel(addImage("../resources/sprinkler.png"));
		picLabel3 = new JLabel(addImage("../resources/sprinkler.png"));
		picLabel4 = new JLabel(addImage("../resources/sprinkler.png"));
		picLabel5 = new JLabel(addLargeImage("../resources/grass.png"));
		middlePanel = new JPanel();
		middlePanel.add(picLabel5);
		status = new JLabel("Active");
		status.setFont(new Font("Georgia", Font.BOLD, 18));
		
		userOverrideButton = new JButton("Deactivate");
		userOverrideButton.setPreferredSize(new Dimension(90,50));
		
		this.add(picLabel1 , BorderLayout.NORTH);
		this.add(picLabel2 , BorderLayout.SOUTH);
		this.add(picLabel3 , BorderLayout.EAST);
		this.add(picLabel4 , BorderLayout.WEST);
		this.add(middlePanel , BorderLayout.CENTER);
		setVisible(true);
	}
	
	public void hideStatus(){
		middlePanel.removeAll();
    	middlePanel.add(picLabel5, BorderLayout.CENTER);
    	middlePanel.revalidate();
    	middlePanel.repaint();
    	this.statusShowing = false;
	}
	
	public void viewStatus(){
		middlePanel.removeAll();
		middlePanel.setLayout(new BorderLayout());
		JPanel statusPanel = new JPanel(new FlowLayout());
		statusPanel.add(status);
		middlePanel.add(statusPanel, BorderLayout.NORTH);
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(userOverrideButton);
		middlePanel.add(buttonPanel, BorderLayout.CENTER);
		middlePanel.revalidate();
    	middlePanel.repaint();
    	this.statusShowing = true;
	}
	
	public void changeStatus(){
		if(statusShowing){
			this.hideStatus();
		} else{
			this.viewStatus();
		}
	}
	
	public ImageIcon addImage(String imgURL){
		ImageIcon image = new ImageIcon(getClass().getResource(imgURL));
		Image scaledImage = image.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		image = new ImageIcon(scaledImage);
		return image;
	}
	
	public ImageIcon addLargeImage(String imgURL){
		ImageIcon image = new ImageIcon(getClass().getResource(imgURL));
		Image scaledImage = image.getImage().getScaledInstance(130, 150, Image.SCALE_SMOOTH);
		image = new ImageIcon(scaledImage);
		return image;
	}	
}
