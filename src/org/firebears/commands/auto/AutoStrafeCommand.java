package org.firebears.commands.auto;

import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command:
 * 	moves sideways, forward then back.
 */
public class  AutoStrafeCommand extends CommandGroup {

    public AutoStrafeCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//1 second, full speed
    	addSequential(new StrafeCommand(-.75), 1.0);
    	addSequential(new ForwardCommand(.5), 1.0);
    	addSequential(new StrafeCommand(.75), 1.0);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
