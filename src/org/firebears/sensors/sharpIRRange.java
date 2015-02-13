package org.firebears.sensors;

// made by Jacob Wiggins

import edu.wpi.first.wpilibj.AnalogInput;

/*
 * Range Sensor
 */
public class sharpIRRange extends AnalogInput {

	public sharpIRRange(int channel) {
		super(channel);
		setOversampleBits(4);

	}

	// distance will be close to the distance that the robot is
	// assuming that it is between 10cm and 80cm.
	// volt represents the voltage that the sensor outputs
	// when taking the range between the robot and something
	// we got the constants by plotting points, making a function that fits the
	// data
	// and using trial and error to make it more accurate
	// return value in inches
	// distance will be close to the distance that the robot is
	// assuming that it is between 10cm and 80cm.
	// volt represents the voltage that the sensor outputs
	// when taking the range between the robot and something
	// we got the constants by plotting points, making a function that fits the
	// data
	// and using trial and error to make it more accurate
	// return value in inches 8" approx 20 cm.

	public double getRangefinderDistance() {
		double volt = getAverageVoltage(); // value from sensor * (5/1024)
		// System.out.println(volt);
		double distance = (26.86 * Math.pow(volt, -1.15)) / 2.54;
		if (distance > 30)
			distance = 1000;
		return distance;

	}

	// @Override
	// public void updateTable() {
	// ITable m_table = super.getTable();
	// if (m_table != null) {
	// m_table.putNumber("Distance", getRangefinderDistance());
	// }
	// }

}
