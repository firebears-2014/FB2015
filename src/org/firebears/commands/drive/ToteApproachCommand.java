package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ToteApproachCommand extends Command {

	boolean continueCommand;
	int lastState;
	public final double DRIVE_TO = 6.0;
	public final double MAX_RANGE = 30.0;
	public final double MAX_SPEED = .2;// .5;// ****PVM 2/11 ???? no timer and
										// no
										// termination when distance <= DRIVE_TO
	public final double MIN_SPEED = .1;// .2;// ****PVM 2/11 need to evaluate
										// zones
										// where distance is reliable, sorta
										// reliable and stinks
	public final double MAX_ROTATE = .2;// .4;// ****PVM 2/11
	public final double MIN_ROTATE = .1;// .2;// ****PVM 2/11
	public final double MAX_ROTATE_TOL = 5;// ****PVM 2/11
	public final double MIN_ROTATE_TOL = .2;// ****PVM 2/11
	public final double MAX_STRAFE = .2;// .5;// ****PVM 2/11
	public final double MIN_STRAFE = .2;// ****PVM 2/11
	public boolean fieldOriented;

	private double rightArm;
	private double rightInner;
	private double leftInner;
	private double leftArm;

	private final double TIME = 10.00;

	private Timer timer;

	public ToteApproachCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.chassis);
		timer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		continueCommand = true;
		lastState = 6;
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
		timer.reset();
		timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		int chassisToteState = 0;
		
		rightArm = RobotMap.rightArmsharpIRRange.getRangefinderDistance();
		rightInner = RobotMap.rightsharpIRRange.getRangefinderDistance();
		leftInner = RobotMap.leftsharpIRRange.getRangefinderDistance();
		leftArm = RobotMap.leftArmsharpIRRange.getRangefinderDistance();
		
		double minSensor = Math.min(rightArm,rightInner);
		minSensor = Math.min(minSensor,leftInner);
		minSensor = Math.min(minSensor,rightArm);
		if (rightArm > 1.5* minSensor)rightArm = 1000;
		if (rightInner > 1.5* minSensor)rightInner = 1000;
		if (leftArm > 1.5* minSensor)leftArm = 1000;
		if (leftInner > 1.5* minSensor)leftInner = 1000;

		// add to chassisToteState depending on sensors
		if (rightArm < MAX_RANGE) {
			chassisToteState += 1;
			// System.out.println("added 1.");
		}
		if (rightInner < MAX_RANGE) {
			chassisToteState += 2;
			// System.out.println("added 2.");
		}
		if (leftInner < MAX_RANGE) {
			chassisToteState += 4;
			// System.out.println("added 4.");
		}
		if (leftArm < MAX_RANGE) {
			chassisToteState += 8;
			// System.out.println("added 8.");
		}
		
		/*
		if(chassisToteState== 15){
			double avg = (leftArm + leftInner + rightArm + rightArm) / 4.0;
			if(leftArm > 1.5 *avg ){chassisToteState = 7;}
			else if(rightArm > 1.5 *avg ){chassisToteState = 14;} 
			
		}*/
		
		
		
		

		switch (chassisToteState) {// each case ought to check distance to tote
									// and angle before issuing commands!!!!
		case 1://LEGAL strafe right 
			Robot.chassis.mechanumDrive(getStrafeSpeed(rightArm),
					getSpeed(rightArm), 0.0);// ****PVM 2/11
			break;
			
		case 2://LEGAL (narrow tote) strafe right
			Robot.chassis.mechanumDrive(getStrafeSpeed(rightInner),
					getSpeed(rightInner), 0.0);// ****PVM 2/11
			break;
		case 3://LEGAL  strafe right
			double distance3 = (rightArm + rightInner) / 2.0; // ****PVM 2/11
			Robot.chassis.mechanumDrive(getStrafeSpeed(distance3),// ****PVM																	
					getSpeed(distance3), 0.0);// ****PVM																	
			break;
			
		case 4:// LEGAL (narrow tote) strafe left
			Robot.chassis.mechanumDrive(-1 * getStrafeSpeed(leftInner),
					getSpeed(leftInner), 0.0);// ****PVM 2/11
			break;
			
		case 5: // use only for distance, invalid strafe or rotate case,
			double distance5 = (rightArm + leftInner) / 2.0;// ****PVM 2/11
			Robot.chassis.mechanumDrive(0.0, getSpeed(distance5), 0.0);// ****PVM																		
		break;

		case 6://**** LEGAL  narrow tote centered, 
			//stop if close, go fwd only if rotate within tolerance, else rotate only to tolerance
			double distance6 = (leftInner + rightInner) / 2.0; // ****PVM 2/11
			
			if (distance6 <= 1.2 * DRIVE_TO // ****PVM
			&& rotate(leftInner, rightInner)== 0.0)
				{continueCommand = false;}
							
			else if (rotate(leftInner, rightInner)== 0.0)			
				Robot.chassis.mechanumDrive(0.0, getSpeed(distance6),0.0);					
			else
				Robot.chassis.mechanumDrive(0.0, 0.0,rotate(rightInner, leftInner));	
		break;
			

		case 7://**** LEGAL strafe right and rotate 
			//go fwd only if rotate within tolerance, else rotate only to tolerance
			double distance7 = (leftInner + rightInner + rightArm) / 3.0;// ****PVM
	
			if (rotate(leftInner, rightInner)== 0.0)			
					Robot.chassis.mechanumDrive(getStrafeSpeed(distance7), getSpeed(distance7),0.0);					
			else
					Robot.chassis.mechanumDrive(getStrafeSpeed(distance7), 0.0,rotate(rightInner, leftInner));	
		break;		

			
		case 8://****LEGAL strafe left no rotate go foreward 
			double distance8 = leftArm;// ****PVM 2/11
			Robot.chassis.mechanumDrive(-1 * getStrafeSpeed(distance8),
					getSpeed(distance8), 0.0);
			break;

		case 9: // use only for distance, invalid strafe or rotate case,
			double distance9 = (leftArm + rightArm) / 2.0;// ****PVM 2/11
			Robot.chassis.mechanumDrive(0.0, getSpeed(distance9), 0.0);// ****PVM
																		// 2/11
			break;

		case 10: // use only for distance, invalid strafe or rotate case,
			double distance10 = (leftArm + rightInner) / 2.0;// ****PVM 2/11
			Robot.chassis.mechanumDrive(0.0, getSpeed(distance10), 0.0);// ****PVM
																		// 2/11
			break;

		case 11: // use only for distance, invalid strafe or rotate case
			double distance11 = (leftArm + rightInner + rightArm) / 3.0;// ****PVM
																		// 2/11
			Robot.chassis.mechanumDrive(0.0, getSpeed(distance11), 0.0);// ****PVM
																		// 2/11
			break;

		case 12://LEGAL  Strafe left, no rotate, go forward
			double distance12 = (leftArm + leftInner) / 2.0;// ****PVM 2/11
			Robot.chassis.mechanumDrive(-1 * getStrafeSpeed(distance12),
					getSpeed(distance12), 0.0);
			break;

		case 13: // use only for distance, invalid strafe or rotate case,
			double distance13 = (leftArm + leftInner + rightArm) / 3.0;
			Robot.chassis.mechanumDrive(0.0, getSpeed(distance13), 0.0);// ****PVM																		// 2/11
			break;

		case 14://****LEGAL strafe left, rotate and forward
			//go fwd only if rotate within tolerance, else rotate only to tolerance
			double distance14 = (leftArm + leftInner + rightInner) / 3.0;
			if (rotate(leftInner, rightInner)== 0.0)			
				Robot.chassis.mechanumDrive(-1 * getStrafeSpeed(distance14), getSpeed(distance14),0.0);					
		else
				Robot.chassis.mechanumDrive(-1 * getStrafeSpeed(distance14), 0.0,rotate(rightInner, leftInner));
			break;
			
		case 15://LEGAL wide tote centered
			//stop if close, go fwd only if rotate within tolerance, else rotate only to tolerance
			//****left arm or rt arm can be way different than the inner sensors but still be here
			double distance15 = (leftArm + leftInner + rightArm + rightArm) / 4.0;
			
			if (distance15 <= 1.2 * DRIVE_TO // ****PVM
					&& rotate(leftInner, rightInner)== 0.0)
						{continueCommand = false;}
			
			else if (rotate(leftInner, rightInner)== 0.0)			
				Robot.chassis.mechanumDrive(0.0, getSpeed(distance15),0.0);					
			else
				Robot.chassis.mechanumDrive(0.0, 0.0,rotate(rightInner, leftInner));	
		break;
			
		default:
			continueCommand = false;
			break;
		}

		Robot.chassis.toteState = chassisToteState;
		Robot.chassis.approachSpeed = getSpeed(rightArm);//????????
		// lastState = chassisToteState;//****PVM 2/11
		System.out.println("chassisToteState State: " + chassisToteState);
		/*
		 * System.out.println("Far Right: " +
		 * RobotMap.rightArmsharpIRRange.getRangefinderDistance());
		 * System.out.println("Mid Right: " +
		 * RobotMap.rightsharpIRRange.getRangefinderDistance());
		 * System.out.println("Mid Left: " +
		 * RobotMap.leftsharpIRRange.getRangefinderDistance());
		 * System.out.println("Far Left: " +
		 * RobotMap.leftArmsharpIRRange.getRangefinderDistance());
		 */
	}

	// 2 values to compare
	private double rotate(double value1, double value2) {
		double avDistance = (value1 + value2) / 2.0; // ****PVM 2/11
		if (value1 > value2 + getRotateTolerance(avDistance)) {// ****PVM 2/11
			return -1 * getRotateSpeed(avDistance);// ****PVM 2/11
		} else if (value2 > value1 + getRotateTolerance(avDistance)) {// ****PVM
																		// 2/11
			return getRotateSpeed(avDistance);// ****PVM 2/11
		} else {
			return 0.0;
		}
	}

	/*
	 * private double forward(double value1, double value2) { double average =
	 * (value1 + value2) / 2; double speed = ((average + 6) / MAX_RANGE) *
	 * speedForward; if (speed > 0.2) { speed = 0.2; } if (average > DRIVE_TO &&
	 * average < MAX_RANGE) { return -speed; } else { return 0.0; } }
	 * 
	 * private double backward(double value1, double value2) { double average =
	 * (value1 + value2) / 2; double speed = ((average + 6) / MAX_RANGE) *
	 * speedForward; if (speed > 0.2) { speed = 0.2; } if (average > DRIVE_TO &&
	 * average < MAX_RANGE) { return speed; } else { return 0.0; } }
	 */

	private double getSpeed(double distance) { // ****PVM 2/11
		double speed = ((distance - DRIVE_TO) / (MAX_RANGE - DRIVE_TO))
				* (MAX_SPEED - MIN_SPEED) + MIN_SPEED;
		if ((distance - DRIVE_TO) <= 0)
			return 0;
		else
			return -1 * speed;
	}

	private double getRotateSpeed(double distance) {// ****PVM 2/11
		return ((distance - DRIVE_TO) / (MAX_RANGE - DRIVE_TO))
				* (MAX_ROTATE - MIN_ROTATE) + MIN_ROTATE;
	}

	private double getRotateTolerance(double distance) {// ****PVM 2/11
		return ((distance - DRIVE_TO) / (MAX_RANGE - DRIVE_TO))
				* (MAX_ROTATE_TOL - MIN_ROTATE_TOL) + MIN_ROTATE_TOL;
	}

	private double getStrafeSpeed(double distance) { // ****PVM 2/11
		return ((distance - DRIVE_TO) / (MAX_RANGE - DRIVE_TO))
				* (MAX_STRAFE - MIN_STRAFE) + MIN_STRAFE;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !continueCommand;// || timer.get() > TIME;
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
