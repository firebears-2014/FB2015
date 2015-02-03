package org.firebears;

import org.firebears.commands.*;
import org.firebears.commands.drive.DriveCommand;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.grabber.*;
import org.firebears.commands.lift.SetHeightCommand;
import org.firebears.commands.lift.SetLiftMotor;
import org.firebears.commands.lift.SetStep;
import org.firebears.commands.lights.LightChangeCommand;
import org.firebears.commands.drive.*;
import org.firebears.commands.lights.*;

import PreferenceSetup.PreferenceSetup;
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
	public DigitalInput scoringPlatformSensor;
	public DigitalInput ContainerSensor;
	public DigitalInput autoSelect1;
	public DigitalInput autoSelect2;
	public DigitalInput autoSelect3;
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

	public OI() {
		joystickDrive = new Joystick(0);
		joystickLift = new Joystick(1);

//		scoringPlatformSensor = new DigitalInput(0);
//		autoSelect1 = new DigitalInput(1);
//		autoSelect2 = new DigitalInput(2);
//		autoSelect3 = new DigitalInput(3);

//		(new JoystickButton(joystickDrive, 3)).whileHeld(new ForwardCommand(0.5));
//		(new JoystickButton(joystickDrive, 5)).whileHeld(new ForwardCommand(-0.5));
		
		// start of final joystick buttons

		setLiftPickup = new JoystickButton(joystickLift, 1);
		setLiftPickup.whenPressed(new SetHeightCommand("Lift_Pickup"));

		setLiftTote0 = new JoystickButton(joystickLift, 2);
		setLiftTote0.whenPressed(new SetHeightCommand("Lift_Tote_0"));

		setLiftTote1 = new JoystickButton(joystickLift, 3);
		setLiftTote1.whenPressed(new SetHeightCommand("Lift_Tote_1"));

		setLiftTote2 = new JoystickButton(joystickLift, 4);
		setLiftTote2.whenPressed(new SetHeightCommand("Lift_Tote_2"));

		setLiftTote3 = new JoystickButton(joystickLift, 5);
		setLiftTote3.whenPressed(new SetHeightCommand("Lift_Tote_3"));

		openGrabbers = new JoystickButton(joystickLift, 6);
		openGrabbers.whenPressed(new GrabberCommand(true));

		closeGrabbers = new JoystickButton(joystickLift, 7);
		closeGrabbers.whenPressed(new GrabberCommand(false));

		toggleStepSwitch = new JoystickButton(joystickLift, 8);
		toggleStepSwitch.whileHeld(new SetStep());
		
		wideCentertote = new JoystickButton(joystickDrive, 11);
		wideCentertote.whenPressed(new WidetoteCommand());
		
		centerTote = new JoystickButton(joystickDrive, 12);
		centerTote.whenPressed(new Totecenter());

		// SmartDashboard Buttons
		// SmartDashboard.putData("Autonomous Command", new
		// AutonomousCommand());

		SmartDashboard.putData("Run Talon Code", new PreferenceSetup(
				RobotMap.CHASSIS_DRIVE_TYPE_TAL));
		SmartDashboard.putData("Run Jag Code", new PreferenceSetup(
				RobotMap.CHASSIS_DRIVE_TYPE_JAG));

		if (Robot.lift!=null) SmartDashboard.putBoolean("Lift Motor", Robot.lift.enable_motor);

		SmartDashboard.putData("Enable Lift Motor", new SetLiftMotor(true));
		SmartDashboard.putData("Disable Lift Motor", new SetLiftMotor(false));

		SmartDashboard.putData("Set Zero", new PreferenceSetup(
				RobotMap.LIFT_ZERO_REF));
		SmartDashboard.putData("Set Tote Zero/Pickup", new PreferenceSetup(
				RobotMap.LIFT_TOTE_PICKUP));
		SmartDashboard.putData("Set Tote One", new PreferenceSetup(
				RobotMap.LIFT_TOTE_1));
		SmartDashboard.putData("Set Tote Two", new PreferenceSetup(
				RobotMap.LIFT_TOTE_2));
		SmartDashboard.putData("Set Tote Three", new PreferenceSetup(
				RobotMap.LIFT_TOTE_3));

		SmartDashboard.putData("Change Lights", new LightChangeCommand(0,
				Robot.lights.RANDOM_ANIM));

	}

	public Joystick getJoystickZero() {
		return joystickDrive;
	}

	public Joystick getJoystickLift() {
		return joystickLift;
	}
}
