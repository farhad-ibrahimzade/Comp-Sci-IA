package frc.robot.OpMode;

import edu.wpi.first.wpilibj.Timer;
import frc.robot.Bot.Bot;
import frc.robot.Navigation.Trajectories;

public class Main_Auto {

    static double startTime;
    static Timer timer = new Timer();

    public static void init() {
        Bot.motorMode(true);
        Bot.initAuto();
    }

    public static void execute(){
        //Drivetrain.PIDdrive(0.5); //move the robot 5 inches
        Trajectories.traj1();
    }
}
