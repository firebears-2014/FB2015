package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.drive.Totecenter;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class  AutoTM extends CommandGroup {
    public AutoTM() {
    	addSequential(new GrabberCommand(true)); 			 //Grab container
    	addSequential(new SetHeightCommand("Lift_Tote_3"));  //moves container up
		addParallel(new StrafeCommand(-1), 1.0); 			 // change interrupt?
		addParallel(new Totecenter());  					 //centers on tote
		addSequential(new SetHeightCommand("Lift_Tote_2"));  //moves container down
		addParallel(new GrabberCommand(false));  			 //releases
		addSequential(new SetHeightCommand("Lift_Tote_0"));  //sets arm to floor position
		addSequential(new Totecenter());  					 //centers again to be sure
		addSequential(new GrabberCommand(true));  			 //grabs tote
		addSequential(new SetHeightCommand("Lift_Tote_1"));  //moves conatainer
    	//todo: add in a way to stop movement depending on where the robot is
    	addSequential(new ForwardCommand(-.5), 2.0);
    	System.out.println("Currently in autonomous 'AutoTM'");
    }

}
