package frc.robot.Navigation;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import frc.robot.Bot.Constants;

public class Kinematics {
    //initialize positions of the wheels(relative to center of robot)
    public static Translation2d frontLeft = new Translation2d(Constants.frontLeftWheelX, Constants.frontLeftWheelY);
    public static Translation2d frontRight = new Translation2d(Constants.frontRightWheelX, Constants.frontRightWheelY);
    public static Translation2d backLeft = new Translation2d(Constants.backLeftWheelX, Constants.backLeftWheelY);
    public static Translation2d backRight = new Translation2d(Constants.backRightWheelX, Constants.backRightWheelY);
    //initialize the kinematics
    private static MecanumDriveKinematics kinematics = new MecanumDriveKinematics(frontLeft, frontRight, backLeft, backRight);

    public static MecanumDriveKinematics getKinematics(){
        return kinematics;
    }
}
