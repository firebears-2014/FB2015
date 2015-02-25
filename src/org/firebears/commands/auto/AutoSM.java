package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.drive.WidetoteCommand;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Autonomous Command: Stacks up the 3 totes in the auto zone
 * 
 * Dumb stack and move auto, needs interrupts
 */
public class AutoSM extends CommandGroup {

	public AutoSM() {
		// tote 1 logic
		addSequential(new GrabberCommand(false));
		addSequential(new GrabberCommand(true));
		addSequential(new WaitCommand(.2));
		addSequential(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));
		addSequential(new WaitCommand(2.1));
		// tote 2 logic
		addSequential(new StrafeCommand(1), 1.47); // change interrupt?
		addSequential(new ForwardCommand(-.3, true), .4);
		addSequential(new WidetoteCommand());
		addSequential(new WaitCommand(.3));
		addSequential(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));
		addSequential(new WaitCommand(.8));
		addSequential(new GrabberCommand(false));
		addSequential(new WaitCommand(0.2));
		addSequential(new StrafeCommand(-.5), .3);							
		addSequential(new WidetoteCommand());  
		addSequential(new GrabberCommand(false));	
		addSequential(new ForwardCommand(-.3, true), .1);
		addSequential(new WidetoteCommand());  
		addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
		addSequential(new WaitCommand(.6));
		addSequential(new GrabberCommand(true));
		addSequential(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));
		addSequential(new WaitCommand(2.1));
		addSequential(new StrafeCommand(1), 1.45);
		//tote 3 logic
		addSequential(new ForwardCommand(-.3, true), .4);
		addSequential(new WidetoteCommand());
		addSequential(new WaitCommand(.3));
		addSequential(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));
		addSequential(new WaitCommand(.8));
		addSequential(new GrabberCommand(false));
		addSequential(new WaitCommand(.5));
		addSequential(new StrafeCommand(-.5), .3);							
		addSequential(new WidetoteCommand());  
		addSequential(new GrabberCommand(false));	
		addSequential(new ForwardCommand(-.3, true), .1);
		addSequential(new WidetoteCommand());  
		addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
		addSequential(new WaitCommand(.6));
		addSequential(new GrabberCommand(true));
		addSequential(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));
		addSequential(new WaitCommand(.3));
		// scoring logic
		addSequential(new ForwardCommand(-1, false), 1.0); 
		addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
		addSequential(new WaitCommand(.2));
		addSequential(new GrabberCommand(false));

	}

}
