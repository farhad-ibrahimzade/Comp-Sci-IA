package frc.robot.OpMode;

import frc.robot.Bot.Bot;
import frc.robot.Bot.Gamepad;

public class Test_Hardware {
    
    public static void init(){
        Bot.motorMode(true);
    }
    
    public static void execute(){
        Gamepad.testControl();
    }
}
