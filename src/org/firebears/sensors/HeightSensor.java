package org.firebears.sensors;

import org.firebears.RobotMap;

import PreferenceSetup.PreferenceSetup;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Preferences;

// is the pot that senses the lift height.
public class HeightSensor {

	AnalogPotentiometer pot = RobotMap.liftpot;

	// number used to convert volts (which the pot returns) to inches
	public final double inchesPerVolt = 1.0;
	public double Zero;

	public HeightSensor() {
		Zero = RobotMap.lift_zero_ref;
	}

	public double getHeight() {
		// returns inches above "Zero"
		return (pot.get() - Zero) * inchesPerVolt;
	}

	public double getZero() {
		return Zero;
	}

	public void setZero() {
		new PreferenceSetup(RobotMap.LIFT_ZERO_REF);

		Zero = RobotMap.lift_zero_ref;
	}

}
