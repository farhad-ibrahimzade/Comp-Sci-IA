package frc.robot.Navigation;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import frc.robot.Bot.Constants;

public class Pid {
    //initialize the pid controller
    private static PIDController pid = new PIDController(Constants.kP, Constants.kI, Constants.kD);

    //pid controllers for each wheel
    private static PIDController leftFront = new PIDController(Constants.kP, Constants.kI, Constants.kD);
    private static PIDController rightFront = new PIDController(Constants.kP, Constants.kI, Constants.kD);
    private static PIDController leftBack = new PIDController(Constants.kP, Constants.kI, Constants.kD);
    private static PIDController rightBack = new PIDController(Constants.kP, Constants.kI, Constants.kD);

    private static PIDController xController = new PIDController(Constants.kP, Constants.kI, Constants.kD);
    private static PIDController yController = new PIDController(Constants.kP, Constants.kI, Constants.kD);

    private static Constraints constraints = new Constraints(Constants.maxVelo,Constants.maxAccel);
    private static ProfiledPIDController tController = new ProfiledPIDController(Constants.kP, Constants.kI, Constants.kD, constraints);

    public static void setSetpoint(double distance){
        pid.setSetpoint(distance);
    }

    public static boolean atSetpoint(){
        return pid.atSetpoint();
    }

    public static double calculate(double value){
        return pid.calculate(value);
    }
    public static PIDController getLeftFront(){
        return leftFront;
    }

    public static PIDController getrightFront(){
        return rightFront;
    }

    public static PIDController getLeftBack(){
        return leftBack;
    }

    public static PIDController getRightBack(){
        return rightBack;
    }

    public static PIDController xController(){
        return xController;
    }
    public static PIDController yController(){
        return yController;
    }
    public static ProfiledPIDController thetaController(){
        return tController;
    }
}
