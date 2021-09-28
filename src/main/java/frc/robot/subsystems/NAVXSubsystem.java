package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.geometry.Rotation2d;


public class NAVXSubsystem extends SubsystemBase {
    //its the navx 
    private AHRS navx = new AHRS(SerialPort.Port.kMXP);

    //now in a subsystem
    public NAVXSubsystem() {
        navx.reset();
    }

    public double getAngle()
    {
        return navx.getAngle();
    }

    public double getRate()
    {
        return navx.getRate();
    }

    public Rotation2d getRotation2d()
    {
        return navx.getRotation2d();
    }


    public void reset()
    {
        navx.reset();
    }

}

