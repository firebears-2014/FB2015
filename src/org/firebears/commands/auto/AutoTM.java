package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.RotateCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.drive.Totecenter;
import org.firebears.commands.drive.WidetoteCommand;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;



/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class  AutoTM extends CommandGroup {
    public AutoTM() {
    	addSequential(new GrabberCommand(false)); 
    	addSequential(new GrabberCommand(true)); 			 //Grab container
    	//add rotate command
       	//addSequential(new RotateCommand(.75, 90)); //TODO: is right?
    	
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));		//moves container up
    	addSequential(new WaitCommand(1.5));
    	addSequential(new StrafeCommand(-.6), 1);
		addSequential(new WidetoteCommand());  
		addSequential(new ForwardCommand(-.3, true), .2);//centers on tote
		addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT));
		addSequential(new WaitCommand(.3));//moves container down
		addSequential(new GrabberCommand(false));
		addSequential(new WaitCommand(.3));//releases
		addSequential(new StrafeCommand(-.5), .4);
		addSequential(new WidetoteCommand());  
		addSequential(new GrabberCommand(false));
		addSequential(new ForwardCommand(-.3, true), .4); 
		addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
		addSequential(new WaitCommand(1.3));//sets arm to floor position 
		addSequential(new GrabberCommand(true));
		addSequential(new WaitCommand(.3));//grabs tote
		addSequential(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));  //moves conatainer
    	//todo: add in a way to stop movement depending on where the robot is
		addSequential(new WaitCommand(.4));
		addSequential(new RotateCommand(.4, 180, false));
    	addSequential(new ForwardCommand(.5, true), 2.1); //TODO: is right?
    	addSequential(new WaitCommand(.2));
    	addSequential(new RotateCommand(.6, 90, true)); //TODO: is right?
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));
    	addSequential(new WaitCommand(.4));
    	addSequential(new GrabberCommand(false));
    }

}	
