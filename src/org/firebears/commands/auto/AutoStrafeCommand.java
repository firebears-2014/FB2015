package org.firebears.commands.auto;

import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command: moves sideways, forward then back.
 */
public class AutoStrafeCommand extends CommandGroup {

	public AutoStrafeCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		System.out.println("Currently in autonomous 'AutoStrafeCommand'");

		addSequential(new StrafeCommand(-.75), 12.0);
		// addSequential(new ForwardCommand(.5, false), 1.0);
		// addSequential(new StrafeCommand(.75), 1.0);

	}

}
