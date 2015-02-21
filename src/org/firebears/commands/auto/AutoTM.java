package org.firebears.commands.auto;

import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.RotateCommand;
import org.firebears.commands.drive.Totecenter;
import org.firebears.commands.drive.WidetoteCommand;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class  AutoTM extends CommandGroup {
    public AutoTM() {
    	addSequential(new GrabberCommand(false)); 
    	addSequential(new GrabberCommand(true)); 			 //Grab container
    	//add rotate command
       	//addSequential(new RotateCommand(.75, 90)); //TODO: is right?
    	
    	addSequential(new SetHeightCommand("Lift_Tote_3"));  //moves container up

		addSequential(new WidetoteCommand());  					 //centers on tote
		addSequential(new SetHeightCommand("Lift_Tote_2"));  //moves container down
		addParallel(new GrabberCommand(false));				//releases
		addSequential(new WidetoteCommand());  
		addSequential(new SetHeightCommand("Lift_Tote_0"));  //sets arm to floor position
		addSequential(new WidetoteCommand());  					 //centers again to be sure
		addSequential(new GrabberCommand(true));  			 //grabs tote
		addSequential(new SetHeightCommand("Lift_Tote_1"));  //moves conatainer
    	//todo: add in a way to stop movement depending on where the robot is
		//rotate -90 degrees 
    	addSequential(new ForwardCommand(-.5, true), 1.8); //TODO: is right?
    	addSequential(new RotateCommand(.75, 90)); //TODO: is right?
    	addSequential(new SetHeightCommand("Lift_Tote_0"));
    	addSequential(new GrabberCommand(false));
    }

}
