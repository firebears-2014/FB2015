// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

	public static final String LIFT_ZERO_REF = "LIFT_ZERO_REF";
	public static final String LIFT_TOTE_PICKUP = "LIFT_TOTE_PICKUP";
	public static final String LIFT_TOTE_1 = "LIFT_TOTE_1";
	public static final String LIFT_TOTE_2 = "LIFT_TOTE_2";
	public static final String LIFT_TOTE_3 = "LIFT_TOTE_3";
	public static final String CHASSIS_DRIVE_TYPE_TAL = "CHASSIS_DRIVE_TYPE_TAL";
	public static final String CHASSIS_DRIVE_TYPE_JAG = "CHASSIS_DRIVE_TYPE_JAG";

	public static double lift_zero_ref;
	public static double lift_tote_pickup;
	public static double lift_tote_1;
	public static double lift_tote_2;
	public static double lift_tote_3;
	public static boolean chassis_drive_type_tal;

	// Talon Code

	public static Talon chassis_front_left_tal;
	public static Talon chassis_back_left_tal;
	public static Talon chassis_front_right_tal;
	public static Talon chassis_back_right_tal;

	// Jaguar Code
	// /*
	public static CANJaguar chassis_front_left_jag;
	public static CANJaguar chassis_back_left_jag;
	public static CANJaguar chassis_front_right_jag;
	public static CANJaguar chassis_back_right_jag;
	// */

	public static RobotDrive chassis_robot_drive;
	public static Gyro chassis_drive_gyro;
	public static AnalogPotentiometer liftpot;
	public static SpeedController lifttalon;
	public static Compressor grabbercompressor;

	public static Solenoid grabbersolenoid_right_open;
	public static Solenoid grabbersolenoid_right_close;
	public static Solenoid grabbersolenoid_left_open;
	public static Solenoid grabbersolenoid_left_close;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	public static org.firebears.sensors.sharpIRRange sharpIRRange;
	public static org.firebears.sensors.sharpIRProx sharpIRProx;

	public static BuiltInAccelerometer accelerometer;

	public static void init() {

		Preferences preferences = Preferences.getInstance();

		lift_zero_ref = preferences.getDouble(LIFT_ZERO_REF, 0);
		lift_tote_pickup = preferences.getDouble(LIFT_TOTE_PICKUP, 0);
		lift_tote_1 = preferences.getDouble(LIFT_TOTE_1, 0);
		lift_tote_2 = preferences.getDouble(LIFT_TOTE_2, 0);
		lift_tote_3 = preferences.getDouble(LIFT_TOTE_3, 0);
		chassis_drive_type_tal = preferences.getBoolean(CHASSIS_DRIVE_TYPE_TAL,
				true);

		// Talon code
		/*
		 * chassis_front_left = new CANTalon(5); chassis_back_left = new
		 * CANTalon(3); chassis_front_right = new CANTalon(4);
		 * chassis_back_right = new CANTalon(2);
		 */

		// Jag Code
		// /*
		try {
			chassis_front_left_tal = new Talon(4);
			LiveWindow.addActuator("chassis", "frontleft",
					chassis_front_left_tal);
		} catch (Exception e) {
			System.err.println("Failed to load Tal 4");
			e.printStackTrace();
		}
		try {
			chassis_front_right_tal = new Talon(2);
			LiveWindow.addActuator("chassis", "frontright",
					chassis_front_right_tal);
		} catch (Exception e) {
			System.err.println("Failed to load Tal 2");
			e.printStackTrace();
		}
		try {
			chassis_back_left_tal = new Talon(3);
			LiveWindow
					.addActuator("chassis", "backleft", chassis_back_left_tal);
		} catch (Exception e) {
			System.err.println("Failed to load Tal 3");
			e.printStackTrace();
		}
		try {
			chassis_back_right_tal = new Talon(5);
			LiveWindow.addActuator("chassis", "backright",
					chassis_back_right_tal);
		} catch (Exception e) {
			System.err.println("Failed to load Tal 5");
			e.printStackTrace();
		}
		// LiveWindow.addActuator("chassis", "frontleft", chassis_front_left);
		// LiveWindow.addActuator("chassis", "frontright", chassis_front_right);
		// LiveWindow.addActuator("chassis", "backleft", chassis_back_left);
		// LiveWindow.addActuator("chassis", "backright", chassis_back_right);
		// */
		// Jag Code
		// /*
		try {
			chassis_front_left_jag = new CANJaguar(4);
			LiveWindow.addActuator("chassis", "frontleft",
					chassis_front_left_jag);
		} catch (Exception e) {
			System.err.println("Failed to load Jag 4");
			e.printStackTrace();
		}
		try {
			chassis_front_right_jag = new CANJaguar(2);
			LiveWindow.addActuator("chassis", "frontright",
					chassis_front_right_jag);
		} catch (Exception e) {
			System.err.println("Failed to load Jag 2");
			e.printStackTrace();
		}
		try {
			chassis_back_left_jag = new CANJaguar(3);
			LiveWindow
					.addActuator("chassis", "backleft", chassis_back_left_jag);
		} catch (Exception e) {
			System.err.println("Failed to load Jag 3");
			e.printStackTrace();
		}
		try {
			chassis_back_right_jag = new CANJaguar(5);
			LiveWindow.addActuator("chassis", "backright",
					chassis_back_right_jag);
		} catch (Exception e) {
			System.err.println("Failed to load Jag 5");
			e.printStackTrace();
		}
		// LiveWindow.addActuator("chassis", "frontleft", chassis_front_left);
		// LiveWindow.addActuator("chassis", "frontright", chassis_front_right);
		// LiveWindow.addActuator("chassis", "backleft", chassis_back_left);
		// LiveWindow.addActuator("chassis", "backright", chassis_back_right);
		// */
		if (chassis_drive_type_tal) {
			chassis_robot_drive = new RobotDrive(chassis_front_left_tal,
					chassis_back_left_tal, chassis_front_right_tal,
					chassis_back_right_tal);
		} else {
			chassis_robot_drive = new RobotDrive(chassis_front_left_jag,
					chassis_back_left_jag, chassis_front_right_jag,
					chassis_back_right_jag);
		}

		chassis_robot_drive.setInvertedMotor(RobotDrive.MotorType.kFrontRight,
				true);
		chassis_robot_drive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft,
				false);
		chassis_robot_drive.setInvertedMotor(RobotDrive.MotorType.kRearRight,
				true);
		chassis_robot_drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft,
				false);

		chassis_robot_drive.setSafetyEnabled(true);
		chassis_robot_drive.setExpiration(0.1);
		chassis_robot_drive.setSensitivity(0.5);
		chassis_robot_drive.setMaxOutput(1.0);

		chassis_drive_gyro = new Gyro(1);
		chassis_drive_gyro.reset();
		LiveWindow.addSensor("Chassis", "drive_gyro", chassis_drive_gyro);
		chassis_drive_gyro.setSensitivity(0.007);
		chassis_drive_gyro.initGyro();
		liftpot = new AnalogPotentiometer(3, 1.0, 0.0);
		LiveWindow.addSensor("Lift", "pot", liftpot);

		lifttalon = new Talon(0);
		LiveWindow.addActuator("Lift", "talon", (Talon) lifttalon);

		grabbercompressor = new Compressor(0);

		grabbersolenoid_left_open = new Solenoid(3);
		LiveWindow.addActuator("Grabber", "solenoid_left_open",
				grabbersolenoid_left_open);

		grabbersolenoid_left_close = new Solenoid(1);
		LiveWindow.addActuator("Grabber", "solenoid_left_close",
				grabbersolenoid_left_close);

		grabbersolenoid_right_open = new Solenoid(4);
		LiveWindow.addActuator("Grabber", "solenoid_right_open",
				grabbersolenoid_right_open);

		grabbersolenoid_right_close = new Solenoid(2);
		LiveWindow.addActuator("Grabber", "solenoid_right_close",
				grabbersolenoid_right_close);

		accelerometer = new BuiltInAccelerometer();
		LiveWindow.addSensor("Accelerometer", "accelerometer", accelerometer);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		sharpIRRange = new org.firebears.sensors.sharpIRRange(0);
		// LiveWindow.addSensor("Rangefinder", "distance", sharpIRRange);
		sharpIRProx = new org.firebears.sensors.sharpIRProx(2);
		// LiveWindow.addSensor("Detector", "Proximity", sharpIRProx);
		sharpIRRange = new org.firebears.sensors.sharpIRRange(1);
		sharpIRProx = new org.firebears.sensors.sharpIRProx(3);
	}
}
