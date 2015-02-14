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
    	//todo: add in a way to stop movement depending on where the robot is
    	addSequential(new ForwardCommand(1), 2.0);
    	System.out.println("Currently in autonomous 'AutoGM'");
    }

}
