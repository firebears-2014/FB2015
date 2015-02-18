package org.firebears.commands.drive;

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Totecenter extends Command {

	public boolean fieldOriented;
	
    public Totecenter() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
		setTimeout(3);
    }

    // Called repeatedly when this Command is scheduled to run  distance in inches
    protected void execute() {

    	if (Robot.chassis.leftsharpIRRange.getRangefinderDistance() > 8 && Robot.chassis.rightsharpIRRange.getRangefinderDistance() > 8) {
    		Robot.chassis.mechanumDrive(0,.1,0);

    	}
    	else if (Robot.chassis.leftsharpIRRange.getRangefinderDistance() > 8) {
    		Robot.chassis.mechanumDrive(0, 0,.1);
    	}
    	else if (Robot.chassis.rightsharpIRRange.getRangefinderDistance() > 8) {
    		Robot.chassis.mechanumDrive(0,0,-.1);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.chassis.leftsharpIRRange.getRangefinderDistance() < 8 && Robot.chassis.rightsharpIRRange.getRangefinderDistance() < 8);
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
		Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }
}
