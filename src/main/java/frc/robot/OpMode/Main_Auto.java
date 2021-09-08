package frc.robot.OpMode;

import frc.robot.Bot.Bot;
import frc.robot.Bot.Drivetrain;

public class Main_Auto {

    static double startTime;

    public static void init() {
        Bot.motorMode(true);
    }

    public static void execute(){
        Drivetrain.PIDdrive(0.5); //move the robot 5 inches

    }
}
