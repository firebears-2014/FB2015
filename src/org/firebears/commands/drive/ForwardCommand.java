<<<<<<< HEAD:src/org/firebears/commands/ForwardCommand.java

package org.firebears.commands;
=======
package org.firebears.commands.drive;
>>>>>>> ac7c5479615c893bbbf36f07a1c6def80f65fb1d:src/org/firebears/commands/drive/ForwardCommand.java

import org.firebears.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command drives forward at a fixed speed until interupted
 */
public class ForwardCommand extends Command {

	double speed;
	
    public ForwardCommand(double s) {
    	requires(Robot.chassis);
    	speed=s;
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    	Robot.chassis.mechanumDrive(0, speed, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.chassis.mechanumDrive(0, speed, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return speed == 0.0;
    }

    // Called once after isFinished returns true
    protected void end() {

    	Robot.chassis.mechanumDrive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {

    	Robot.chassis.mechanumDrive(0, 0, 0);
    }
}
