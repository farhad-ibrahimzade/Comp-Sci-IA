package frc.robot.Bot;

import frc.robot.Navigation.Encoders;
import frc.robot.Navigation.Trajectories;

public class Bot {
    public static void init(){

    }
    public static void initAuto(){
        Encoders.init();
        Trajectories.init();
    }
    /**
   * A delay method that pauses the execution of the robot code for a set period of time.
   *
   * @param ms Time, in milliseconds, for which the delay will last
   */
  @SuppressWarnings("ParameterName")
    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }
     /**
   * A method that sets the zero-power mode for all motors: either coast or brake. 
   * Brake is typically recommended when robot is active, and coast for when robot is turned off.
   *
   * @param on True for brake and false for coast.
   */
    public static void motorMode(boolean on){
        Drivetrain.motorMode(on);
        Intake.motorMode(on);
        Shooter.motorMode(on);
    }
}
