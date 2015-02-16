package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

/*
 * This command rotates the robot by "degrees" degrees
 */
public class RotateCommand {
	
	float rotateDegrees;
	float rotateSpeed;
	
	public RotateCommand(float speed, float degrees) {
		rotateDegrees = degrees;
		rotateSpeed = speed;
	}
	
    protected void initialize() {
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
    	Robot.chassis.mechanumDrive(0, -speed, 0);
    }

}
