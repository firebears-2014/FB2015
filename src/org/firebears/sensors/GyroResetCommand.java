package org.firebears.sensors;

import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Reset the gyro to zero degrees.
 */
public class GyroResetCommand extends Command {

	public GyroResetCommand() { }

	protected void initialize() {
		RobotMap.chassis_drive_gyro.reset();
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() { }

	protected void interrupted() { }
}
