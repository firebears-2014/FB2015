package org.firebears.commands.drive;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives sideways at a fixed speed until interupted
 */
public class StrafeCommand extends Command {
	
	double speed;
	
    public StrafeCommand(double s) {
    	requires(Robot.chassis);
    	speed=s;
    }


    // Called just before this Command runs the first time
    protected void initialize() {
    

    	Robot.chassis.mechanumDrive(speed, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.mechanumDrive(speed, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {

    	Robot.chassis.mechanumDrive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    	Robot.chassis.mechanumDrive(0, 0, 0);
    }
}