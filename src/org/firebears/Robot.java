package org.firebears;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.firebears.commands.PreferenceSetup;
import org.firebears.commands.auto.*; //Autonomous Commands
import org.firebears.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Command autonomousCommand;
	Command AutoGM;
	Command AutoM;
	Command AutoSM;
	private BuiltInAccelerometer accel = new BuiltInAccelerometer();
	public static OI oi;
	public static Chassis chassis;
	public static Lift lift;
	public static Grabber grabber;
	public static Lights lights;
	public static Vision vision;
	public static double accX;
	public static double accZ;
	public static double accY;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();

		chassis = new Chassis();
		lift = new Lift();
		grabber = new Grabber();
		lights = new Lights();
		vision = new Vision();
		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();
		//CameraServer server = CameraServer.getInstance();
		//server.setQuality(50);
		//server.startAutomaticCapture("cam0");
		// instantiate which command should be used for the autonomous period
		// uncomment next 3 lines to override defaults.
		// autonomousCommand = new AutoStrafeCommand();
		// /*
		if (oi.autoSelect1 != null && oi.autoSelect1.get() == false) {
			autonomousCommand = new AutoSM();
		} else if (oi.autoSelect2 != null && oi.autoSelect2.get() == false) {
			autonomousCommand = new AutoGM();
		} else if (oi.autoSelect3 != null && oi.autoSelect3.get() == false) {
			autonomousCommand = new AutoM();
		}// else if (OI.autoSelect4.get()==false){autonomousCommand = new
			// AutonomousCommand();
			// }
			// */
		if (RobotMap.chassis_drive_gyro != null)
			RobotMap.chassis_drive_gyro.reset();
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		chassis.setFieldOriented(false);
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		if (RobotMap.chassis_drive_gyro != null)
			RobotMap.chassis_drive_gyro.reset();
		chassis.setFieldOriented(false);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		if (RobotMap.chassis_drive_gyro != null)
			RobotMap.chassis_drive_gyro.reset();
		boolean fieldOriented = (Preferences.getInstance())
				.getBoolean(RobotMap.CHASSIS_FIELD_ORIENTED, true);
		System.out.println("Chassis fieldOriented = " + fieldOriented);
		chassis.setFieldOriented(fieldOriented);
	}

	/**
	 * This function is called periodically during operator control
	 */

	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		if (oi.scoringPlatformSensor != null)
			SmartDashboard.putBoolean("Color Sensor Value",
					oi.scoringPlatformSensor.get());
		if (RobotMap.leftArmsharpIRRange != null)
			SmartDashboard.putNumber("far left Distance from object",
					RobotMap.leftArmsharpIRRange.getRangefinderDistance());
		if (RobotMap.leftsharpIRRange != null)
			SmartDashboard.putNumber("left side Distance from object",
					RobotMap.leftsharpIRRange.getRangefinderDistance());
		if (RobotMap.rightArmsharpIRRange != null)
			SmartDashboard.putNumber("far right Distance from object",
					RobotMap.rightArmsharpIRRange.getRangefinderDistance());
		if (RobotMap.rightsharpIRRange != null)
			SmartDashboard.putNumber("right side Distance from object",
					RobotMap.rightsharpIRRange.getRangefinderDistance());

		if (RobotMap.chassis_drive_gyro != null)
			SmartDashboard.putNumber("gyro value",
					RobotMap.chassis_drive_gyro.getAngle());

		if (RobotMap.DEBUG) {
			SmartDashboard.putNumber("Accel X", accel.getX());
			SmartDashboard.putNumber("Accel Y", accel.getY());
			SmartDashboard.putNumber("Accel Z", accel.getZ());
			SmartDashboard.putNumber("liftPot", RobotMap.liftpot.get());
			SmartDashboard.putNumber("Lift Height (Inches)",
					Robot.lift.getLiftHeight());
			SmartDashboard.putNumber("Lift SetPoint", Robot.lift.getSetpoint());
			SmartDashboard.putNumber("Lift Output", Robot.lift.lift_output);
		}

		SmartDashboard.putNumber("Lift 0", RobotMap.lift_tote_pickup);
		SmartDashboard.putNumber("Lift 1", RobotMap.lift_tote_1);
		SmartDashboard.putNumber("Lift 2", RobotMap.lift_tote_2);
		SmartDashboard.putNumber("Lift 3", RobotMap.lift_tote_3);

	}

	public void testInit() {
		chassis.setFieldOriented(false);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		SmartDashboard.putData("Set Zero", new PreferenceSetup(
				RobotMap.LIFT_ZERO_REF));
		SmartDashboard.putData("Set Tote Zero Pickup", new PreferenceSetup(
				RobotMap.LIFT_TOTE_PICKUP));
		SmartDashboard.putData("Set Tote One", new PreferenceSetup(
				RobotMap.LIFT_TOTE_1));
		SmartDashboard.putData("Set Tote Two", new PreferenceSetup(
				RobotMap.LIFT_TOTE_2));
		SmartDashboard.putData("Set Tote Three", new PreferenceSetup(
				RobotMap.LIFT_TOTE_3));

	}
}
