package org.firebears.commands.lift;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * sets whether the motor for the lift is enabled or not.
 */
public class SetLiftMotorCommand extends Command {
	
	boolean enable_motor;
	
    public SetLiftMotorCommand(boolean enable) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	if (Robot.lift!=null) requires(Robot.lift);
    	enable_motor = enable;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.setMotor(enable_motor);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
