package PreferenceSetup;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 * The Command for initializing preferences.
 */
public class PreferenceSetup extends Command {

	private Preferences preferences;
	private String m_key;

	public PreferenceSetup(String key) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		m_key = key;
		preferences = Preferences.getInstance();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		/*
		 * ex: if(m_key.equals(RobotMap.String)){
		 * 
		 * preferences.putDouble(m_key, double);
		 * 
		 * RobotMap.value = wantedValue; }
		 */
		double height = Robot.lift.heightSensor.getHeight();

		if (m_key.equals(RobotMap.LIFT_ZERO_REF)) {
			preferences.putDouble(m_key, RobotMap.lift_zero_ref);
			RobotMap.lift_zero_ref = height;
		} else if (m_key.equals(RobotMap.LIFT_TOTE_PICKUP)) {
			preferences.putDouble(m_key, RobotMap.lift_tote_pickup);
			RobotMap.lift_tote_pickup = height;
		} else if (m_key.equals(RobotMap.LIFT_TOTE_1)) {
			preferences.putDouble(m_key, RobotMap.lift_tote_1);
			RobotMap.lift_tote_1 = height;
		} else if (m_key.equals(RobotMap.LIFT_TOTE_2)) {
			preferences.putDouble(m_key, RobotMap.lift_tote_2);
			RobotMap.lift_tote_2 = height;
		} else if (m_key.equals(RobotMap.LIFT_TOTE_3)) {
			preferences.putDouble(m_key, RobotMap.lift_tote_3);
			RobotMap.lift_tote_3 = height;
		} else if (m_key.equals(RobotMap.CHASSIS_DRIVE_TYPE_TAL)) {
			preferences.putBoolean(m_key, true);
			RobotMap.chassis_drive_type_tal = true;
		} else if (m_key.equals(RobotMap.CHASSIS_DRIVE_TYPE_JAG)) {
			preferences.putBoolean(RobotMap.CHASSIS_DRIVE_TYPE_TAL, false);
			RobotMap.chassis_drive_type_tal = false;
		}

		preferences.save();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
