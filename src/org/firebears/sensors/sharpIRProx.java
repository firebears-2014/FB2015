package org.firebears.sensors;
//made by Jacob Wiggins

import edu.wpi.first.wpilibj.AnalogInput;

/*
 * Proximity Detector
 */
public class sharpIRProx extends AnalogInput {

	public sharpIRProx(int channel) {
		super(channel);
	}

	public boolean inRange() {
		double prox = this.getAverageVoltage();
		if (prox < 1.0) {
			return true;
		} else {
			return false;
		}
	}

}
