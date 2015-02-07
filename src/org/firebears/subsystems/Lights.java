package org.firebears.subsystems;

import org.firebears.commands.lights.LightsCommand;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


/**
 * The subsystem that interfaces with the lights.
 */
public class Lights extends Subsystem {

	NetworkTable table;
	
	private final String[] ab = {
			"strip1", "strip2", "strip3"
	};
	
	public static final String PULSING_GREEN_ANIM = "PULSING_GREEN_ANIM";
	public static final String MOVING_BLUE_ANIM = "MOVING_BLUE_ANIM";
	public static final String FIRE_ANIM = "FIRE_ANIM";
	public static final String LIFT = "LIFT";
	public static final String CRAZY = "CRAZY";
	public static final String BINARY = "BIN_ANIM";
	public static final String BULB = "BULB";
	
	public static final String RANDOM_ANIM = "RANDOM";
	
	//TODO: Change #s to the proper id's and add undefined light strips

	public static final int LIFT_LIGHTS_RIGHT = 0;
	public static final int LIFT_LIGHTS_LEFT = 1;
	public static final int UNDERGLOW_LIGHTS = 2;
//	public static final int UNDERGLOW_LIGHTS = 0;

	public Lights() {
		table = NetworkTable.getTable("lights");
		setStrip(0, PULSING_GREEN_ANIM);
		setStrip(1, MOVING_BLUE_ANIM);
		setStrip(2, FIRE_ANIM);
		for(int i = 0; i < 3; i++) {
			setLiftHeight(i, 0.0);
		}
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new LightsCommand());
	}

    public void setStrip(int which, String anim) {
    	if(anim == RANDOM_ANIM) {
    		switch(table.getString(ab[which])) {
    			case PULSING_GREEN_ANIM:
    				anim = MOVING_BLUE_ANIM;
    				break;
    			case MOVING_BLUE_ANIM:
    				anim = FIRE_ANIM;
    				break;
    			case FIRE_ANIM:
    				anim = LIFT;
    				break;
    			case LIFT:
    				anim = PULSING_GREEN_ANIM;
    				break;
    		}
    	}
        table.putString(ab[which], anim);
    }
    
    public void setLiftHeight(int which, double height) {
        table.putNumber(ab[which] + ".value", height);
    }
}

