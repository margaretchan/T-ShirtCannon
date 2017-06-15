package com.team766.tests;

import com.team766.lib.ConfigFile;
import com.team766.lib.Messages.UpdateCannon;

import lib.Scheduler;
import tests.RobotTestCase;

public class CannonTest extends RobotTestCase{
	
	public void testCannonFire() throws Exception{
		//Fire release:f air:t
		Scheduler.getInstance().sendMessage(new UpdateCannon(false, true));
		
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonRelease()).get() == false;}, 2); 
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonAir()).get() == true;}, 2); 
	}
	
	public void testCannonGetAir() throws Exception{
		//Get Air release:t air:f
		Scheduler.getInstance().sendMessage(new UpdateCannon(true, false));
		
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonRelease()).get() == true;}, 2); 
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonAir()).get() == false;}, 2); 
	}
	
	public void testCannonTT() throws Exception{
		//release:t air:t
		Scheduler.getInstance().sendMessage(new UpdateCannon(true, true));
		
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonRelease()).get() == true;}, 2); 
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonAir()).get() == true;}, 2); 
	}
	
	public void testCannonFF() throws Exception{
		//release:f air:f
		Scheduler.getInstance().sendMessage(new UpdateCannon(false, false));
		
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonRelease()).get() == false;}, 2); 
		assertTrueTimed(() -> {return instance.getSolenoid(ConfigFile.getCannonAir()).get() == false;}, 2); 
	}

}
