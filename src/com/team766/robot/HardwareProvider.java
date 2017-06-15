package com.team766.robot;

import com.team766.lib.ConfigFile;
import com.team766.robot.HardwareProvider;

import interfaces.JoystickReader;
import interfaces.RelayOutput;
import interfaces.RobotProvider;
import interfaces.SpeedController;

public class HardwareProvider {
	
private static HardwareProvider instance;
	
	public static HardwareProvider getInstance(){
		if(instance == null){
			instance = new HardwareProvider();
		}
		return instance;
	}
	
	public SpeedController getLeft(){
		return RobotProvider.instance.getMotor(ConfigFile.getLeftMotor()[0], true);
	}
	
	public SpeedController getRight(){
		return RobotProvider.instance.getMotor(ConfigFile.getRightMotor()[0], true);
	}
	
	public JoystickReader getLeftJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getLeftJoystick());
	}
	
	public JoystickReader getRightJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getRightJoystick());
	}
	
	public RelayOutput getCannonValve(){
		return RobotProvider.instance.getRelay(ConfigFile.getCannonValve());
	}

	public JoystickReader getBoxJoystick(){
		return RobotProvider.instance.getJoystick(ConfigFile.getBoxJoystick());
	}
}
