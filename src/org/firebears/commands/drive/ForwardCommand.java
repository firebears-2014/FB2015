package org.firebears.commands.drive;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives forward at a fixed speed until interupted
 */
//ToDo: add in a way to stop movement depending on where the robot is
public class ForwardCommand extends Command {

	double speed;
	public boolean fieldOriented;
	
    public ForwardCommand(double s) {
    	requires(Robot.chassis);
    	speed=s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
    	Robot.chassis.mechanumDrive(0, -speed, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.mechanumDrive(0, -speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return speed == 0.0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }
}
