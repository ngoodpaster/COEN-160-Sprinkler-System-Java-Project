package display_MVC;

import java.awt.*;


import javax.swing.*;

import daily_MVC.DailySchedule;
import sprinkler_MVC.SprinklerController;
import sprinkler_MVC.SprinklerPanel;
import system_MVC.SprinklerSystem;

public class DisplayPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel north, south, northWest, northEast, southWest, southEast;
	SprinklerPanel northPanel, southPanel, eastPanel, westPanel;
	SprinklerSystem systemModel;
	DailySchedule currentSchedule;
	SprinklerController northGroup, southGroup, eastGroup, westGroup;

	public DisplayPanel(SprinklerSystem systemModel){
		setLayout(new BorderLayout());

		this.systemModel = systemModel;

		currentSchedule = this.systemModel.currentSchedule;

		north = new JPanel(new FlowLayout());
		south = new JPanel(new FlowLayout());

		northWest = new JPanel();
		northWest.setPreferredSize(new Dimension(125, 150));
		north.add(northWest);

		northPanel = new SprinklerPanel();
		northPanel.setPreferredSize(new Dimension(180, 150));
		northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		north.add(northPanel);

		northEast = new JPanel();
		northEast.setPreferredSize(new Dimension(125, 150));
		north.add(northEast);

		southWest = new JPanel();
		southWest.setPreferredSize(new Dimension(125, 150));
		south.add(southWest);

		southPanel = new SprinklerPanel();
		southPanel.setPreferredSize(new Dimension(200, 150));
		southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		south.add(southPanel);

		southEast = new JPanel();
		southEast.setPreferredSize(new Dimension(125, 150));
		south.add(southEast);

		westPanel = new SprinklerPanel();
		westPanel.setPreferredSize(new Dimension(160, 200));
		westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		eastPanel = new SprinklerPanel();
		eastPanel.setPreferredSize(new Dimension(160, 200));
		eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		JLabel house = new JLabel(addImage("../resources/topviewhouse.png", 195, 245));
		this.add(house, BorderLayout.CENTER);

		JLabel nwtree = new JLabel(addImage("../resources/topviewtree2.png", 150, 150));
		northWest.add(nwtree, BorderLayout.CENTER);

		JLabel netree = new JLabel(addImage("../resources/topviewtree2.png", 150, 150));
		northEast.add(netree, BorderLayout.CENTER);

		JLabel swtree = new JLabel(addImage("../resources/topviewtree2.png", 150, 150));
		southWest.add(swtree, BorderLayout.CENTER);

		JLabel setree = new JLabel(addImage("../resources/topviewtree2.png", 150, 150));
		southEast.add(setree, BorderLayout.CENTER);
		
		
		this.add(north, BorderLayout.NORTH);
		this.add(south, BorderLayout.SOUTH);
		this.add(eastPanel, BorderLayout.EAST);
		this.add(westPanel, BorderLayout.WEST);
		setVisible(true);
	}

	public ImageIcon addImage(String imgURL, int w, int h) {
		ImageIcon image = new ImageIcon(getClass().getResource(imgURL));
		Image scaledImage = image.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
		image = new ImageIcon(scaledImage);
		return image;
	}

}