package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToteApproachCommand extends Command {

	boolean continueCommand;
	int lastState;
	public final double DRIVE_TO = 4.0;
	public final double MAX_RANGE = 30.0;
	public boolean fieldOriented;

	public ToteApproachCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		continueCommand = true;
		lastState = 0;
		fieldOriented = Robot.chassis.getFieldOriented();

		Robot.chassis.setFieldOriented(false);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int chassisToteState = 0;

		// add to chassisToteState depending on sensors
		if (RobotMap.rightArmsharpIRRange.getRangefinderDistance() < MAX_RANGE) {
			chassisToteState += 1;
			System.out.println("added 1.");
		}
		if (RobotMap.rightsharpIRRange.getRangefinderDistance() < MAX_RANGE) {
			chassisToteState += 2;
			System.out.println("added 2.");
		}
		if (RobotMap.leftsharpIRRange.getRangefinderDistance() < MAX_RANGE) {
			chassisToteState += 4;
			System.out.println("added 4.");
		}
		if (RobotMap.leftArmsharpIRRange.getRangefinderDistance() < MAX_RANGE) {
			chassisToteState += 8;
			System.out.println("added 8.");
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

							forward(RobotMap.rightArmsharpIRRange
									.getRangefinderDistance(),
									RobotMap.rightsharpIRRange
											.getRangefinderDistance()),
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
				if (averageDistance > DRIVE_TO) {
					Robot.chassis.mechanumDrive(0.0, -1 * 0.2, 0.0);
				} else {
					continueCommand = false;
				}
			} else {
				Robot.chassis.mechanumDrive(
						0.0,

						forward(RobotMap.rightsharpIRRange
								.getRangefinderDistance(),
								RobotMap.leftsharpIRRange
										.getRangefinderDistance()),
						rotate(RobotMap.rightsharpIRRange
								.getRangefinderDistance(),
								RobotMap.leftsharpIRRange
										.getRangefinderDistance()));
			}
			break;
		case 7:
			Robot.chassis
					.mechanumDrive(
							0.2,

							forward(RobotMap.rightsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()),
							rotate(RobotMap.rightArmsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()));
			break;
		case 8:
			Robot.chassis.mechanumDrive(-1 * 0.2, 0.0, 0.0);
			break;
		case 12:
			Robot.chassis
					.mechanumDrive(
							-1 * 0.2,

							forward(RobotMap.leftArmsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()),
							rotate(RobotMap.leftsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftArmsharpIRRange
											.getRangefinderDistance()));
			break;
		case 14:
			Robot.chassis
					.mechanumDrive(
							-1 * 0.2,

							forward(RobotMap.rightsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()),
							rotate(RobotMap.rightsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftArmsharpIRRange
											.getRangefinderDistance()));
			break;
		case 15:
			if ((rotate(RobotMap.rightsharpIRRange.getRangefinderDistance(),
					RobotMap.leftsharpIRRange.getRangefinderDistance()) == 0)) {
				double averageDistance = (RobotMap.rightsharpIRRange
						.getRangefinderDistance() + RobotMap.leftsharpIRRange
						.getRangefinderDistance()) / 2;
				if (averageDistance > DRIVE_TO) {
					Robot.chassis.mechanumDrive(
							0.0,
							forward(RobotMap.rightsharpIRRange
									.getRangefinderDistance(),
									RobotMap.leftsharpIRRange
											.getRangefinderDistance()), 0.0);
				} else {
					continueCommand = false;
				}
			} else {
				Robot.chassis.mechanumDrive(
						0.0,

						forward(RobotMap.rightsharpIRRange
								.getRangefinderDistance(),
								RobotMap.leftsharpIRRange
										.getRangefinderDistance()),
						rotate(RobotMap.rightArmsharpIRRange
								.getRangefinderDistance(),
								RobotMap.leftArmsharpIRRange
										.getRangefinderDistance()));
			}
		default:
			if (lastState == 0 || lastState == 5 || lastState == 9
					|| lastState == 10 || lastState == 11 || lastState == 13) {
				continueCommand = false;
			} else {
				chassisToteState = lastState;
			}
			break;
		}

		lastState = chassisToteState;
		System.out.println("chassisToteState State: " + chassisToteState);
		System.out.println("Far Right: "
				+ RobotMap.rightArmsharpIRRange.getRangefinderDistance());
		System.out.println("Mid Right: "
				+ RobotMap.rightsharpIRRange.getRangefinderDistance());
		System.out.println("Mid Left: "
				+ RobotMap.leftsharpIRRange.getRangefinderDistance());
		System.out.println("Far Left: "
				+ RobotMap.leftArmsharpIRRange.getRangefinderDistance());

	}

	// 2 values to compare
	private double rotate(double value1, double value2) {
		// make value1 be rightmost sensor being tested
		if (value1 > value2 + 1) {
			return -1 * 0.2;
		} else if (value2 > value1 + 1) {
			return 0.2;
		} else {
			return 0.0;
		}
	}

	private double forward(double value1, double value2) {
		double average = (value1 + value2) / 2;
		if (average > 4) {
			if (average > DRIVE_TO) {
				return -0.2;
			} else {
				return 0.0;
			}
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
		Robot.chassis.setFieldOriented(fieldOriented);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
	}
}
