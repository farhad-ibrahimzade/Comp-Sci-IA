package frc.robot;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

public class Constants {
    public static final class DriveConstants{

        //wheel ports
        public static final int kFrontLeftWheelPort = 2;
        public static final int kFrontRightWheelPort = 6;
        public static final int kBackLeftWheelPort = 3;
        public static final int kBackRightWheelPort = 5;

        //drivetrain stuff
        public static double speed = 1; //speed control 

        //PID stuff
        public static double kP = 0.001;
        public static double kI = 0;
        public static double kD = 0;

        public static double ks = 0.174;
        public static double kv = 2.64;
        public static double ka = 0.0997;
        public static final double kTrackWidth = 0.53; //20.5 - 21 in
        // Distance between centers of right and left wheels on robot
        public static final double kWheelBase = 0.52; //20.25-20.5 in
        // Distance between centers of front and back wheels on robot

        public static final MecanumDriveKinematics kDriveKinematics =
        new MecanumDriveKinematics(
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));

        public static final SimpleMotorFeedforward kFeedforward =
        new SimpleMotorFeedforward(ks, kv, ka);

        // Example value only - as above, this must be tuned for your drive!
        public static final double kPFrontLeftVel = 0.5;
        public static final double kPRearLeftVel = 0.5;
        public static final double kPFrontRightVel = 0.5;
        public static final double kPRearRightVel = 0.5;
        
    }
    public static final class IntakeConstants {
        public static final int kIntakePort = 4;
        public static final double kIntakeSpeed = 0.5;
    }

    public static final class ShooterConstants {
        public static final int kShooter1Port = 1;
        public static final int kShooter2Port = 7;
        public static final int kIndexPort = 0;

        public static final double kIndexClosedPosition = 0.5;
        public static final double kIndexOpenPosition = 0.35;

        public static final double kIdealShotSpeed = 0.8; //ideal motor speed to run the shooter at
        public static final double kTimeToChargeUp = 4; //time in seconds for the shooter to reach ideal speed

        public static final double shooterSpeed = 100;

        public static final double kP = 0.1;
        public static final double kI = 0;
        public static final double kD = 0;
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

    public static final class SonarConstants{
        public static final int sonar1 = 0;
        public static final int sonar2 = 0;
    }
    public static final class AutoConstants {
        public static final double kAutoShootPower = -0.9;
        public static final double kAutoChargeUpTime = 2;
        public static final double kAutoShootEndTime = 5;

        
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 0.5;
        public static final double kPYController = 0.5;
        public static final double kPThetaController = 0.5;
    
        // Constraint for the motion profilied robot angle controller
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
      }
}
