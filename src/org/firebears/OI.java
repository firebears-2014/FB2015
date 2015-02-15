package org.firebears;

import org.firebears.commands.*;
import org.firebears.commands.drive.DriveCommand;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.grabber.*;
import org.firebears.commands.lift.SetHeightCommand;
import org.firebears.commands.lift.SetLiftMotor;
import org.firebears.commands.lift.SetStep;
import org.firebears.commands.lights.CelebrateCommand;
import org.firebears.commands.lights.LightChangeCommand;
import org.firebears.commands.drive.*;
import org.firebears.commands.lights.*;
import org.firebears.sensors.GyroResetCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public Joystick joystickDrive;
	public Joystick joystickLift;
	public DigitalInput ContainerSensor;
	// only three, unless we get a new rotary switch
	// public DigitalInput autoSelect4;

	public JoystickButton setLiftPickup;
	public JoystickButton setLiftTote0;
	public JoystickButton setLiftTote1;
	public JoystickButton setLiftTote2;
	public JoystickButton setLiftTote3;
	public JoystickButton openGrabbers;
	public JoystickButton closeGrabbers;
	public JoystickButton toggleStepSwitch;
	public JoystickButton toggleAutomatedSwitch;
	public JoystickButton wideCentertote;
	public JoystickButton centerTote;
	public JoystickButton resetGyro;
	public JoystickButton approachTote;
	public JoystickButton slowTriggerButton;
	public JoystickButton celebrate;

	public OI() {
		// Initialize joysticks
		joystickDrive = new Joystick(0);
		joystickLift = new Joystick(1);

		// (new JoystickButton(joystickDrive, 3)).whileHeld(new
		// ForwardCommand(0.5));
		// (new JoystickButton(joystickDrive, 5)).whileHeld(new
		// ForwardCommand(-0.5));

		// LIFT Joystick Initialization

		setLiftPickup = new JoystickButton(joystickLift, 1);
		setLiftPickup.whenPressed(new SetHeightCommand(
				Robot.lift.LIFT_PICKUP_HEIGHT));

		setLiftTote0 = new JoystickButton(joystickLift, 2);
		setLiftTote0
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));

		setLiftTote1 = new JoystickButton(joystickLift, 3);
		setLiftTote1
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));

		setLiftTote2 = new JoystickButton(joystickLift, 4);
		setLiftTote2
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT));

		setLiftTote3 = new JoystickButton(joystickLift, 5);
		setLiftTote3
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));

		openGrabbers = new JoystickButton(joystickLift, 6);
		openGrabbers.whenPressed(new GrabberCommand(true));

		closeGrabbers = new JoystickButton(joystickLift, 7);
		closeGrabbers.whenPressed(new GrabberCommand(false));

		toggleStepSwitch = new JoystickButton(joystickLift, 8);
		toggleStepSwitch.whenPressed(new SetStep(false));
		toggleStepSwitch.whenReleased(new SetStep(true));

		celebrate = new JoystickButton(joystickLift, 10);
		celebrate.whileHeld(new CelebrateCommand());

		// DRIVE Joystick Initialization

		slowTriggerButton = new JoystickButton(joystickDrive, 1);

		resetGyro = new JoystickButton(joystickDrive, 7);
		resetGyro.whenPressed(new GyroResetCommand());

		approachTote = new JoystickButton(joystickDrive, 9);
		approachTote.whenPressed(new ToteApproachCommand());

		wideCentertote = new JoystickButton(joystickDrive, 11);
		wideCentertote.whenPressed(new WidetoteCommand());

		centerTote = new JoystickButton(joystickDrive, 12);
		centerTote.whenPressed(new Totecenter());

		// SmartDashboard Buttons
		// SmartDashboard.putData("Autonomous Command", new
		// AutonomousCommand());

		if (RobotMap.DEBUG) {
			SmartDashboard.putData("lift to 0", new SetHeightCommand(
					Robot.lift.LIFT_0_HEIGHT));
			SmartDashboard.putData("lift to 1", new SetHeightCommand(
					Robot.lift.LIFT_1_HEIGHT));
			SmartDashboard.putData("lift to 2", new SetHeightCommand(
					Robot.lift.LIFT_2_HEIGHT));
			SmartDashboard.putData("lift to 3", new SetHeightCommand(
					Robot.lift.LIFT_3_HEIGHT));

			SmartDashboard.putData("Run Talon Code", new PreferenceSetup(
					RobotMap.CHASSIS_DRIVE_TYPE_TAL));
			SmartDashboard.putData("Run Jag Code", new PreferenceSetup(
					RobotMap.CHASSIS_DRIVE_TYPE_JAG));

			if (Robot.lift != null)
				SmartDashboard
						.putBoolean("Lift Motor", Robot.lift.enable_motor);

			SmartDashboard.putData("Enable Lift Motor", new SetLiftMotor(true));
			SmartDashboard.putData("Disable Lift Motor",
					new SetLiftMotor(false));

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

			// SmartDashboard.putData("Change Lights", new LightChangeCommand(0,
			// Robot.lights.RANDOM_ANIM));
		}
	}

	public Joystick getJoystickZero() {
		return joystickDrive;
	}

	public Joystick getJoystickLift() {
		return joystickLift;
	}
}
