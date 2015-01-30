package org.firebears.sensors;

import org.firebears.RobotMap;

import PreferenceSetup.PreferenceSetup;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Preferences;

// is the pot that senses the lift height.
public class HeightSensor {

	AnalogPotentiometer pot = RobotMap.liftpot;

	public final double inchesPerVolt = 1.0;
	public double Zero = 1.0;

	public HeightSensor() {
		// get zero from preferences
		Zero = RobotMap.lift_zero_ref;
	}

	public double getHeight() {
		return (pot.get() - Zero) * inchesPerVolt;
	}

	public void setZero() {
		Preferences preferences;
		preferences = Preferences.getInstance();
		new PreferenceSetup(RobotMap.LIFT_ZERO_REF);

		Zero = RobotMap.lift_zero_ref;
	}

}
