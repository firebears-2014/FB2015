package org.firebears.commands.auto;

import org.firebears.commands.drive.ForwardCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Autonomous command currently does nothing, will do ____
 */
public class  AutoM extends CommandGroup {

    public AutoM() {
    	//ToDo: add in a way to stop movement depending on where the robot is
    	addSequential(new ForwardCommand(-1));
    	System.out.println("Currently in autonomous 'AutoM'");
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
      	setTimeout(1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
