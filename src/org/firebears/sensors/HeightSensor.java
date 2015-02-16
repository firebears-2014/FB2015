package org.firebears.sensors;

import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;

/*
 * this is the pot that senses the lift height.
 */
public class HeightSensor {

	AnalogPotentiometer pot = null;

	// number used to convert volts (which the pot returns) to inches
	// assume 0 to 5 V input, single turn on 10 turn pot =
	// 7.853975" = .5V, Travel = 58" PVM 1/31/15
	// public final double inchesPerVolt = 7.853975/0.5;// 15.70795 inches per
	// volt PVM 1/31/15
	public final double inchesPerVolt = 82.7586;

	public HeightSensor(AnalogPotentiometer p) {
		pot = p;
		if (pot == null) {
			System.err.println("WARNING! Lift potentiometer is null");
		}
	}

	public double getVolts() {
		return pot.get();
	}

	public double getHeight() {
		// returns inches above "Zero"
		if (pot == null) {
			return 0.0;
		}
		return (pot.get() - RobotMap.lift_zero_ref) * inchesPerVolt;
	}

}
