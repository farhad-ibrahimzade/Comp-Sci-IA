package frc.robot.Bot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

public class Gyro {
    private static AHRS gyro; //create gyro object

    /**
     * Initialize the Gyro and assign it to its port (SPI)
     */
    public static void init(){
        gyro = new AHRS(SPI.Port.kMXP);
    }

    /**
    * This function returns the current heading from the gyro as a Rotation2d object
    * @return Current heading
     */
    public static Rotation2d getHeading(){
        return gyro.getRotation2d();
    }

    /**
    * This function returns the current heading from the gyro in degrees
    * @return Current heading
     */
    public static double getDegrees(){
        return gyro.getRotation2d().getDegrees();
    }

     /**
    * This function resets the gyro
     */
    public static void reset(){
        gyro.reset();
    }

    /**
    * This function returns the current rate of change of rotation
    * @return Current rate of change of rotation
     */
    public static double getRate(){
        return gyro.getRate();
    }
}
