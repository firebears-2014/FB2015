package org.firebears;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.firebears.commands.auto.*; //Autonomous Commands
import org.firebears.sensors.Accelerometer;
import org.firebears.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    Command autonomousCommand;
    Command AutoGM;
    Command AutoM;
    Command AutoSM;
    private BuiltInAccelerometer accel = new BuiltInAccelerometer();
    public static OI oi;
    public static Chassis chassis;
    public static Lift lift;
    public static Grabber grabber;
    public static Lights lights;
    public static Vision vision;
    public static double accX;
    public static double accZ;
    public static double accY;
    
 
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    RobotMap.init();
    
        chassis = new Chassis();
        lift = new Lift();
        grabber = new Grabber();
        lights = new Lights();
        vision = new Vision();
        // OI must be constructed after subsystems. If the OI creates Commands 
        //(which it very likely will), subsystems are not guaranteed to be 
        // constructed yet. Thus, their requires() statements may grab null 
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // instantiate which command should be used for the autonomous period
        // uncomment next 3 lines to override defaults.
//        autonomousCommand = new AutoStrafeCommand();
//		/*
    	if (oi.autoSelect1.get()==false){autonomousCommand = new AutoSM();
    	}else if (oi.autoSelect2.get()==false){autonomousCommand = new AutoGM();
    	}else if (oi.autoSelect3.get()==false){autonomousCommand = new AutoM();
    	}//else if (OI.autoSelect4.get()==false){autonomousCommand = new AutonomousCommand();
    	//}
//    	*/
        if (RobotMap.chassis_drive_gyro!=null) RobotMap.chassis_drive_gyro.reset();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
        if (RobotMap.chassis_drive_gyro!=null) RobotMap.chassis_drive_gyro.reset();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        Accelerometer.reset();
        
    }

    /**
     * This function is called periodically during operator control
     */

	public void teleopPeriodic() {
    	Scheduler.getInstance().run();

		SmartDashboard.putBoolean("Proximity detector", RobotMap.sharpIRProx.inRange());
		SmartDashboard.putNumber("Distance from object", RobotMap.sharpIRRange.getRangefinderDistance());
		SmartDashboard.putBoolean("Color Sensor Value", oi.scoringPlatformSensor.get());
		if (RobotMap.chassis_drive_gyro!=null) SmartDashboard.putNumber("gyro value", RobotMap.chassis_drive_gyro.getAngle());
		
		SmartDashboard.putNumber("Accel X", accel.getX());
		SmartDashboard.putNumber("Accel Y", accel.getY());
		SmartDashboard.putNumber("Accel Z", accel.getZ());
		
		
//		System.out.println("Raw Accel output: "+accel.getX()+" "+accel.getY());
		Accelerometer.update();
		double Y = Accelerometer.totalY();
		double X = Accelerometer.totalX();
//		System.out.println("X displacement: "+X+" "+"Y displacement: "+Y);

System.out.println("::: POV: " + oi.getJoystickZero().getPOV());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        
    }
}
