package com.team766.lib.Messages;

import lib.Message;

public class UpdateCannon implements Message{
	
	private boolean valve;
	
	public UpdateCannon(boolean on){
		valve = on;
	}
	
	public boolean getValve(){
		return valve;
	}
	
	public String toString() {
		return "Message:\tUpdate Cannon";
	}

}
