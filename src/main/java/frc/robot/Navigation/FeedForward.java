package frc.robot.Navigation;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frc.robot.Bot.Constants;

public class FeedForward {
    //initialize the feedforward
    private static SimpleMotorFeedforward feedForward = new SimpleMotorFeedforward(Constants.ks, Constants.kv, Constants.ka);

    public static SimpleMotorFeedforward getFeedForward(){
        return feedForward;
    }
}