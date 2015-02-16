package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;


/**
 * Autonomous command : moves into the auto zone
 */
public class  AutoM extends CommandGroup {

    public AutoM() {
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT));
    	//Stop depends on color sensor
    	addSequential(new ForwardCommand(.5, true), 2.0);
    	System.out.println("Currently in autonomous 'AutoM'");
    }

}