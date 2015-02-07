package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteApproachCommand extends Command {

	boolean continueCommand;
	int lastState;
	public final double DRIVE_TO = 4.0;

	public ToteApproachCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		continueCommand = true;
		lastState = 0;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int chassisToteState = 0;

		// add to chassisToteState depending on sensors
		if (RobotMap.rightArmsharpIRRange.getRangefinderDistance() < 30) {
			chassisToteState += 1;
		}
		if (RobotMap.rightsharpIRRange.getRangefinderDistance() < 30) {
			chassisToteState += 2;
		}
		if (RobotMap.leftArmsharpIRRange.getRangefinderDistance() < 30) {
			chassisToteState += 4;
		}
		if (RobotMap.leftsharpIRRange.getRangefinderDistance() < 30) {
			chassisToteState += 8;
		}

		switch (chassisToteState) {
		case 1:
			Robot.chassis.mechanumDrive(0.2, 0.0, 0.0);
			break;
		case 2:
			Robot.chassis.mechanumDrive(0.2, 0.0, 0.0);
			break;
		case 3:
			Robot.chassis
					.mechanumDrive(
							0.2,
							0.0,
							rotate(RobotMap.rightArmsharpIRRange
									.getRangefinderDistance(),
									RobotMap.rightsharpIRRange
											.getRangefinderDistance()));
			break;
		case 4:
			Robot.chassis.mechanumDrive(-1 * 0.2, 0.0, 0.0);
			break;
		case 6:
			if (rotate(RobotMap.rightsharpIRRange.getRangefinderDistance(),
					RobotMap.leftsharpIRRange.getRangefinderDistance()) == 0) {
				double averageDistance = (RobotMap.rightsharpIRRange
						.getRangefinderDistance() + RobotMap.leftsharpIRRange
						.getRangefinderDistance()) / 2;
				if (averageDistance > DRIVE_TO + 1) {
					Robot.chassis.mechanumDrive(0.0, 0.2, 0.0);
				} else if (averageDistance < DRIVE_TO - 1) {
					Robot.chassis.mechanumDrive(0.0, -1 * 0.2, 0.0);
				} else {
					continueCommand = false;
				}
			}
			Robot.chassis
					.mechanumDrive(
							0.0,
							0.0,
							rotate(RobotMap.rightsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()));
			break;
		case 7:
			Robot.chassis
					.mechanumDrive(
							0.2,
							0.0,
							rotate(RobotMap.rightArmsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()));
			break;
		case 8:
			Robot.chassis.mechanumDrive(-1 * 0.2, 0.0, 0.0);
			break;
		case 12:
			Robot.chassis.mechanumDrive(
					-1 * 0.2,
					0.0,
					rotate(RobotMap.leftsharpIRRange.getRangefinderDistance(),
							RobotMap.leftArmsharpIRRange
									.getRangefinderDistance()));
			break;
		case 14:
			Robot.chassis.mechanumDrive(
					-1 * 0.2,
					0.0,
					rotate(RobotMap.rightsharpIRRange.getRangefinderDistance(),
							RobotMap.leftArmsharpIRRange
									.getRangefinderDistance()));
			break;
		case 15:
			if ((rotate(RobotMap.rightArmsharpIRRange.getRangefinderDistance(),
					RobotMap.leftArmsharpIRRange.getRangefinderDistance()) == 0)
					&& (rotate(
							RobotMap.rightsharpIRRange.getRangefinderDistance(),
							RobotMap.leftsharpIRRange.getRangefinderDistance()) == 0)) {
				double averageDistance = (RobotMap.rightArmsharpIRRange
						.getRangefinderDistance()
						+ RobotMap.rightsharpIRRange.getRangefinderDistance()
						+ RobotMap.leftsharpIRRange.getRangefinderDistance() + RobotMap.leftArmsharpIRRange
						.getRangefinderDistance()) / 4;
				if (averageDistance > DRIVE_TO + 1) {
					Robot.chassis.mechanumDrive(0.0, 0.2, 0.0);
				} else if (averageDistance < DRIVE_TO - 1) {
					Robot.chassis.mechanumDrive(0.0, -1 * 0.2, 0.0);
				} else {
					continueCommand = false;
				}
			}
			Robot.chassis.mechanumDrive(
					0.0,
					0.0,
					rotate(RobotMap.rightArmsharpIRRange
							.getRangefinderDistance(),
							RobotMap.leftArmsharpIRRange
									.getRangefinderDistance()));
		default:
			chassisToteState = lastState;
			break;
		}

		lastState = chassisToteState;

		if (chassisToteState == 0 || chassisToteState == 5
				|| chassisToteState == 9 || chassisToteState == 10
				|| chassisToteState == 11 || chassisToteState == 13) {
			continueCommand = false;
		}
	}

	// returns positive it 1 > 2
	// 2 values to compare
	private double rotate(double value1, double value2) {
		// make value1 be rightmost sensor being tested
		if (value1 > value2 + 1) {
			return 0.2;
		} else if (value2 > value1 + 1) {
			return -1 * 0.2;
		} else {
			return 0.0;
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !continueCommand;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.chassis.mechanumDrive(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.chassis.mechanumDrive(0, 0, 0);
	}
}
