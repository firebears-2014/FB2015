package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ForwardCommand;
import org.firebears.commands.drive.StrafeCommand;
import org.firebears.commands.grabber.GrabberCommand;
import org.firebears.commands.lift.SetHeightCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

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
    	addSequential(new StrafeCommand(-.5), .5); //Move left to the tote
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT)); //Set Bin
    	addSequential(new GrabberCommand(false)); //Release the bin
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_0_HEIGHT)); //Move lift to grab
    	addSequential(new GrabberCommand(true)); //Grab the tote
    	addSequential(new SetHeightCommand(Robot.lift.LIFT_2_HEIGHT)); //Lift the tote
    	addSequential(new ForwardCommand(-.5), 2.0); // Move backward into auto zone    	
		System.out.println("Currently in autonomous 'AutoStack'");
    }

}
