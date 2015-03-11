package org.firebears.subsystems;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.commands.PreferenceSetup;
import org.firebears.sensors.HeightSensor;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Subsystem for the lift ( elevator )
 */
public class Lift extends PIDSubsystem {
	public HeightSensor heightSensor;

	public final String LIFT_0_HEIGHT = "LIFT_0_HEIGHT";
	public final String LIFT_1_HEIGHT = "LIFT_1_HEIGHT";
	public final String LIFT_2_HEIGHT = "LIFT_2_HEIGHT";
	public final String LIFT_3_HEIGHT = "LIFT_3_HEIGHT";
	public final String LIFT_HIGH_HEIGHT = "LIFT_HIGH_HEIGHT";

	SpeedController liftJag = RobotMap.liftJag;

	public double addStep = 0;

	public boolean manual_mode = false;
	public boolean enable_motor = true;

	public boolean manual_up = false;
	public boolean manual_down = false;

	private static double m_P = 0.12;
	private static double m_I = 0.0;
	private static double m_D = 0.0;

	public double lift_output;

	// Initialize your subsystem here
	public Lift() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
		super("Lift", m_P, m_I, m_D);
		setAbsoluteTolerance(0.2);
		getPIDController().setContinuous(false);
		LiveWindow.addActuator("Lift", "PIDSubsystem Controller",
				getPIDController());
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=PID
		heightSensor = new HeightSensor(RobotMap.liftpot);
		getPIDController().enable();

		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.

		new PreferenceSetup(RobotMap.LIFT_ZERO_REF);

	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;

		// returns inches
		return heightSensor.getHeight();
	}

	// in inches
	public double getLiftHeight() {
		return heightSensor.getHeight();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);

		// if statement to allow turning off of lift motor in smartdashboard

		lift_output = output;
		if (enable_motor) {
			if (liftJag != null)
				liftJag.pidWrite(-1 * output);
		}
	}

	public void setSetpointInches(String setpoint) {
		if (manual_mode == false) {
			// set pid to pickup height
			// set to inches above "zero height"
			boolean picking_up = Robot.grabber.isOpen();
			// as opposed to droping
			if (picking_up) {
				// addStep will be 0 or 8, depending on if the switch is
				// flicked
				if (setpoint.equals(LIFT_0_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_0 + addStep);
				} else if (setpoint.equals(LIFT_1_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_1 + addStep);
				} else if (setpoint.equals(LIFT_2_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_2 + addStep);
				} else if (setpoint.equals(LIFT_3_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_3 + addStep);
				} else if (setpoint.equals(LIFT_HIGH_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_high + addStep);
				}
			} else {
				if (setpoint.equals(LIFT_0_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_0 + addStep + 3);
				} else if (setpoint.equals(LIFT_1_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_1 + addStep + 3);
				} else if (setpoint.equals(LIFT_2_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_2 + addStep + 3);
				} else if (setpoint.equals(LIFT_3_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_3 + addStep + 3);
				} else if (setpoint.equals(LIFT_HIGH_HEIGHT)) {
					setSetpoint(RobotMap.lift_tote_high + addStep + 3);
				}
			}
		} else {
			System.out.println("manual mode");
			if (setpoint.equals(LIFT_0_HEIGHT)) {
				manual_down = true;
				manual_up = false;
			} else if (setpoint.equals(LIFT_1_HEIGHT)) {
				manual_down = false;
				manual_up = true;
			}
		}
	}

	public void setMotor(boolean enable) {
		// switches motor to be off if on, or on if off
		enable_motor = enable;
	}
}
