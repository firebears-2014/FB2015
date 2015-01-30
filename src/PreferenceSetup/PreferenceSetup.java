package PreferenceSetup;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
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
    	 * ex:
    	 * if(m_key.equals(RobotMap.String)){
    	 * 
    	 * preferences.putDouble(m_key, double);
    	 * 
    	 * RobotMap.value = wantedValue;
    	 * }
    	 */
		double height = Robot.lift.heightSensor.getHeight();
		
    	if(m_key.equals(RobotMap.LIFT_ZERO_REF)){
      	 
       	 preferences.putDouble(m_key, RobotMap.lift_zero_ref);
       	 RobotMap.lift_zero_ref = height;
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
