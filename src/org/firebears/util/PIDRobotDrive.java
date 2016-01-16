package org.firebears.util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.communication.UsageReporting;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tInstances;
import edu.wpi.first.wpilibj.communication.FRCNetworkCommunicationsLibrary.tResourceType;

public class PIDRobotDrive extends RobotDrive {

    static final int kFrontLeft_val = 0;
    static final int kFrontRight_val = 1;
    static final int kRearLeft_val = 2;
    static final int kRearRight_val = 3;

    PIDController frontLeftPID;
    PIDController backLeftPID;
    PIDController frontRightPID;
    PIDController backRightPID;

    public double g_p = 1.0;
    public double g_i = 0.0;
    public double g_d = 0.0;
    public double g_maxEncoderSpeed = .2;

    public double g_maxOutput = 0.05;

	public PIDRobotDrive(
			SpeedController frontLeftMotor,
			SpeedController rearLeftMotor,
			SpeedController frontRightMotor,
			SpeedController rearRightMotor,
			PIDSource pidSource,
			PIDSource pidSource2,
			PIDSource pidSource3,
			PIDSource pidSource4,
			double maxEncoderSpeed)
	{
		super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
		frontLeftPID = new PIDController(g_p, g_i, g_d,
				pidSource, frontLeftMotor);
		frontRightPID = new PIDController(g_p, g_i, g_d,
				pidSource2, frontRightMotor);
		backLeftPID = new PIDController(g_p, g_i, g_d,
				pidSource3, rearLeftMotor);
		backRightPID = new PIDController(g_p, g_i, g_d,
				pidSource4, rearRightMotor);
		frontLeftPID.enable();
		frontRightPID.enable();
		backLeftPID.enable();
		backRightPID.enable();
		g_maxEncoderSpeed = maxEncoderSpeed;
	}

	public void mecanumDrive_Cartesian(double x, double y, double rotation, double gyroAngle) {
        if(!kMecanumCartesian_Reported) {
            UsageReporting.report(tResourceType.kResourceType_RobotDrive, getNumMotors(), tInstances.kRobotDrive_MecanumCartesian);
            kMecanumCartesian_Reported = true;
        }
        double xIn = x;
        double yIn = y;
        // Negate y for the joystick.
        yIn = -yIn;
        // Compenstate for gyro angle.
        double rotated[] = rotateVector(xIn, yIn, gyroAngle);
        xIn = rotated[0];
        yIn = rotated[1];

        double wheelSpeeds[] = new double[kMaxNumberOfMotors];
        wheelSpeeds[kFrontLeft_val] = xIn + yIn + rotation;
        wheelSpeeds[kFrontRight_val] = -xIn + yIn - rotation;
        wheelSpeeds[kRearLeft_val] = -xIn + yIn + rotation;
        wheelSpeeds[kRearRight_val] = xIn + yIn - rotation;

        normalize(wheelSpeeds);

       frontLeftPID.setSetpoint(wheelSpeeds[kFrontLeft_val] * g_maxOutput * g_maxEncoderSpeed);
        frontRightPID.setSetpoint(wheelSpeeds[kFrontRight_val] * g_maxOutput * g_maxEncoderSpeed);
        backLeftPID.setSetpoint(wheelSpeeds[kRearLeft_val] * g_maxOutput * g_maxEncoderSpeed);
        backRightPID.setSetpoint(wheelSpeeds[kRearRight_val] * g_maxOutput * g_maxEncoderSpeed);
    }

}
