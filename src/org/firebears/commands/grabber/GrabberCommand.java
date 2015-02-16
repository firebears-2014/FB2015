package org.firebears.commands.grabber;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command to close or open grabber.
 * 	@close_grabbers: whether to close the grabber or open it.
 */
public class GrabberCommand extends Command {
	
	boolean closeGrabber = true;

	public GrabberCommand(boolean close_grabbers) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.grabber);
		closeGrabber = close_grabbers;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.grabber.setGrabbers(closeGrabber);
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
