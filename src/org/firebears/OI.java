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
	public Joystick joystick;
	public DigitalInput ContainerSensor;

	public JoystickButton setLiftTote0;
	public JoystickButton setLiftTote1;
	public JoystickButton setLiftTote2;
	public JoystickButton setLiftTote3;
	public JoystickButton setLiftTote4;
	public JoystickButton openGrabbers;
	public JoystickButton closeGrabbers;
	public JoystickButton stepOn;
	public JoystickButton stepOff;
	public JoystickButton manualUp;
	public JoystickButton manualDown;
//	public JoystickButton wideCentertote;
//	public JoystickButton centerTote;
//	public JoystickButton resetGyro;
//	public JoystickButton approachTote;
//	public JoystickButton slowTriggerButton;
	public JoystickButton celebrate;
//	public JoystickButton strafe;
//	public JoystickButton strafe2;

	public JoystickButton strafeLeft;
	public JoystickButton strafeRight;

	// Configure Controls
	// http://tinyurl.com/ky34flv
	private final int TOTE_0 = 11;
	private final int TOTE_1 = 9;
	private final int TOTE_2 = 7;
	private final int TOTE_3 = 12;
	private final int TOTE_4 = 10;
	private final int GRABBER_OPEN = 5;
	private final int GRABBER_CLOSE = 3;
	private final int CELEBRATE = 8;
	private final int MANUAL_UP = 6;
	private final int MANUAL_DOWN = 4;
	private final int STEP_ON = 2;
	private final int STEP_OFF = 1;
	
	public OI() {
		// Initialize joystick.
		joystick = new Joystick(0);

		setLiftTote0 = new JoystickButton(joystick, TOTE_0);
		setLiftTote0.whenPressed(new SetHeightCommand(Robot.lift.HEIGHT_0));

		setLiftTote1 = new JoystickButton(joystick, TOTE_1);
		setLiftTote1.whenPressed(new SetHeightCommand(Robot.lift.HEIGHT_1));

		setLiftTote2 = new JoystickButton(joystick, TOTE_2);
		setLiftTote2.whenPressed(new SetHeightCommand(Robot.lift.HEIGHT_2));

		setLiftTote3 = new JoystickButton(joystick, TOTE_3);
		setLiftTote3.whenPressed(new SetHeightCommand(Robot.lift.HEIGHT_3));

		setLiftTote4 = new JoystickButton(joystick, TOTE_4);
		setLiftTote4.whenPressed(new SetHeightCommand(Robot.lift.HEIGHT_4));

		openGrabbers = new JoystickButton(joystick, GRABBER_OPEN);
		openGrabbers.whenPressed(new GrabberCommand(true));

		closeGrabbers = new JoystickButton(joystick, GRABBER_CLOSE);
		closeGrabbers.whenPressed(new GrabberCommand(false));

		stepOn = new JoystickButton(joystick, STEP_ON);
		stepOn.whenPressed(new SetStepCommand(true));
		
		stepOff = new JoystickButton(joystick, STEP_OFF);
		stepOff.whenPressed(new SetStepCommand(false));

		manualUp = new JoystickButton(joystick, MANUAL_UP);
		manualUp.whileHeld(new SetManualCommand(true));
		
		manualDown = new JoystickButton(joystick, MANUAL_DOWN);
		manualDown.whileHeld(new SetManualCommand(false));

		celebrate = new JoystickButton(joystick, CELEBRATE);
		celebrate.whileHeld(new CelebrateCommand());

		// DRIVE Joystick Initialization

//		slowTriggerButton = new JoystickButton(joystick, 1);

//		strafeLeft = new JoystickButton(joystick, 5);
//		strafeLeft.whileHeld(new StrafeCommand(-0.75));

//		strafeRight = new JoystickButton(joystick, 6);
//		strafeRight.whileHeld(new StrafeCommand(0.75));

//		resetGyro = new JoystickButton(joystick, 7);
//		resetGyro.whenPressed(new GyroResetCommand());

//		approachTote = new JoystickButton(joystick, 9);
//		approachTote.whenPressed(new ToteApproachCommand());

//		wideCentertote = new JoystickButton(joystick, 11);
//		wideCentertote.whenPressed(new WidetoteCommand());

//		centerTote = new JoystickButton(joystick, 12);
//		centerTote.whenPressed(new Totecenter());
		
/*		strafe = new JoystickButton(joystickDrive, 3);
		strafe.whileHeld(new StrafeCommand(.75));

		strafe2 = new JoystickButton(joystickDrive, 4);
		strafe2.whileHeld(new StrafeCommand(-.75, 0.));*/
		
		// SmartDashboard Buttons
		// SmartDashboard.putData("Autonomous Command", new
		// AutonomousCommand());

		if (RobotMap.DEBUG) {
			SmartDashboard.putData("lift to 0", new SetHeightCommand(
					Robot.lift.HEIGHT_0));
			SmartDashboard.putData("lift to 1", new SetHeightCommand(
					Robot.lift.HEIGHT_1));
			SmartDashboard.putData("lift to 2", new SetHeightCommand(
					Robot.lift.HEIGHT_2));
			SmartDashboard.putData("lift to 3", new SetHeightCommand(
					Robot.lift.HEIGHT_3));
			SmartDashboard.putData("lift to high", new SetHeightCommand(
					Robot.lift.HEIGHT_4));

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

	public Joystick getJoystick() {
		return joystick;
	}
}
