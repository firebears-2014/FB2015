package org.firebears.commands.drive;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives in any direction at a fixed speed until interrupted
 */
public class ChassisCommand extends Command {

	double x;
	double y;
	double z;

	public boolean fieldOriented;

	/**
	 * 
	 * @param px
	 *            strafe speed
	 * @param py
	 *            forward/backward speed
	 * @param pz
	 *            rotation speed
	 */
	public ChassisCommand(double px, double py, double pz) {
		requires(Robot.chassis);
		x = px;
		y = py;
		z = pz;
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		Robot.chassis.mechanumDrive(x, -y, z);

		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.chassis.mechanumDrive(x, -y, z);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.setFieldOriented(fieldOriented);
		Robot.chassis.mechanumDrive(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.chassis.setFieldOriented(fieldOriented);
		Robot.chassis.mechanumDrive(0, 0, 0);
	}
}
