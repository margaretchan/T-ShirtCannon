package com.team766.robot;

import com.team766.lib.CommandBase;
import com.team766.robot.Actors.OperatorControl;
import com.team766.robot.Actors.Drive.Drive;
import com.team766.robot.Robot.GameState;

import interfaces.MyRobot;
import lib.ConstantsFileReader;
import lib.Scheduler;

public class Robot implements MyRobot{
	
	private final long RUN_TIME = 30;
	private long lastSleepTime = 0;
	
	public enum GameState{
		Teleop,
		Disabled,
		Test,
		Auton
	}
	
	public static GameState gameState = GameState.Disabled;

    public static GameState getState() {
        return gameState;
    }

    public static void setState(GameState state) {
        gameState = state;
    }

	public void robotInit() {
		CommandBase.init();
		
		Scheduler.getInstance().add(CommandBase.Drive, 100);
		Scheduler.getInstance().add(CommandBase.Cannon, 100);
	}

	public void disabledInit() {
    	Scheduler.getInstance().remove(OperatorControl.class);

		ConstantsFileReader.getInstance().loadConstants();
	}

	public void autonomousInit() {
		
	}

	public void teleopInit() {
		setState(GameState.Teleop);
		Scheduler.getInstance().add(new OperatorControl());
	}

	public void testInit() {
		
	}

	public void disabledPeriodic() {
		
	}

	public void autonomousPeriodic() {
		
	}

	public void teleopPeriodic() {
		sleep();
	}

	public void testPeriodic() {
		
	}

	public void startCompetition() {
		
	}
	
	private void sleep(){
		//Run loops at set speed
		try {
			//System.out.println("Curr: " + System.currentTimeMillis() + "\tLast: " + lastSleepTime);
			Thread.sleep(RUN_TIME - (System.currentTimeMillis() - lastSleepTime));
		} catch (Exception e) {
			System.out.println(toString() + "\tNo time to sleep, running behind schedule!! rut roh :/  Robert the robot drank 2 much coffee...can't sleep");
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {}
		}
		
		lastSleepTime = System.currentTimeMillis();
	}
	
	public String toString(){
		return "T-Shirt Cannon";
	}

}
