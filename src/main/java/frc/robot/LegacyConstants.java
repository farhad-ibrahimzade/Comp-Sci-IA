package frc.robot;

import edu.wpi.first.wpilibj.util.Units;

public class LegacyConstants {
    //drivetrain stuff 
    public static double speed = 0.6; //speed control 
    public static final double driveSpeed = 0.6;
    public static double kP = 3;
    public static double kI = 0.1;
    public static double kD = 0.3;
    public static double wheelRadius = 4;
    public static double wheelRatio = 2.01; //encoder conversion factor
    public static double gearRatio = 12.75;
    public static double rpmToVelocity = 2 * Math.PI * Units.inchesToMeters(wheelRadius) / 60; //RPM of the wheel to speed of the wheel (in meters per second)
    //distance from the center of the robot to the wheels(for kinematics), expressed in inches
    public static double frontLeftWheelX = -13;
    public static double frontLeftWheelY = 6;
    public static double frontRightWheelX = 13;
    public static double frontRightWheelY = 6;
    public static double backLeftWheelX = -13;
    public static double backLeftWheelY = -6;
    public static double backRightWheelX = 13;
    public static double backRightWheelY = -6;

    //charachterisation constants
    public static double ks = 0.01;
    public static double kv = 0.1;
    public static double ka = 0.001;
    
    //max velo and accel
    public static double maxVelo = 10;
    public static double maxAccel = 10;
    
    //joystick stuff 
    public static int port = 0; //joystick port
    public static int LX = 0; //axis
    public static int LY = 1;
    public static int RX = 4;
    public static int RY = 5;
    public static int dpadUp = 5;
    public static int dpadDown = 6;
    public static int x = 3;

    //intake stuff
    public static int intakePort = 4; //intake id
    public static double intakeSpeed = 0.5; //intake speed

    //shooter stuff
    public static int shooterPort = 7; //shooter port
    public static int servoPort = 0; //servo port
    public static double Servo_Down = 0.5;
    public static double Servo_Up = 0.35;
    public static double shooterSpeeed = 0.4; // shooter speed
}
