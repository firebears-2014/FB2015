package org.firebears.commands.lift;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetManualCommand extends Command {

	private boolean manual;

	public SetManualCommand(boolean set) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		manual = set;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.lift.manual_mode = manual;
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
