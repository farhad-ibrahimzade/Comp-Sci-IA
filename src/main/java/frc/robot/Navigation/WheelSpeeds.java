package frc.robot.Navigation;

import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;

public class WheelSpeeds {
    
    public static MecanumDriveWheelSpeeds getSpeeds() {
        return new MecanumDriveWheelSpeeds(Encoders.getFrontLeftVelo(), Encoders.getFrontRightVelo(),
        Encoders.getBackLeftVelo(), Encoders.getBackRightVelo());
    }
}
