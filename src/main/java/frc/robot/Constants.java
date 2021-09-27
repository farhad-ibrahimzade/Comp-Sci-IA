package frc.robot;

import edu.wpi.first.wpilibj.util.Units;

public class Constants {
    public static final class DriveConstants{
        public static final int kFrontLeftWheelPort = 2;
        public static final int kFrontRightWheelPort = 6;
        public static final int kBackLeftWheelPort = 3;
        public static final int kBackRightWheelPort = 5;
        //drivetrain stuff TODO: CALIBRATE
        public static double speed = 0.6; //speed control 
        public static final double driveSpeed = 0.6;
        public static double kP = 0.001;
        public static double kI = 0;
        public static double kD = 0;
        public static double wheelRadius = 4;
        public static double wheelRatio = 0.1; //encoder conversion factor
        public static double gearRatio = 1;
        public static double rpmToVelocity = 2 * Math.PI * Units.inchesToMeters(wheelRadius) / 60; //RPM of the wheel to speed of the wheel (in meters per second)
        //distance from the center of the robot to the wheels(for kinematics), expressed in inches
        public static double frontLeftWheelX = 0;
        public static double frontLeftWheelY = 0;
        public static double frontRightWheelX = 0;
        public static double frontRightWheelY = 0;
        public static double backLeftWheelX = 0;
        public static double backLeftWheelY = 0;
        public static double backRightWheelX = 0;
        public static double backRightWheelY = 0;
    }
    public static final class IntakeConstants {
        public static final int kIntakePort = 4;
        public static final double kIntakeSpeed = 0.5;
    }

    public static final class ShooterConstants {
        public static final int kShooter1Port = 7;
        public static final int kShooter2Port = 6;
        public static final int kIndexPort = 0;

        public static final double kIndexClosedPosition = 0.5;
        public static final double kIndexOpenPosition = 0.35;

        public static final double kIdealShotSpeed = 0.4; //ideal motor speed to run the shooter at
        public static final double kTimeToChargeUp = 4; //time in seconds for the shooter to reach ideal speed
    }
    public static final class JoystickConstants {
        public static final int kXStick1 = 0;
        public static final int kYStick1 = 1;
        public static final int kXStick2 = 4;
        public static final int kYStick2 = 5;

        public static final int kJoysick1Port = 0;
        public static final int kJoystick2Port = 1;
    }

    public static final class LimelightConstants {
        public static final double kIdealStrafeValue = 0.4;
        public static final double kIdealForwardValue = 0.2;
        public static final double kIdealRotateValue = 0.15;

        public static final double kIdealAreaValue = 2.5;
        public static final double kAreaRangeValue = 0.3;

        public static final double kP = 0.005;
    }

    public static final class AutoConstants {
        public static final double kAutoShootPower = -0.9;
        public static final double kAutoChargeUpTime = 2;
        public static final double kAutoShootEndTime = 5;

        
    }
    //charachterisation constants
    public static double ks = 0;
    public static double kv = 0;
    public static double ka = 0;
    
    //max velo and accel
    public static double maxVelo = 0;
    public static double maxAccel = 0;
    
    //joystick stuff TODO: NEEDS TO BE CALIBRATED
    public static int port = 0; //joystick port
    public static int LX = 0; //axis
    public static int LY = 1;
    public static int RX = 4;
    public static int RY = 5;
    public static int dpadUp = 5;
    public static int dpadDown = 6;
    public static int x = 3;

}
