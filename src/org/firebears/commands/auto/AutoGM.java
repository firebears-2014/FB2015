package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class  AutoGM extends CommandGroup {
    public AutoGM() {
    	addSequential(new GrabberCommand(false));
    	addSequential(new ForwardCommand(.5, true), 2.0);
    	addSequential(new GrabberCommand(true)); //Grab Tote
    	//Lift lift as to not damage it.
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT));
    	//Stop depends on color sensor
    	addSequential(new ForwardCommand(-.5, true), 2.0);
    	//Drop
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
    	//Release
    	addSequential(new GrabberCommand(false)); //release Tote
    }

}
