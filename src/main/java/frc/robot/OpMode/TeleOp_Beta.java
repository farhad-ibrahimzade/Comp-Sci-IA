package frc.robot.OpMode;

import frc.robot.Bot.Bot;

//THIS IS A BETA VERSION FOR THE TELEOP CODE! ALL TESTINGS OCCUR HERE. NOT FOR USE IN COMPETITIONS!

import frc.robot.Bot.Gamepad;

public class TeleOp_Beta {

    public static void init(){
        Bot.motorMode(true);
    }
    
    public static void execute(){
        Gamepad.teleopControl();
    }
}
