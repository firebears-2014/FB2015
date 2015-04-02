package org.firebears.commands.auto;

import org.firebears.Robot;
import org.firebears.commands.drive.ChassisCommand;
import org.firebears.commands.drive.StrafeCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous command: Grabs tote and brings it into auto zone
 */
public class AutoS extends CommandGroup {
	public AutoS() {
		Robot.lift.setSetpoint(5);
		addSequential(new ChassisCommand(-0.65, 0, 0.0164), 4.3);
		addSequential(new ChassisCommand(0.1, 0, 0), 1);
	}

}
