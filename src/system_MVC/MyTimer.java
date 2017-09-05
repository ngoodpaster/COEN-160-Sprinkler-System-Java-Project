package system_MVC;

import java.util.*;
import java.awt.event.*;
import javax.swing.Timer;

public class MyTimer extends Observable{
	Timer timer;
	Integer timerCount;
	
	public MyTimer(int delay, Integer startTime){
		timerCount = startTime;
		ActionListener taskPerformer = new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				System.out.println(timerCount.toString());			
				timerCount++;
				setChanged();
				notifyObservers(timerCount.intValue());
			}	
		};
		timer = new Timer(delay , taskPerformer);
		
	}
	
	public void start(){
		timer.start();
	}
	
	public void stop(){
		timer.stop();
	}
	
}
