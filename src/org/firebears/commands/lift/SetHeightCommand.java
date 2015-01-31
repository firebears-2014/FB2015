package org.firebears.commands.lift;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command For Setting the height of the lift.
 * 	@height: setpoint to set to:
 * 		"Lift_Pickup"
 * 		"Lift_Tote_0"
 * 		"Lift_Tote_1"
 * 		"Lift_Tote_2"
 * 		"Lift_Tote_3"
 */
public class SetHeightCommand extends Command {

	private String SetHeight;

	public SetHeightCommand(String height) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		SetHeight = height;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.lift.setSetpointInches(SetHeight);
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
