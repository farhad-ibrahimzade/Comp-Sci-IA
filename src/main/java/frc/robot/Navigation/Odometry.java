package frc.robot.Navigation;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import frc.robot.Bot.Gyro;

public class Odometry {
    //initialize the odometry
    private static MecanumDriveOdometry odometry = new MecanumDriveOdometry(Kinematics.getKinematics(), Gyro.getHeading());

    public static void update() { 
        odometry.update(Gyro.getHeading(), WheelSpeeds.getSpeeds());
    }
    public static Pose2d updatePosition() { 
        return odometry.update(Gyro.getHeading(), WheelSpeeds.getSpeeds());
    }

    public static void resetPosition(Pose2d pose, Rotation2d angle){
        odometry.resetPosition(pose, angle);
    }
}
