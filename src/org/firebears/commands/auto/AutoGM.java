package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.RotateCommand;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;



/**
 * Autonomous command: Grabs tote and drives backwards it into auto zone.
 * Position the robot facing the drivers stations, with grabbers around the tote.
 */
public class  AutoGM extends CommandGroup {
    public AutoGM() {
    	addSequential(new GrabberCommand(false));
    	addSequential(new GrabberCommand(true)); //Grab Tote
    	//Lift lift as to not damage it.
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));
    	//Stop depends on color sensor
    	addSequential(new ForwardCommand(-.5, true), 1.7);
    	//Rotate as to fit in auto zone.
    	addSequential(new RotateCommand(.40, 90));
    	//Drop
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
    	//Release
    	addSequential(new WaitCommand(1.5));
    	addSequential(new GrabberCommand(false)); //release Tote
    }

}
