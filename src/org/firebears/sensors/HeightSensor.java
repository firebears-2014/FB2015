package org.firebears.sensors;

import org.firebears.RobotMap;
import org.firebears.commands.PreferenceSetup;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Preferences;

/*
 * this is the pot that senses the lift height.
 */
public class HeightSensor {

	AnalogPotentiometer pot = null;

	// number used to convert volts (which the pot returns) to inches
	//assume 0 to 5 V input, single turn on 10 turn pot  = 7.853975" = .5V, Travel = 58" PVM 1/31/15
	public final double inchesPerVolt = 7.853975/0.5;// 15.70795 inches per volt PVM 1/31/15
	public double Zero;

	public HeightSensor(AnalogPotentiometer p) {
		Zero = RobotMap.lift_zero_ref;
		pot = p;
		if (pot == null)  {
			System.err.println("WARNING! Lift potentiometer is null");
		}
	}

	public double getHeight() {
		// returns inches above "Zero"
		if (pot == null) {
			return 0.0;
		}
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
