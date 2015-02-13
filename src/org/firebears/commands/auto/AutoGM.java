package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.grabber.GrabberCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * Autonomous command currently does nothing, will do ____
 */
public class  AutoGM extends CommandGroup {

    public AutoGM() {
    	addSequential(new GrabberCommand(true));
    	//ToDo: add in a way to stop movement depending on where the robot is
    	addSequential(new ForwardCommand(-1));
    	System.out.println("Currently in autonomous 'AutoGM'");
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
      	setTimeout(2);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
