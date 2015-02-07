package org.firebears.subsystems;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;


/**
 * The subsystem that interfaces with the lights.
 */
public class Lights extends Subsystem {

	NetworkTable table;
	
	// Constants for pixel strips 
	public static final String STRIP_LIFT1 = "lift1";
	public static final String STRIP_LIFT2 = "lift2";
	public static final String STRIP_BOX = "box";
	public static final String STRIP_UNDERGLOW = "underglow";
	public static final String STRIP_CELEBRATE = "celebrate";
	
	// Constants for  animations
	public static final String PULSING_GREEN_ANIM = "PULSING_GREEN_ANIM";
	public static final String MOVING_BLUE_ANIM = "MOVING_BLUE_ANIM";
	public static final String FIRE_ANIM = "FIRE_ANIM";
	public static final String LIFT = "LIFT";
	public static final String CRAZY = "CRAZY";
	public static final String BINARY = "BIN_ANIM";
	public static final String BULB = "BULB";
	
	//TODO: Change #s to the proper id's and add undefined light strips

	public static final int LIFT_LIGHTS_RIGHT = 0;
	public static final int LIFT_LIGHTS_LEFT = 1;
	public static final int UNDERGLOW_LIGHTS = 2;
//	public static final int UNDERGLOW_LIGHTS = 0;

	double old_value;
	
	public Lights() {
		table = NetworkTable.getTable("lights");
		setStrip(STRIP_LIFT1, FIRE_ANIM);
		setStrip(STRIP_LIFT2, FIRE_ANIM);
		setStrip(STRIP_BOX, FIRE_ANIM);
		setStrip(STRIP_UNDERGLOW, FIRE_ANIM);
		setStrip(STRIP_CELEBRATE, FIRE_ANIM);
	}
	
	//Have no default command - would overload message bus.
    public void initDefaultCommand() {}

    public void setStrip(String which, String anim) {
        table.putString(which, anim);
    }
    
    private void setLiftHeight(String which, double height) {
        table.putNumber(which + ".value", height);
    }
    
    public void updateLiftHeight() {
    	double new_value =
        		Robot.lift.getLiftHeight();
        	new_value = (double)Math.round(new_value * 60);
        	
        	if(new_value != old_value) {
    			setLiftHeight(STRIP_LIFT1, new_value);
    			setLiftHeight(STRIP_LIFT2, new_value);
        	}
        	
        	old_value = new_value;

//        	Robot.lights.setLiftHeight(Lights.LIFT_LIGHTS_LEFT, Robot.lift.getLiftHeight());
//       	Robot.lights.setLiftHeight(Lights.LIFT_LIGHTS_RIGHT, Robot.lift.getLiftHeight());
    }
}

