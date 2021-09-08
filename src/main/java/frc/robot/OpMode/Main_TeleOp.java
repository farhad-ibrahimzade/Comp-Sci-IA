package frc.robot.OpMode;

import frc.robot.Bot.Bot;
import frc.robot.Bot.Gamepad;

public class Main_TeleOp {

    public static void init(){
        Bot.motorMode(true);
    }

    public static void execute(){
        Gamepad.teleopControl();
    }
}
