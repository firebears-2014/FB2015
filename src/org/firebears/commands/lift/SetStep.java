package org.firebears.commands.lift;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * While run, prepared for step.  When finished will go back to normal height.
 */
public class SetStep extends Command {

    public SetStep() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.addStep = 6.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.addStep = 0;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
