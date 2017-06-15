package com.team766.robot.Actors.Cannon;

import com.team766.lib.Messages.UpdateCannon;
import com.team766.robot.HardwareProvider;

import interfaces.RelayOutput;
import interfaces.RelayOutput.Value;
import interfaces.SubActor;
import lib.Actor;
import lib.Message;

public class Cannon extends Actor{
	
	RelayOutput valve = HardwareProvider.getInstance().getCannonValve();

	Message currentMessage;
	SubActor currentCommand;
	private boolean commandFinished;

	public void init() {
		acceptableMessages = new Class[]{UpdateCannon.class};
		commandFinished = true;
		setValveOff();
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
			
			if(currentMessage == null){
				return;
			}
			if(currentMessage instanceof UpdateCannon){
				currentCommand = null;
				UpdateCannon cannonMessage = (UpdateCannon)currentMessage;
				if(cannonMessage.getValve()){
					setValveForeward();
				}
				else
					setValveOff();
				commandFinished = true;
			}
		}
		
		step();
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
	
	public String toString() {
		return "Actor:\tCannon";
	}
	
	protected void setValveForeward(){
		valve.set(Value.kForward);
	}
	
	protected void setValveOff(){
		valve.set(Value.kOff);
	}

}
