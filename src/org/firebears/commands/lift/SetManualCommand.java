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
		requires(Robot.lift);
		manual = set;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.lift.manual_mode = manual;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.lift.manual_down = Robot.oi.getJoystickLift().getRawButton(1);
		Robot.lift.manual_up = Robot.oi.getJoystickLift().getRawButton(2);

		if (Robot.lift.manual_up && Robot.lift.manual_down) {
			Robot.lift.setSetpoint(Robot.lift.getLiftHeight());
		} else if (Robot.lift.manual_up) {
			Robot.lift.setSetpoint(Robot.lift.getLiftHeight() + 3);
		} else if (Robot.lift.manual_down) {
			Robot.lift.setSetpoint(Robot.lift.getLiftHeight() - 3);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !(Robot.lift.manual_mode);
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
