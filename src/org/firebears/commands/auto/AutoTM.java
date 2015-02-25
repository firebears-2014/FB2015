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
    	addSequential(new GrabberCommand(false)); 							//grabbers
    	addSequential(new GrabberCommand(true));    	
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT));		//Lifts container up
    	addSequential(new WaitCommand(1.5));								//waits for arm to finish moving
    	addSequential(new StrafeCommand(-.6), 1);							//align with tote
		addSequential(new WidetoteCommand());  
		addSequential(new ForwardCommand(-.3, true), .4);					
		addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT));		//places container on tote
		addSequential(new WaitCommand(.3));
		addSequential(new GrabberCommand(false));							//sets container down
		addSequential(new WaitCommand(.3));
		addSequential(new StrafeCommand(-.5), .4);							//alignment
		addSequential(new WidetoteCommand());  
		addSequential(new GrabberCommand(false));							//redundancy 
		addSequential(new ForwardCommand(-.3, true), .4); 					//alignment
		addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));		//sets arms to floor
		addSequential(new WaitCommand(1.3));								//waits for arms
		addSequential(new GrabberCommand(true));							//grabs tote
		addSequential(new WaitCommand(.3));	
		addSequential(new SetHeightCommand(Robot.lift.LIFT_1_HEIGHT));  	
		addSequential(new WaitCommand(.4));
		addSequential(new RotateCommand(.4, 180, false));					//turns around 
    	addSequential(new ForwardCommand(.5, true), 2.1); 					//drives over hump
    	addSequential(new WaitCommand(.7));
    	addSequential(new RotateCommand(.4, -75, true)); 					//rotates
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT));		//releases tote
    	addSequential(new WaitCommand(.4));
    	addSequential(new GrabberCommand(false));
    }

}	
