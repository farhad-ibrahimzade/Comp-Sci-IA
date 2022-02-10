package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SonarConstants;

public class SonarSubsystem extends SubsystemBase{

    private static Ultrasonic sonar = new Ultrasonic(SonarConstants.sonar1, SonarConstants.sonar2);

    public SonarSubsystem() {
        Ultrasonic.setAutomaticMode(true);
    }

    public static double getInches(){
        return sonar.getRangeInches();
    }

    public static double getMM(){
        return sonar.getRangeMM();
    }
}
