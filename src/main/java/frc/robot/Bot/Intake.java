package frc.robot.Bot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {
    private static CANSparkMax intakeMotor = new CANSparkMax(Constants.intakePort, MotorType.kBrushed);

     /**
   * A method that runs the intake at the provided power.
   *    
   * @param value Speed at which the intake will run (from -1.0 to 1.0)
   */
    public static void succ(double value){
        intakeMotor.set(Constants.intakeSpeed*value);
    }
     /**
   * A method that runs the intake inwards.
   *    
   */
    public static void succIn(){
        succ(1);
    }
     /**
   * A method that runs the intake outwards.
   *    
   */
    public static void succOut(){
        succ(-1);
    }
     /**
   * A method that stops the intake from running.
   *    
   */
    public static void stop(){
        succ(0);
    }
     /**
   * A method that sets the zero-power mode for the intake motor(s): either coast or brake. 
   * Brake is typically recommended when robot is active, and coast for when robot is turned off.
   *
   * @param on True for brake and false for coast.
   */
    public static void motorMode(boolean on){
        IdleMode mode;
        if(on) mode = IdleMode.kBrake;
        else mode = IdleMode.kCoast;
        intakeMotor.setIdleMode(mode);
    }
}