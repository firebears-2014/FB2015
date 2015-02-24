package org.firebears.commands.drive;

import org.firebears.Robot;
import org.firebears.RobotMap;

import edu.wpi.first.wpilibj.command.PIDCommand;

/*
 * This command rotates the robot by "degrees" degrees
 */
public class RotateCommand extends PIDCommand{
	
	public boolean fieldOriented;
	
    final double targetDegrees;
	
	double rotateDegrees;
	double rotateSpeed;
	private double initialAngle;
	private boolean isNegative = false;
	boolean timeo;
	
	public RotateCommand(double d, double degrees, boolean to) {
        super(0.02, 0.0, 0.0); //P, I, D
		targetDegrees = degrees;
		rotateSpeed = d;
		timeo = to;
		if(degrees < 0) {
			isNegative = true;
		}
        getPIDController().setAbsoluteTolerance(5);
	}

	protected void initialize() {
		fieldOriented = Robot.chassis.getFieldOriented();
		Robot.chassis.setFieldOriented(false);
    	initialAngle = RobotMap.chassis_drive_gyro.getAngle();
        getPIDController().setSetpoint(targetDegrees);
        if(timeo==true){
        setTimeout(2);
        }
    }
    
    protected void execute() {
    	
    }
    
    protected boolean isFinished() {
        return getPIDController().onTarget() || isTimedOut();
    }
    
    protected void end() {
    	Robot.chassis.mechanumDrive(0, 0, 0);
		Robot.chassis.setFieldOriented(fieldOriented);
    }
    
    protected void interrupted() {
    	end();
    }

	@Override
	protected double returnPIDInput() {
		return RobotMap.chassis_drive_gyro.getAngle() - initialAngle;
	}

	@Override
	protected void usePIDOutput(double output) {
		Robot.chassis.mechanumDrive(0, 0, output * rotateSpeed /** (isNegative ? -1. : 1.)*/);
	}

}
