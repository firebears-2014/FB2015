package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.grabber.GrabberCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class  AutoGM extends CommandGroup {
    public AutoGM() {
    	addSequential(new GrabberCommand(true)); //Grab Tote
    	//Stop depends on color sensor
    	addSequential(new ForwardCommand(-.5, true), 2.0);
    	System.out.println("Currently in autonomous 'AutoGM'");
    }

}
