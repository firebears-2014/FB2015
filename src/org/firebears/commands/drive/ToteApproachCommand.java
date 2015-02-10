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
	private double speedStrafe = 0.2;
	private double speedForward = 0.2;
	private double speedRotate = 0.2;

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
		double rightArm = RobotMap.rightArmsharpIRRange
				.getRangefinderDistance();
		double rightInner = RobotMap.rightsharpIRRange.getRangefinderDistance();
		double leftInner = RobotMap.leftsharpIRRange.getRangefinderDistance();
		double leftArm = RobotMap.leftArmsharpIRRange.getRangefinderDistance();

		// add to chassisToteState depending on sensors
		if (rightArm < MAX_RANGE) {
			chassisToteState += 1;
			System.out.println("added 1.");
		}
		if (rightInner < MAX_RANGE) {
			chassisToteState += 2;
			System.out.println("added 2.");
		}
		if (leftInner < MAX_RANGE) {
			chassisToteState += 4;
			System.out.println("added 4.");
		}
		if (leftArm < MAX_RANGE) {
			chassisToteState += 8;
			System.out.println("added 8.");
		}

		switch (chassisToteState) {
		case 1:
			Robot.chassis.mechanumDrive(speedStrafe, 0.0, 0.0);
			break;
		case 2:
			Robot.chassis.mechanumDrive(speedStrafe, 0.0, 0.0);
			break;
		case 3:
			Robot.chassis.mechanumDrive(speedStrafe,

			/* forward(rightArm, rightInner) */0.0,
					rotate(rightArm, rightInner));
			break;
		case 4:
			Robot.chassis.mechanumDrive(-1 * speedStrafe, 0.0, 0.0);
			break;
		case 6:
			if (rotate(rightInner, leftInner) == 0) {
				double averageDistance = (rightInner + leftInner) / 2;
				if (averageDistance < 1.5 * DRIVE_TO) {
					if (averageDistance > 0.5 * DRIVE_TO) {
						continueCommand = false;
					} else {
						Robot.chassis.mechanumDrive(0.0,
								backward(rightInner, leftInner), 0.0);
					}

				} else {
					Robot.chassis.mechanumDrive(0.0,
							forward(rightInner, leftInner), 0.0);
				}
			} else {
				Robot.chassis.mechanumDrive(0.0,
				/* forward(rightInner, leftInner) */0.0,
						rotate(rightInner, leftInner));
			}
			break;
		case 7:
			Robot.chassis.mechanumDrive(speedStrafe,

			/* forward(rightInner, leftInner) */0.0,
					rotate(rightArm, leftInner));
			break;
		case 8:
			Robot.chassis.mechanumDrive(-1 * speedStrafe, 0.0, 0.0);
			break;
		case 12:
			Robot.chassis.mechanumDrive(-1 * speedStrafe,

			/*
			 * forward(leftInner, leftArm)
			 */0.0, rotate(leftInner, leftArm));
			break;
		case 14:
			Robot.chassis.mechanumDrive(-1 * speedStrafe,

			/* forward(rightInner, leftInner) */0.0,
					rotate(rightInner, leftArm));
			break;
		case 15:
			if (rotate(rightArm, leftArm) == 0) {
				double averageDistance = (rightArm + leftArm) / 2;
				if (averageDistance < 1.5 * DRIVE_TO) {
					if (averageDistance > 0.5 * DRIVE_TO) {
						continueCommand = false;
					} else {
						Robot.chassis.mechanumDrive(0.0,
								backward(rightArm, leftArm), 0.0);
					}

				} else {
					Robot.chassis.mechanumDrive(0.0,
							forward(rightArm, leftArm), 0.0);
				}
			} else {
				Robot.chassis.mechanumDrive(0.0, /* forward(rightArm, leftArm) */
						0.0, rotate(rightArm, leftArm));
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
		if (value1 > value2 + 2) {
			return -1 * speedRotate;
		} else if (value2 > value1 + 2) {
			return speedRotate;
		} else {
			return 0.0;
		}
	}

	private double forward(double value1, double value2) {
		double average = (value1 + value2) / 2;
		double speed = ((average + 6) / MAX_RANGE) * speedForward;
		if (speed > 0.2) {
			speed = 0.2;
		}
		if (average > DRIVE_TO && average < MAX_RANGE) {
			return -speed;
		} else {
			return 0.0;
		}
	}

	private double backward(double value1, double value2) {
		double average = (value1 + value2) / 2;
		double speed = ((average + 6) / MAX_RANGE) * speedForward;
		if (speed > 0.2) {
			speed = 0.2;
		}
		if (average > DRIVE_TO && average < MAX_RANGE) {
			return speed;
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
