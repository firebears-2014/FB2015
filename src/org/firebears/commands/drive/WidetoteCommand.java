package org.firebears.commands.drive;
// Made by Jacob Wiggins

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WidetoteCommand extends Command {

    public WidetoteCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.chassis.leftsharpIRRange.getRangefinderDistance() > 20 && Robot.chassis.rightsharpIRRange.getRangefinderDistance() > 20) {
    		Robot.chassis.mechanumDrive(0,0.1,0);
    	}
    	else if (Robot.chassis.leftsharpIRRange.getRangefinderDistance() > 20) {
    		Robot.chassis.mechanumDrive(0, 0,.1);
    	}
    	else if (Robot.chassis.rightsharpIRRange.getRangefinderDistance() > 20) {
    		Robot.chassis.mechanumDrive(0,0,-.1);
    	}
    	else if (Robot.chassis.leftArmsharpIRRange.getRangefinderDistance() > 20) {
    		Robot.chassis.mechanumDrive(.1, 0, 0);
    	}
    	else if (Robot.chassis.rightArmsharpIRRange.getRangefinderDistance() > 20) {
    		Robot.chassis.mechanumDrive(-.1, 0, 0);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.chassis.leftsharpIRRange.getRangefinderDistance() < 20 && Robot.chassis.rightsharpIRRange.getRangefinderDistance() < 20 && Robot.chassis.leftArmsharpIRRange.getRangefinderDistance() < 20 && Robot.chassis.rightArmsharpIRRange.getRangefinderDistance() < 20);
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.chassis.mechanumDrive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
