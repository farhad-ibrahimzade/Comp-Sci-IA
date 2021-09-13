package frc.robot.Navigation;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.robot.Bot.Constants;
import frc.robot.Bot.DriveSubsystem;

public class Trajectories {

    static DriveSubsystem sub = new DriveSubsystem();
    static TrajectoryConfig config = new TrajectoryConfig(Constants.maxVelo, Constants.maxAccel);

    public static void init() {
        config.setKinematics(Kinematics.getKinematics());
    }

    static Trajectory trajectory = TrajectoryGenerator.generateTrajectory(
            Arrays.asList(new Pose2d(0, 0, new Rotation2d(0)), new Pose2d(1.0, 0, new Rotation2d(0))), config);

    static MecanumController mecanumController = new MecanumController(trajectory);

    public static void traj1(){
        mecanumController.initialize();
        mecanumController.execute();
    }

}
