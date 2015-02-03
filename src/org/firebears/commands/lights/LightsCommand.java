package org.firebears.commands.lights;

import edu.wpi.first.wpilibj.command.Command;

import org.firebears.Robot;
import org.firebears.subsystems.Lights;

/**
 * Command For Updating Lights' animations
 */
public class  LightsCommand extends Command {

    public LightsCommand() {
        requires(Robot.lights);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.lights.setLiftHeight(Lights.LIFT_LIGHTS_LEFT, Robot.lift.getLiftHeight());
    	//Robot.lights.setLiftHeight(Lights.LIFT_LIGHTS_RIGHT, Robot.lift.getLiftHeight());
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
