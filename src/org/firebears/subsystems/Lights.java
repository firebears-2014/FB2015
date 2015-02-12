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
	public static final String ANIM_PULSE = "PULSING_GREEN_ANIM";
	public static final String ANIM_MOVE = "MOVING_BLUE_ANIM";
	public static final String ANIM_FIRE = "FIRE_ANIM";
	public static final String ANIM_LIFT = "LIFT";
	public static final String ANIM_CRAZY = "CRAZY";
	public static final String ANIM_BINARY = "BIN_ANIM";
	public static final String ANIM_BULB = "BULB";
	public static final String ANIM_CATERPILLAR = "ANIM_CATERPILLAR";
	
	//Color Schemes
	public static final int CS_RED = 0;
	public static final int CS_BLUE = 1;
	public static final int CS_YELLOW = 2;
	public static final int CS_RED_YELLOW = 3;
	public static final int CS_RED_WHITE = 4;
	public static final int CS_WHITE = 5;
	
	//TODO: Change #s to the proper id's and add undefined light strips

	public static final int LIFT_LIGHTS_RIGHT = 0;
	public static final int LIFT_LIGHTS_LEFT = 1;
	public static final int UNDERGLOW_LIGHTS = 2;
//	public static final int UNDERGLOW_LIGHTS = 0;

	double old_value;
	public boolean isEarly = true;
	
	public Lights() {
		table = NetworkTable.getTable("lights");
		setStrip(STRIP_LIFT1, ANIM_FIRE);
		setStrip(STRIP_LIFT2, ANIM_FIRE);
		setStrip(STRIP_BOX, ANIM_FIRE);
		setStrip(STRIP_UNDERGLOW, ANIM_FIRE);
		setStrip(STRIP_CELEBRATE, ANIM_FIRE);
	}
	
	//Have no default command - would overload message bus.
    public void initDefaultCommand() {}

    public void setStrip(String which, String anim) {
        table.putString(which, anim);
    }
    
    private void setLiftHeight(String which, double height) {
        table.putNumber(which + ".value", height);
    }
    
    private void setColor(String which, int height) {
    	table.putNumber(which + ".color", (double) height);
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
    
    //Called from Robot.disabledInit()
    public void disabled() {
    	setStrip(STRIP_LIFT1, ANIM_FIRE);
    	setStrip(STRIP_LIFT2, ANIM_FIRE);
    	setStrip(STRIP_BOX, ANIM_FIRE);
    	setStrip(STRIP_UNDERGLOW, ANIM_FIRE);
    	setStrip(STRIP_CELEBRATE, ANIM_FIRE);
    }
    
    public void autonomous(boolean red) {
    	setStrip(STRIP_LIFT1, ANIM_BINARY);
    	setStrip(STRIP_LIFT2, ANIM_BINARY);
    	setStrip(STRIP_BOX, ANIM_PULSE);
    	setStrip(STRIP_UNDERGLOW, ANIM_CATERPILLAR);
    	setStrip(STRIP_CELEBRATE, ANIM_PULSE);
    	if(red) {
    		setColor(STRIP_CELEBRATE, CS_RED);
    	}else{
    		setColor(STRIP_CELEBRATE, CS_BLUE);	
    	}
	}
    
    public void teleop() {
    	setStrip(STRIP_LIFT1, ANIM_LIFT);
    	setStrip(STRIP_LIFT2, ANIM_LIFT);
    	setStrip(STRIP_BOX, ANIM_PULSE);
    		setColor(STRIP_BOX, CS_RED_YELLOW);
    	setStrip(STRIP_UNDERGLOW, ANIM_CATERPILLAR);
			setColor(STRIP_UNDERGLOW, CS_YELLOW);
		setStrip(STRIP_CELEBRATE, ANIM_PULSE);
    		setColor(STRIP_CELEBRATE, CS_RED_YELLOW);
    }
    
    public void last_twenty() {
		//No change required for lift lights
		setStrip(STRIP_BOX, ANIM_PULSE);
			setColor(STRIP_BOX, CS_RED_WHITE);
		setStrip(STRIP_UNDERGLOW, ANIM_CATERPILLAR);
			setColor(STRIP_UNDERGLOW, CS_WHITE);
		setStrip(STRIP_CELEBRATE, ANIM_PULSE);
			setColor(STRIP_CELEBRATE, CS_RED_WHITE);
    }
    
    public void celebrate() {
    	setStrip(STRIP_LIFT1, ANIM_CRAZY);
    	setStrip(STRIP_LIFT2, ANIM_CRAZY);
    	setStrip(STRIP_BOX, ANIM_CRAZY);
    	setStrip(STRIP_UNDERGLOW, ANIM_CRAZY);
    	setStrip(STRIP_CELEBRATE, ANIM_CRAZY);
    }
}