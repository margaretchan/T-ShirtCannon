package com.team766.robot.Actors;

import com.team766.lib.Messages.MotorCommand;
import com.team766.lib.Messages.UpdateCannon;
import com.team766.lib.Buttons;
import com.team766.robot.Constants;
import com.team766.robot.HardwareProvider;
import com.team766.robot.Robot;

import interfaces.JoystickReader;
import interfaces.RelayOutput.Value;
import lib.Actor;
import lib.Scheduler;

public class OperatorControl extends Actor{
	
	JoystickReader jLeft = HardwareProvider.getInstance().getLeftJoystick();
	JoystickReader jRight = HardwareProvider.getInstance().getRightJoystick();
	JoystickReader jBox = HardwareProvider.getInstance().getBoxJoystick();

	
	private double[] leftAxis = new double[4];
	private double[] rightAxis = new double[4];
	private boolean[] prevPress = new boolean[1];
	
	private double previousLeft, previousRight, previousHeading;

	public void run() {
		while(Robot.getState() == Robot.GameState.Teleop && enabled){
			iterate();
			sleep();
		}
		
		Scheduler.getInstance().remove(this);
	}
	
	private double curveJoystick(double value){
		return value * Math.abs(value);
	}

	public void init() {
		previousLeft = 0;
		previousRight = 0;
		previousHeading = 0;
	}

	public String toString() {
		return "Operator Control";
	}

	public void step() {
		
	}

	public void iterate() {
		leftAxis[0] = (Math.abs(jLeft.getRawAxis(0)) > Constants.leftAxisDeadband)? curveJoystick(jLeft.getRawAxis(0)) : 0;
		leftAxis[1] = (Math.abs(jLeft.getRawAxis(1)) > Constants.leftAxisDeadband)? curveJoystick(-jLeft.getRawAxis(1)) : 0;			
		leftAxis[2] = (Math.abs(jLeft.getRawAxis(2)) > Constants.leftAxisDeadband)? curveJoystick(-jLeft.getRawAxis(2)) : 0;
		leftAxis[3] = (Math.abs(jLeft.getRawAxis(3)) > Constants.leftAxisDeadband)? jLeft.getRawAxis(3) : 0;
		
		rightAxis[0] = (Math.abs(jRight.getRawAxis(0)) > Constants.rightAxisDeadband)? curveJoystick(jRight.getRawAxis(0)) : 0;
		rightAxis[1] = (Math.abs(jRight.getRawAxis(1)) > Constants.rightAxisDeadband)? -jRight.getRawAxis(1) : 0;
		rightAxis[2] = (Math.abs(jRight.getRawAxis(2)) > Constants.rightAxisDeadband)? jRight.getRawAxis(2) : 0;
		rightAxis[3] = (Math.abs(jRight.getRawAxis(3)) > Constants.rightAxisDeadband)? -jRight.getRawAxis(3) : 0;
		
		if(Constants.TANK_DRIVE){
			if(previousLeft != leftAxis[1]) //1 is forward and back
				sendMessage(new MotorCommand(-leftAxis[1], MotorCommand.Motor.leftDrive));
			if(previousRight != jRight.getRawAxis(1))
				sendMessage(new MotorCommand(rightAxis[1], MotorCommand.Motor.rightDrive));
			
			previousLeft = leftAxis[1];
			previousRight = rightAxis[1];
		}else{
			
		}
		
		if(!prevPress[0] && jBox.getRawButton(Buttons.shoot)) {
			sendMessage(new UpdateCannon(true));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {e.printStackTrace();}
			sendMessage(new UpdateCannon(false));
		}
		prevPress[0] = jBox.getRawButton(Buttons.shoot);

	}
	
}
