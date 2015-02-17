package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

/*
 * This command rotates the robot by "degrees" degrees
 */
public class RotateCommand {
	
	public boolean fieldOriented;
	
	float rotateDegrees;
	float rotateSpeed;
	
	public RotateCommand(float speed, float degrees) {
		rotateDegrees = degrees;
		rotateSpeed = speed;
	}
	
    protected void initialize() {
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
    	execute();
    }
    
    protected void execute() {
    	Robot.chassis.mechanumDrive(0, 0, rotateSpeed);
    }
    
    protected boolean isFinished() {
    	return false;
    }
    
    protected void end() {
    	Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }
    
    protected void interrupted() {
    	end();
    }

}
