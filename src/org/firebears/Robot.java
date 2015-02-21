package org.firebears;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
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
	public DriverStation ds;
	SendableChooser autoChooser;

	Command autonomousCommand;
	Command AutoGM;
	Command AutoM;
	Command AutoSM;
	Command AutoTM;
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
	private final double STARTING_HEIGHT = 20;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		System.out.println("Starting robot code...");
		RobotMap.init();

		chassis = new Chassis();
		lift = new Lift();
		grabber = new Grabber();
		lights = new Lights();
		vision = new Vision();

		chassis.setReversed(RobotMap.chassis_drive_type_tal);

		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// instantiate which command should be used for the autonomous period
		// uncomment next 3 lines to override defaults.
		// autonomousCommand = new AutoStrafeCommand();
		// /*
		
		//autonomousCommand = new AutoM();
		if (RobotMap.autoSelect1 != null && RobotMap.autoSelect1.get() == true) {
			autonomousCommand = new AutoM();
		  	System.out.println("AUTONOMOUS IS Auto M:");
		  	System.out.println("Does: moves into the auto zone"); 
		} else if (RobotMap.autoSelect2 != null && RobotMap.autoSelect2.get() == true) { 
			autonomousCommand = new AutoGM();
			System.out.println("AUTONOMOUS IS Auto GM");
			System.out.println("Does:Grabs tote and brings it into auto zone ");
		} else if (RobotMap.autoSelect3 != null && RobotMap.autoSelect3.get() == true) {
			autonomousCommand = new AutoSM();
			System.out.println("AUTONOMOUS IS Auto SM");
			System.out.println("Does: stacks 3 totes and moves");
		} else if (RobotMap.autoSelect4 != null && RobotMap.autoSelect4.get() == true) {
			autonomousCommand = new AutoTM();
			System.out.println("AUTONOMOUS IS Auto TM");
			System.out.println("Does:stacks conainter on tote and moves");
		} else {
			System.out.println("NO AUTONOMOUS SELECTED, defaulting to Auto M");	
			autonomousCommand = new AutoM();
		}


		if (RobotMap.chassis_drive_gyro != null)
			RobotMap.chassis_drive_gyro.reset();

		ds = DriverStation.getInstance();
		// autoChooser = new SendableChooser();
		// autoChooser.addDefault("Rotary Switch", (Double) 0.);

		System.out.println("Started Robot Code!");
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {
		chassis.setFieldOriented(false);
		lights.disabled();
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
		ds.getAlliance();
		// change lights for autonomous
		if (ds.getAlliance().equals(Alliance.Red)) {
			lights.autonomous(true);
		} else {
			lights.autonomous(false);
		}
		lift.setSetpointInches(lift.LIFT_1_HEIGHT);
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

		boolean fieldOriented = (Preferences.getInstance()).getBoolean(
				RobotMap.CHASSIS_FIELD_ORIENTED, true);
		System.out.println("Chassis fieldOriented = " + fieldOriented);
		chassis.setFieldOriented(fieldOriented);

		// Go into teleop lights
		lights.isEarly = false;
		lights.teleop();
		lift.setSetpointInches(lift.LIFT_1_HEIGHT);
	}

	/**
	 * This function is called periodically during operator control
	 */

	public void teleopPeriodic() {
		Scheduler.getInstance().run();

		if (RobotMap.scoringPlatformSensor != null)
			SmartDashboard.putBoolean("Color Sensor Value",
					RobotMap.scoringPlatformSensor.get());
		if (RobotMap.leftArmsharpIRRange != null)
			SmartDashboard.putNumber("Far Left Distance from object",
					RobotMap.leftArmsharpIRRange.getRangefinderDistance());
		if (RobotMap.leftsharpIRRange != null)
			SmartDashboard.putNumber("Left side Distance from object",
					RobotMap.leftsharpIRRange.getRangefinderDistance());
		if (RobotMap.rightArmsharpIRRange != null)
			SmartDashboard.putNumber("Far Right Distance from object",
					RobotMap.rightArmsharpIRRange.getRangefinderDistance());
		if (RobotMap.rightsharpIRRange != null)
			SmartDashboard.putNumber("Right side Distance from object",
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

			SmartDashboard.putNumber("Lift 0", RobotMap.lift_tote_0);
			SmartDashboard.putNumber("Lift 1", RobotMap.lift_tote_1);
			SmartDashboard.putNumber("Lift 2", RobotMap.lift_tote_2);
			SmartDashboard.putNumber("Lift 3", RobotMap.lift_tote_3);

			SmartDashboard.putNumber("Tote State", chassis.toteState);
			SmartDashboard.putNumber("Tote Speed", chassis.approachSpeed);

		}

		SmartDashboard.putNumber("Lift 0", RobotMap.lift_tote_0);
		SmartDashboard.putNumber("Lift 1", RobotMap.lift_tote_1);
		SmartDashboard.putNumber("Lift 2", RobotMap.lift_tote_2);
		SmartDashboard.putNumber("Lift 3", RobotMap.lift_tote_3);

		// Run the teleop last twenty animations in the last 20 seconds
		if (lights.isEarly && ds.getMatchTime() >= 130.0) {
			lights.isEarly = false;
			lights.last_twenty();
		}
	}

	// hihihi
	public void testInit() {
		chassis.setFieldOriented(false);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		/*
		 * SmartDashboard.putData("Set Zero", new PreferenceSetup(
		 * RobotMap.LIFT_ZERO_REF)); SmartDashboard.putData("Set Tote Zero 0",
		 * new PreferenceSetup( RobotMap.LIFT_TOTE_0));
		 * SmartDashboard.putData("Set Tote One", new PreferenceSetup(
		 * RobotMap.LIFT_TOTE_1)); SmartDashboard.putData("Set Tote Two", new
		 * PreferenceSetup( RobotMap.LIFT_TOTE_2));
		 * SmartDashboard.putData("Set Tote Three", new PreferenceSetup(
		 * RobotMap.LIFT_TOTE_3));
		 */
		lift.setSetpoint(STARTING_HEIGHT);
	}
}
