package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.RotateCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command : moves backwards into the auto zone
 * Position the robot facing the drivers.
 */
public class  AutoM extends CommandGroup {

    public AutoM() {
    	//Lift lift as to not damage it.
    	addSequential(new SetHeightCommand(Robot.lift.HEIGHT_2));
    	//Move Backward: Stop depends on color sensor
    	addSequential(new ForwardCommand(-.5, false), 2.2);
    	//Rotate as to fit in auto zone.
    	addSequential(new RotateCommand(.50, 90, true));
    }

}