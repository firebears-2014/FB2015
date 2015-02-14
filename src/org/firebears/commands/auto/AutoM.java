package org.firebears.commands.auto;

import org.firebears.commands.drive.ForwardCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Autonomous command currently does nothing, will do ____
 */
public class  AutoM extends CommandGroup {

    public AutoM() {
    	//todo: add in a way to stop movement depending on where the robot is
    	addSequential(new ForwardCommand(-1), 1.0);
    	System.out.println("Currently in autonomous 'AutoM'");
    }

}
