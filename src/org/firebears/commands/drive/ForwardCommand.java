package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives forward at a fixed speed until interrupted by DIO color
 */
// ToDo: add in a way to stop movement depending on where the robot is
public class ForwardCommand extends Command {

	double speed;
	public boolean fieldOriented;
	private boolean which;
	private int which_count = 0;
	private boolean cs;

	public ForwardCommand(double s, boolean colorSensor) {
		requires(Robot.chassis);
		speed = s;
		cs = colorSensor;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (cs) {
			which_count = 0;
			which = RobotMap.scoringPlatformSensor.get();
		}
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
		Robot.chassis.mechanumDrive(0, -speed, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if ((RobotMap.scoringPlatformSensor.get() != which) && cs) {
			which_count++;
			System.out.println("More whiches");
		}
		Robot.chassis.mechanumDrive(0, -speed, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (cs) {
			if (which_count > 1) {
				System.out.println("witch kill robot");
				return true;
			} else {
				return false;
			}
		} else {
			return speed == 0;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
