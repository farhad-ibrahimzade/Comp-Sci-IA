package frc.robot.Bot;

import edu.wpi.first.wpilibj.Joystick; 

public class Gamepad {

    static Joystick joy1 = new Joystick(Constants.port);
    private static double RX = joy1.getRawAxis(Constants.RX);
    private static double RY = joy1.getRawAxis(Constants.RY);
    private static double LX = joy1.getRawAxis(Constants.LX);

     /**
   * A method that converts the input from the gamepad to control the robot during the Teleoperated game mode.
   *
   */
    public static void teleopControl(){
        Drivetrain.drive(joy1.getRawAxis(Constants.RX), joy1.getRawAxis(Constants.RY), joy1.getRawAxis(Constants.LX));
        intakeControl(); 
        shooterControl(Constants.x);
        if(joy1.getRawButton(5)){
            Drivetrain.speedControl(0.2);
        }
        else{
            Drivetrain.speedControl(Constants.driveSpeed);
        }
    }
     /**
   * A method that converts the input from the gamepad to control the robot for hardware testing.
   *
   */
    public static void testControl(){

    }
     /**
   * A method that converts the input from the gamepad to control the intake of the robot.
   *    
   */
    private static void intakeControl(){
        Intake.succ(dpadY());
    }
     /**
   * A method that converts the input from the gamepad to control the shooter.
   *    
   * @param button Button used for turning the shooter on.
   */
    private static void shooterControl(int button){
        if(joy1.getRawButton(button)){
            Shooter.shoot();
        }
    }
    private static double dpadY(){
        int direction = joy1.getPOV(0);
        double dpadYAxisValue;
        if(direction!= -1){
        dpadYAxisValue = Math.cos(Math.toRadians(direction));
        }
        else{
            return 0;
        }
        return dpadYAxisValue;
    }
    
}