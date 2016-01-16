package org.firebears.util;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.SpeedController;

public class TalonEncoder implements PIDSource {
	
	CANTalon talon;
	
	public TalonEncoder(SpeedController chassis_front_left_controller) {
		talon = (CANTalon) chassis_front_left_controller;
	}

	@Override
	public double pidGet() {
		return talon.getEncVelocity();
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return null;
	}

}
