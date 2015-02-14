package org.firebears.commands.lift;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * While run, prepared for step. When finished will go back to normal height.
 */
public class SetStep extends Command {

	boolean Pressed;

	public SetStep(boolean pressed) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		Pressed = pressed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (Pressed) {
			Robot.lift.addStep = 8.0;
		} else {
			Robot.lift.addStep = 0.0;
		}
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
