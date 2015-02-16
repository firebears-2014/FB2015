package org.firebears.commands.auto;

import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.drive.Totecenter;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous Command: Stacks up the 3 totes in the auto zone
 * 
 * Dumb stack and move auto, needs interrupts
 */
public class AutoSM extends CommandGroup {

	public AutoSM() {
		// tote 1 logic
		addSequential(new GrabberCommand(true));
		addParallel(new SetHeightCommand("Lift_Tote_2"));
		addParallel(new StrafeCommand(1), 1.0); // change interrupt?
		addParallel(new Totecenter());
		// tote 2 logic
		addSequential(new SetHeightCommand("Lift_Tote_1"));
		addParallel(new GrabberCommand(false));
		addParallel(new SetHeightCommand("Lift_Tote_0"));
		addSequential(new GrabberCommand(true));
		addParallel(new SetHeightCommand("Lift_Tote_2"));
		addParallel(new StrafeCommand(1), 1.0); // change interrupt
		addParallel(new Totecenter());
		// tote 3 logic
		addSequential(new SetHeightCommand("Lift_Tote_1"));
		addParallel(new GrabberCommand(false));
		addParallel(new SetHeightCommand("Lift_Tote_0"));
		addSequential(new GrabberCommand(true));
		addSequential(new SetHeightCommand("Lift_Tote_1"));
		// scoring logic
		addSequential(new ForwardCommand(-1, false), 1.0); // change interrupt
		addSequential(new SetHeightCommand("Lift_Tote_0"));
		addSequential(new GrabberCommand(false));
		addSequential(new ForwardCommand(-.1, false), 1.0); // change interrupt
		System.out.println("Currently in autonomous 'AutoSM'");
	}

}
