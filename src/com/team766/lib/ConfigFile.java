package com.team766.lib;

import interfaces.ConfigFileReader;

public class ConfigFile {

	public static int[] getLeftMotor(){
		return ConfigFileReader.getInstance().getPorts("leftMotor");
	}
	
	public static int[] getRightMotor(){
		return ConfigFileReader.getInstance().getPorts("rightMotor");
	}
	
	public static int getLeftJoystick(){
		return ConfigFileReader.getInstance().getPort("leftJoy");
	}
	
	public static int getRightJoystick(){
		return ConfigFileReader.getInstance().getPort("rightJoy");
	}
	
	public static int getBoxJoystick(){
		return ConfigFileReader.getInstance().getPort("boxJoy");
	}
	
	public static int getCannonValve(){
		return ConfigFileReader.getInstance().getPort("cannonValve");
	}
}
