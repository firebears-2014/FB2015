package org.firebears.sensors;
// made by Jacob Wiggins

import edu.wpi.first.wpilibj.AnalogInput;

public class sharpIRRange extends AnalogInput {

	public sharpIRRange(int channel) {
		super(channel);
		// TODO Auto-generated constructor stub
	}
	//distance will be close to the distance that the robot is
	//assuming that it is between 10cm and 80cm.
	//volt represents the voltage that the sensor outputs
	//when taking the range between the robot and something
	//we got the constants by plotting points, making a function that fits the data
	//and using trial and error to make it more accurate

	public double getRangefinderDistance() {
		double volt = this.getAverageVoltage(); // value from sensor * (5/1024)
		//System.out.println(volt);
		double distance = (26.86 * Math.pow(volt, -1.15));
		return distance;
		
	}

}
