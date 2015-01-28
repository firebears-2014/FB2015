// RobotBuilder Version: 1.5
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.firebears.subsystems;

import org.firebears.Robot;
import org.firebears.RobotMap;
import org.firebears.commands.*;
import org.firebears.commands.drive.DriveCommand;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Chassis extends Subsystem {
	
	public Chassis() {
		drive_gyro.reset();
	}
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	
	//Talon Code
	/*
	CANTalon front_left = RobotMap.chassis_front_left;
	CANTalon back_left = RobotMap.chassis_back_left;
	CANTalon front_right = RobotMap.chassis_front_right;
	CANTalon back_right = RobotMap.chassis_back_right;
	*/
	
	//Jag Code
//	/*
	CANJaguar front_left = RobotMap.chassis_front_left;
	CANJaguar back_left = RobotMap.chassis_back_left;
	CANJaguar front_right = RobotMap.chassis_front_right;
	CANJaguar back_right = RobotMap.chassis_back_right;
//	*/
	
	RobotDrive robot_drive = RobotMap.chassis_robot_drive;
	Gyro drive_gyro = RobotMap.chassis_drive_gyro;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	/**
	 * Drive the chassis mecanum wheels.
	 * 
	 * @param strafe x dimension speed, in the range -1.0 to 1.0.
	 * @param forward y dimension movement, in the range -1.0 to 1.0.
	 * @param rotation angular rotation, in the range -1.0 to 1.0.
	 */
	public void mechanumDrive(double strafe, double forward, double rotation) {
    	double theta = RobotMap.chassis_drive_gyro.getAngle();
    	double angle = 0;
		SmartDashboard.putNumber("gyro value", theta);
    	
//        double cosA = Math.cos(theta * (3.14159 / 180.0));
//        double sinA = Math.sin(theta * (3.14159 / 180.0));
//        strafe = strafe * cosA - forward * sinA;
//        forward = strafe * sinA + forward * cosA;
    	
//    	front_left.set(strafe + forward + rotation);
//    	front_right.set((strafe + forward - rotation));
//    	back_left.set(strafe - forward + rotation);
//    	back_right.set((strafe - forward - rotation ));

		robot_drive.mecanumDrive_Cartesian(strafe, forward, rotation, angle);
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
		setDefaultCommand(new DriveCommand());
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
