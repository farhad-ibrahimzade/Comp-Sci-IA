package frc.robot.Bot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class Gyro {
    private static AHRS gyro;

    public static void init(){
        gyro = new AHRS(SPI.Port.kMXP);
    }

    public static Rotation2d getHeading(){
        return gyro.getRotation2d();
    }

    public static double getDegrees(){
        return gyro.getRotation2d().getDegrees();
    }

    public static void reset(){
        gyro.reset();
    }

    public static double getRate(){
        return gyro.getRate();
    }
}
