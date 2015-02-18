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
	private double initialAngle;
	private boolean isNegative = false;
	
	public RotateCommand(float speed, float degrees) {
		rotateDegrees = degrees;
		rotateSpeed = speed;
		if(degrees < 0) {
			isNegative = true;
		}
	}
	
    protected void initialize() {
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
    	initialAngle = RobotMap.chassis_drive_gyro.getAngle();
    	execute();
    }
    
    protected void execute() {
    	Robot.chassis.mechanumDrive(0, 0, rotateSpeed);
    }
    
    protected boolean isFinished() {
    	//If difference in angle is more than rotateDegrees
		if(initialAngle - RobotMap.chassis_drive_gyro.getAngle() >
			rotateDegrees * (isNegative ? -1. : 1.))
		{
			return !isNegative;
		}else{
	    	return isNegative;	
		}
    }
    
    protected void end() {
    	Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }
    
    protected void interrupted() {
    	end();
    }

}
