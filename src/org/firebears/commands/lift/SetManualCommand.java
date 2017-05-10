package org.firebears.commands.lift;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SetManualCommand extends Command {

	private boolean manualUp;

	public SetManualCommand(boolean goUp) {
		requires(Robot.lift);
		manualUp = goUp;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(manualUp) {
			Robot.lift.setSetpoint(Robot.lift.getLiftHeight() + 4.5);
		} else {
			Robot.lift.setSetpoint(Robot.lift.getLiftHeight() - 3);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.lift.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.setSetpoint(Robot.lift.getSetpoint());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.lift.setSetpoint(Robot.lift.getSetpoint());
	}
}
