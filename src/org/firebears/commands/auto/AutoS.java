package org.firebears.commands.auto;

import org.firebears.commands.drive.StrafeCommand;
import edu.wpi.first.wpilibj.command.CommandGroup;



/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class  AutoS extends CommandGroup {
    public AutoS() {
    	addSequential(new StrafeCommand(-.7), 1.3);
    }

}	
