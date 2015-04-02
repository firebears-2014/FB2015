package org.firebears;

import org.firebears.commands.*;
import org.firebears.commands.grabber.*;
import org.firebears.commands.lift.SetHeightCommand;
import org.firebears.commands.lift.SetLiftMotorCommand;
import org.firebears.commands.lift.SetManualCommand;
import org.firebears.commands.lift.SetStepCommand;
import org.firebears.commands.lights.CelebrateCommand;
import org.firebears.commands.lights.LightChangeCommand;
import org.firebears.commands.drive.*;
import org.firebears.sensors.GyroResetCommand;
import org.firebears.subsystems.Lights;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * ping roborio-2846-local
 */
public class OI {

	public Joystick joystickDrive;
	public Joystick joystickLift;
	public DigitalInput ContainerSensor;

	public JoystickButton setLiftTote0;
	public JoystickButton setLiftTote1;
	public JoystickButton setLiftTote2;
	public JoystickButton setLiftTote3;
	public JoystickButton setLiftToteHigh;
	public JoystickButton openGrabbers;
	public JoystickButton closeGrabbers;
	public JoystickButton toggleStepSwitch;
	public JoystickButton toggleManualSwitch;
	public JoystickButton wideCentertote;
	public JoystickButton centerTote;
	public JoystickButton resetGyro;
	public JoystickButton approachTote;
	public JoystickButton slowTriggerButton;
	public JoystickButton celebrate;
	public JoystickButton strafe;
	public JoystickButton strafe2;

	public JoystickButton strafeLeft;
	public JoystickButton strafeRight;

	public OI() {
		// Initialize joysticks
		joystickDrive = new Joystick(0);
		joystickLift = new Joystick(1);
		// LIFT Joystick Initialization

		setLiftTote0 = new JoystickButton(joystickLift, 1);
		setLiftTote0
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));

		setLiftTote1 = new JoystickButton(joystickLift, 2);
		setLiftTote1
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));

		setLiftTote2 = new JoystickButton(joystickLift, 3);
		setLiftTote2
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT));

		setLiftTote3 = new JoystickButton(joystickLift, 4);
		setLiftTote3
				.whenPressed(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));

		setLiftToteHigh = new JoystickButton(joystickLift, 5);
		setLiftToteHigh.whenPressed(new SetHeightCommand(
				Robot.lift.LIFT_HIGH_HEIGHT));

		openGrabbers = new JoystickButton(joystickLift, 6);
		openGrabbers.whenPressed(new GrabberCommand(true));

		closeGrabbers = new JoystickButton(joystickLift, 7);
		closeGrabbers.whenPressed(new GrabberCommand(false));

		toggleStepSwitch = new JoystickButton(joystickLift, 8);
		toggleStepSwitch.whenPressed(new SetStepCommand(false));
		toggleStepSwitch.whenReleased(new SetStepCommand(true));

		toggleManualSwitch = new JoystickButton(joystickLift, 9);
		toggleManualSwitch.whenPressed(new SetManualCommand(true));
		toggleManualSwitch.whenReleased(new SetManualCommand(false));

		celebrate = new JoystickButton(joystickLift, 11);
		celebrate.whileHeld(new CelebrateCommand());

		// DRIVE Joystick Initialization

		slowTriggerButton = new JoystickButton(joystickDrive, 1);

		strafeLeft = new JoystickButton(joystickDrive, 5);
		strafeLeft.whileHeld(new StrafeCommand(-0.75));

		strafeRight = new JoystickButton(joystickDrive, 6);
		strafeRight.whileHeld(new StrafeCommand(0.75));

		resetGyro = new JoystickButton(joystickDrive, 7);
		resetGyro.whenPressed(new GyroResetCommand());

		approachTote = new JoystickButton(joystickDrive, 9);
		approachTote.whenPressed(new ToteApproachCommand());

		wideCentertote = new JoystickButton(joystickDrive, 11);
		wideCentertote.whenPressed(new WidetoteCommand());

		centerTote = new JoystickButton(joystickDrive, 12);
		centerTote.whenPressed(new Totecenter());
		
/*		strafe = new JoystickButton(joystickDrive, 3);
		strafe.whileHeld(new StrafeCommand(.75));

		strafe2 = new JoystickButton(joystickDrive, 4);
		strafe2.whileHeld(new StrafeCommand(-.75, 0.));*/
		
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
			SmartDashboard.putData("lift to high", new SetHeightCommand(
					Robot.lift.LIFT_HIGH_HEIGHT));

			SmartDashboard.putData("Run Talon Code", new PreferenceSetup(
					RobotMap.CHASSIS_DRIVE_TYPE_TAL));
			SmartDashboard.putData("Run Jag Code", new PreferenceSetup(
					RobotMap.CHASSIS_DRIVE_TYPE_JAG));

			if (Robot.lift != null)
				SmartDashboard
						.putBoolean("Lift Motor", Robot.lift.enable_motor);

			SmartDashboard.putData("Enable Lift Motor",
					new SetLiftMotorCommand(true));
			SmartDashboard.putData("Disable Lift Motor",
					new SetLiftMotorCommand(false));

			SmartDashboard.putData("Lights lift1 Crazy",
					new LightChangeCommand(Lights.STRIP_LIFT1,
							Lights.ANIM_CRAZY));
			SmartDashboard.putData("Lights lift2 Fire", new LightChangeCommand(
					Lights.STRIP_LIFT2, Lights.ANIM_FIRE));
			SmartDashboard.putData("Lights lift1 Fire", new LightChangeCommand(
					Lights.STRIP_LIFT1, Lights.ANIM_FIRE));
			SmartDashboard.putData("Lights lift2 Crazy",
					new LightChangeCommand(Lights.STRIP_LIFT2,
							Lights.ANIM_CRAZY));

		}
		SmartDashboard.putData("Set Zero", new PreferenceSetup(
				RobotMap.LIFT_ZERO_REF));
		SmartDashboard.putData("Set Tote Zero Pickup", new PreferenceSetup(
				RobotMap.LIFT_TOTE_0));
		SmartDashboard.putData("Set Tote One", new PreferenceSetup(
				RobotMap.LIFT_TOTE_1));
		SmartDashboard.putData("Set Tote Two", new PreferenceSetup(
				RobotMap.LIFT_TOTE_2));
		SmartDashboard.putData("Set Tote Three", new PreferenceSetup(
				RobotMap.LIFT_TOTE_3));
		SmartDashboard.putData("Set Tote High", new PreferenceSetup(
				RobotMap.LIFT_TOTE_HIGH));
		SmartDashboard.putData("Rotate 90", new RotateCommand(.5, 90., true));

	}

	public Joystick getJoystickZero() {
		return joystickDrive;
	}

	public Joystick getJoystickLift() {
		return joystickLift;
	}
}
