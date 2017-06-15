package com.team766.robot.Actors.Drive;

import com.team766.lib.Messages.MotorCommand;
import com.team766.robot.HardwareProvider;
import com.team766.robot.Actors.Drive.MotorSubCommand;

import interfaces.SpeedController;
import interfaces.SubActor;
import lib.Actor;
import lib.Message;

public class Drive extends Actor{
	
	SpeedController left = HardwareProvider.getInstance().getLeft();
	SpeedController right = HardwareProvider.getInstance().getRight();
	
	Message currentMessage;
	SubActor currentCommand;
	private boolean commandFinished;

	public void init() {
		acceptableMessages = new Class[]{MotorCommand.class};
		commandFinished = true;
	}
	
	public void run() {
		while(enabled){	
			iterate();
			sleep();
		}
		
		stopCurrentCommand();
	}
	
	public void iterate() {
		if(newMessage()){
			stopCurrentCommand();
			commandFinished = false;
			
			currentMessage = readMessage();
			
			if(currentMessage == null)
				return;
			else if(currentMessage instanceof MotorCommand)
				currentCommand = new MotorSubCommand(currentMessage);
		}
		
		step();
			
	}
	
	public String toString() {
		return "Actors:\tDrive";
	}

	public void step(){
		if(currentCommand != null){
			if(currentCommand.isDone()){
				stopCurrentCommand();
			}else{
				currentCommand.update();
			}
		}
	}
	
	private void stopCurrentCommand(){
		if(currentCommand != null){
			currentCommand.stop();
		}
		currentCommand = null;
		commandFinished = true;
	}
	
	public void setLeft(double speed){
		left.set(speed);
	}
	
	public void setRight(double speed){
		right.set(speed);
	}
	
	public void setMotors(double speed){
		right.set(speed);
		left.set(speed);
	}

}
