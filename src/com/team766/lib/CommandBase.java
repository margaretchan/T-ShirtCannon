package com.team766.lib;

import com.team766.robot.Actors.Drive.Drive;
import com.team766.robot.Actors.Cannon.Cannon;


import interfaces.SubActor;

public abstract class CommandBase implements SubActor{
	
	public static Drive Drive;	
	public static Cannon Cannon;
	
	public static void init() {
		Drive = new Drive();
		Cannon = new Cannon();
	}

	public boolean isDone() {
		return false;
	}

}
