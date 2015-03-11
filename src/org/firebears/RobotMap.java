package org.firebears;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {

	public static final boolean DEBUG = true;

	public static final String LIFT_ZERO_REF = "LIFT_ZERO_REF";
	public static final String LIFT_TOTE_0 = "LIFT_TOTE_0";
	public static final String LIFT_TOTE_1 = "LIFT_TOTE_1";
	public static final String LIFT_TOTE_2 = "LIFT_TOTE_2";
	public static final String LIFT_TOTE_3 = "LIFT_TOTE_3";
	public static final String LIFT_TOTE_HIGH = "LIFT_TOTE_HIGH";
	public static final String CHASSIS_DRIVE_TYPE_TAL = "CHASSIS_DRIVE_TYPE_TAL";
	public static final String CHASSIS_DRIVE_TYPE_JAG = "CHASSIS_DRIVE_TYPE_JAG";
	public static final String CHASSIS_FIELD_ORIENTED = "CHASSIS_FIELD_ORIENTED";
	public static final String CHASSIS_BRAKING_MODE = "CHASSIS_BRAKING_MODE";

	public static double lift_zero_ref;
	public static double lift_tote_0;
	public static double lift_tote_1;
	public static double lift_tote_2;
	public static double lift_tote_3;
	public static double lift_tote_high;
	public static boolean chassis_drive_type_tal;

	// Talon Code

	public static SpeedController chassis_front_left_controller;
	public static SpeedController chassis_back_left_controller;
	public static SpeedController chassis_front_right_controller;
	public static SpeedController chassis_back_right_controller;

	// public static Encoder chassis_front_left_encoder;
	// public static Encoder chassis_back_left_encoder;
	// public static Encoder chassis_front_right_encoder;
	// public static Encoder chassis_back_right_encoder;

	public static RobotDrive chassis_robot_drive;
	public static Gyro chassis_drive_gyro;
	public static AnalogPotentiometer liftpot;
	public static SpeedController liftJag;
	public static Compressor grabbercompressor;
	public static DigitalInput autoSelect1;
	public static DigitalInput autoSelect2;
	public static DigitalInput autoSelect3;
	public static DigitalInput autoSelect4;
	public static DigitalInput autoSelect5;
	public static DigitalInput autoSelect6;
	public static DigitalInput autoSelect7;
	public static DigitalInput scoringPlatformSensor;

	public static AnalogInput analogInput;

	public static Solenoid grabbersolenoid_right_open;
	public static Solenoid grabbersolenoid_right_close;
	public static Solenoid grabbersolenoid_left_open;
	public static Solenoid grabbersolenoid_left_close;

	public static org.firebears.sensors.sharpIRRange leftsharpIRRange;
	public static org.firebears.sensors.sharpIRRange leftArmsharpIRRange;
	public static org.firebears.sensors.sharpIRRange rightsharpIRRange;
	public static org.firebears.sensors.sharpIRRange rightArmsharpIRRange;

	public static BuiltInAccelerometer accelerometer;

	public static void init() {

		Preferences preferences = Preferences.getInstance();

		lift_zero_ref = preferences.getDouble(LIFT_ZERO_REF, 0.0);
		lift_tote_0 = preferences.getDouble(LIFT_TOTE_0, 0.5);
		lift_tote_1 = preferences.getDouble(LIFT_TOTE_1, 12.0);
		lift_tote_2 = preferences.getDouble(LIFT_TOTE_2, 23.3);
		lift_tote_3 = preferences.getDouble(LIFT_TOTE_3, 35.5);
		lift_tote_high = preferences.getDouble(LIFT_TOTE_HIGH, 46.5);
		chassis_drive_type_tal = preferences.getBoolean(CHASSIS_DRIVE_TYPE_TAL,
				true);
		if (!preferences.containsKey(CHASSIS_FIELD_ORIENTED)) {
			preferences.putBoolean(CHASSIS_FIELD_ORIENTED, true);
		}
		if (!preferences.containsKey(CHASSIS_BRAKING_MODE)) {
			preferences.putBoolean(CHASSIS_BRAKING_MODE, true);
		}

		// Talon Code
		boolean isBrakingOn = preferences.getBoolean(CHASSIS_BRAKING_MODE, true);
		if (chassis_drive_type_tal) {
			System.out.println("Configuring RobotDrive for CANTalons");
			chassis_front_left_controller = new CANTalon(5);
			chassis_front_right_controller = new CANTalon(1);
			chassis_back_left_controller = new CANTalon(4);
			chassis_back_right_controller = new CANTalon(3);
			if (isBrakingOn) {
				((CANTalon)chassis_front_left_controller).enableBrakeMode(isBrakingOn);
				((CANTalon)chassis_front_right_controller).enableBrakeMode(isBrakingOn);
				((CANTalon)chassis_back_left_controller).enableBrakeMode(isBrakingOn);
				((CANTalon)chassis_back_right_controller).enableBrakeMode(isBrakingOn);
			}

		} else {
			// Jag Code
			System.out.println("Configuring RobotDrive for CANJaguars");
			try {
				chassis_front_left_controller = new CANJaguar(5);
				LiveWindow.addActuator("chassis", "frontleft",
						(CANJaguar) chassis_front_left_controller);
			} catch (Exception e) {
				System.err.println("Failed to load Jag 5");
				e.printStackTrace();
			}
			try {
				chassis_front_right_controller = new CANJaguar(3);
				LiveWindow.addActuator("chassis", "frontright",
						(CANJaguar) chassis_front_right_controller);
			} catch (Exception e) {
				System.err.println("Failed to load Jag 3");
				e.printStackTrace();
			}
			try {
				chassis_back_left_controller = new CANJaguar(4);
				LiveWindow.addActuator("chassis", "backleft",
						(CANJaguar) chassis_back_left_controller);
			} catch (Exception e) {
				System.err.println("Failed to load Jag 4");
				e.printStackTrace();
			}
			try {
				chassis_back_right_controller = new CANJaguar(6);
				LiveWindow.addActuator("chassis", "backright",
						(CANJaguar) chassis_back_right_controller);
			} catch (Exception e) {
				System.err.println("Failed to load Jag 6");
				e.printStackTrace();
			}
		}
		// chassis_robot_drive = new RobotDrive(chassis_front_left_controller,
		// chassis_back_left_controller, chassis_front_right_controller,
		// chassis_back_right_controller);

		/*
		 * chassis_front_left_encoder = new Encoder(2, 3, false,
		 * EncodingType.k4X);
		 * chassis_front_left_encoder.setDistancePerPulse(1.0);
		 * chassis_front_left_encoder
		 * .setPIDSourceParameter(PIDSourceParameter.kRate);
		 * 
		 * chassis_back_left_encoder = new Encoder(6, 7, false,
		 * EncodingType.k4X);
		 * chassis_back_left_encoder.setDistancePerPulse(1.0);
		 * chassis_back_left_encoder
		 * .setPIDSourceParameter(PIDSourceParameter.kRate);
		 * 
		 * chassis_front_right_encoder = new Encoder(0, 1, false,
		 * EncodingType.k4X);
		 * chassis_front_right_encoder.setDistancePerPulse(1.0);
		 * chassis_front_right_encoder
		 * .setPIDSourceParameter(PIDSourceParameter.kRate);
		 * 
		 * chassis_back_right_encoder = new Encoder(4, 5, false,
		 * EncodingType.k4X);
		 * chassis_back_right_encoder.setDistancePerPulse(1.0);
		 * chassis_back_right_encoder
		 * .setPIDSourceParameter(PIDSourceParameter.kRate);
		 */
		if (chassis_front_left_controller != null
				|| chassis_front_left_controller != null
				|| chassis_front_left_controller != null
				|| chassis_front_left_controller != null) {
			chassis_robot_drive = new RobotDrive(chassis_front_left_controller,
					chassis_back_left_controller,
					chassis_front_right_controller,
					chassis_back_right_controller);
			// PID Robot Drive: warning DANGEROUS
			/*
			 * chassis_robot_drive = new PIDRobotDrive(
			 * chassis_front_left_controller, chassis_back_left_controller,
			 * chassis_front_right_controller, chassis_back_right_controller,
			 * chassis_front_left_encoder, chassis_back_left_encoder,
			 * chassis_front_right_encoder,chassis_back_right_encoder, 1.0);
			 */

			chassis_robot_drive.setInvertedMotor(
					RobotDrive.MotorType.kFrontRight, true);
			chassis_robot_drive.setInvertedMotor(
					RobotDrive.MotorType.kFrontLeft, false);
			chassis_robot_drive.setInvertedMotor(
					RobotDrive.MotorType.kRearRight, true);
			chassis_robot_drive.setInvertedMotor(
					RobotDrive.MotorType.kRearLeft, false);

			chassis_robot_drive.setSafetyEnabled(true);
			chassis_robot_drive.setExpiration(0.1);
			chassis_robot_drive.setSensitivity(0.5);
			chassis_robot_drive.setMaxOutput(1.0);
		}

		chassis_drive_gyro = new Gyro(0);
		LiveWindow.addSensor("Chassis", "drive_gyro", chassis_drive_gyro);
		chassis_drive_gyro.setSensitivity(0.007);
		// chassis_drive_gyro.initGyro();
		if (chassis_drive_gyro != null) {
			chassis_drive_gyro.reset();
			LiveWindow.addSensor("Chassis", "drive_gyro", chassis_drive_gyro);
		}

		analogInput = new AnalogInput(6);
		analogInput.setAverageBits(4);

		liftpot = new AnalogPotentiometer(analogInput, 1.0, 0.0);
		LiveWindow.addSensor("Lift", "pot", liftpot);

		try {
			liftJag = new CANJaguar(2);
			LiveWindow.addActuator("lift", "lift", (CANJaguar) liftJag);
		} catch (Exception e) {
			System.err.println("Failed to load Jag 2");
			e.printStackTrace();
		}

		grabbercompressor = new Compressor(0);

		grabbersolenoid_left_open = new Solenoid(1);
		LiveWindow.addActuator("Grabber", "solenoid_left_open",
				grabbersolenoid_left_open);

		grabbersolenoid_left_close = new Solenoid(0);
		LiveWindow.addActuator("Grabber", "solenoid_left_close",
				grabbersolenoid_left_close);

		grabbersolenoid_right_open = new Solenoid(3);
		LiveWindow.addActuator("Grabber", "solenoid_right_open",
				grabbersolenoid_right_open);

		grabbersolenoid_right_close = new Solenoid(2);
		LiveWindow.addActuator("Grabber", "solenoid_right_close",
				grabbersolenoid_right_close);

		accelerometer = new BuiltInAccelerometer();
		LiveWindow.addSensor("Accelerometer", "accelerometer", accelerometer);
		scoringPlatformSensor = new DigitalInput(8);

		autoSelect1 = new DigitalInput(11);
		autoSelect2 = new DigitalInput(12);
		autoSelect3 = new DigitalInput(13);
		autoSelect4 = new DigitalInput(14);
		autoSelect5 = new DigitalInput(15);
		autoSelect6 = new DigitalInput(16);
		autoSelect7 = new DigitalInput(17);

		rightArmsharpIRRange = new org.firebears.sensors.sharpIRRange(5);
		rightsharpIRRange = new org.firebears.sensors.sharpIRRange(3);
		leftsharpIRRange = new org.firebears.sensors.sharpIRRange(1);
		leftArmsharpIRRange = new org.firebears.sensors.sharpIRRange(4);

	}
}
