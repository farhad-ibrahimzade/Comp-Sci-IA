package frc.robot.Navigation;

import com.revrobotics.CANEncoder;

import frc.robot.Bot.Constants;
import frc.robot.Bot.Drivetrain;

public class Encoders {
    //initialize the encoders
    private static CANEncoder frontLeftEncoder = Drivetrain.leftMotor1.getEncoder();
    private static CANEncoder frontRightEncoder = Drivetrain.leftMotor2.getEncoder();
    private static CANEncoder backLeftEncoder = Drivetrain.rightMotor1.getEncoder();
    private static CANEncoder backRightEncoder = Drivetrain.rightMotor2.getEncoder();

    public static void init(){
        frontLeftEncoder.setPositionConversionFactor(Constants.wheelRatio);
        frontRightEncoder.setPositionConversionFactor(Constants.wheelRatio);
        backLeftEncoder.setPositionConversionFactor(Constants.wheelRatio);
        backRightEncoder.setPositionConversionFactor(Constants.wheelRatio);

        frontLeftEncoder.setVelocityConversionFactor(Constants.rpmToVelocity);
        frontRightEncoder.setVelocityConversionFactor(Constants.rpmToVelocity);
        backLeftEncoder.setVelocityConversionFactor(Constants.rpmToVelocity);
        backRightEncoder.setVelocityConversionFactor(Constants.rpmToVelocity);
    }

    public static double getPosition(){
        return frontLeftEncoder.getPosition();
    }

    public static double getFrontLeftVelo(){
        return frontLeftEncoder.getVelocity();
    }

    public static double getFrontRightVelo(){
        return frontRightEncoder.getVelocity();
    }

    public static double getBackLeftVelo(){
        return backLeftEncoder.getVelocity();
    }

    public static double getBackRightVelo(){
        return backRightEncoder.getVelocity();
    }

    public static void reset(){
        frontLeftEncoder.setPosition(0);
        backLeftEncoder.setPosition(0);
        frontRightEncoder.setPosition(0);
        backRightEncoder.setPosition(0);
    }
}
