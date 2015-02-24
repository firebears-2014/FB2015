package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ChassisCommand;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.RotateCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.drive.ToteApproachCommand;
import org.firebears.commands.drive.Totecenter;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * Autonomous command:
 *  1. grabs recycling bin
 *  2. lifts bin
 *  3. Strafes to tote
 *  4. sets down bin
 *  5. Lets go.
 *  6. Moves lift down
 *  7. grabs tote.
 *  8. Lifts tote
 *  9. Moves backwards into auto-zone
 */
public class  AutoStack extends CommandGroup {

    public AutoStack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	addSequential(new GrabberCommand(true)); //Close Grabbers
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_3_HEIGHT)); //Lift Bin
    	addSequential(new WaitCommand(1.5)); //Wait for lift
    	addSequential(new RotateCommand(.5, 45, true)); //Rotate
    	addSequential(new ChassisCommand(-.5, .1, 0), 1); //Approach the tote.
//    	addSequential(new ToteApproachCommand()); //Approach the tote.
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT)); //Set Bin
    	addParallel(new ForwardCommand(.5, false), .25);
    	addSequential(new WaitCommand(1.5));
    	addSequential(new GrabberCommand(false)); //Release the bin
    	addSequential(new ForwardCommand(-.4, false), .3);
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT)); //Move lift to grab
    	addSequential(new WaitCommand(.8));
    	addSequential(new ForwardCommand(.6, false), .3);
    	addSequential(new WaitCommand(1.5));
    	addSequential(new GrabberCommand(true)); //Grab the tote
    	addSequential(new WaitCommand(.8));
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT)); //Lift the tote
    	addSequential(new WaitCommand(1.));
    	addSequential(new RotateCommand(.5, -90, true));
    	addSequential(new ForwardCommand(.5, false), 2.0); // Move Forward into auto zone 
    	addSequential(new RotateCommand(.5, -80, true)); //Fit
		System.out.println("Currently in autonomous 'AutoStack'");
    }

}
