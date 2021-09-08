package frc.robot.Bot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;

public class Shooter {
    private static CANSparkMax shooterMotor = new CANSparkMax(Constants.shooterPort, MotorType.kBrushless);
    private static Servo servo = new Servo(Constants.servoPort);

    public static void init(){
        servo.set(0.5);
    }
     /**
   * A method that turns the shooter on.
   *    
   */
    private static void on(){
        shooterMotor.set(Constants.shooterSpeeed);
    }
     /**
   * A method that turns the shooter off.
   *    
   */
    private static void off(){
        shooterMotor.set(0);
    }
     /**
   * A method that runs a routine to shoot the game elements into the target
   *    
   */
    public static void shoot(){
        on();
        servo.set(Constants.Servo_Down);
        Intake.succIn();
        Bot.wait(2000);
        servo.set(Constants.Servo_Up);
        Bot.wait(500);
        servo.set(Constants.Servo_Down);
        Bot.wait(1500);
        servo.set(Constants.Servo_Down);
        Intake.stop();
        off();
    }
     /**
   * A method that sets the zero-power mode for the shooter motor(s): either coast or brake. 
   * Brake is typically recommended when robot is active, and coast for when robot is turned off.
   *
   * @param on True for brake and false for coast.
   */
    public static void motorMode(boolean on){
        IdleMode mode;
        if(on) mode = IdleMode.kBrake;
        else mode = IdleMode.kCoast;
        shooterMotor.setIdleMode(mode);
    }
}
