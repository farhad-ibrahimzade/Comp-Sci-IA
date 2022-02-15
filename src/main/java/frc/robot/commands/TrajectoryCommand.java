package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.MecanumControllerCommand;
import frc.robot.Trajectories;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class TrajectoryCommand extends CommandBase {
    final static DriveSubsystem m_robotDrive = new DriveSubsystem();

    MecanumControllerCommand mecanumControllerCommand =
        new MecanumControllerCommand(
            Trajectories.exampleTrajectory,
            m_robotDrive::getPose,
            DriveConstants.kFeedforward,
            DriveConstants.kDriveKinematics,
            // Position contollers
            DriveConstants.xController,
            DriveConstants.yController,
            DriveConstants.thetaController,
            // Needed for normalizing wheel speeds
            DriveConstants.kMaxSpeedMetersPerSecond,
            // Velocity PID's
            DriveConstants.driveController,
            DriveConstants.driveController,
            DriveConstants.driveController,
            DriveConstants.driveController,
            m_robotDrive::getCurrentWheelSpeeds,
            m_robotDrive::setDriveMotorControllersVolts, // Consumer for the output motor voltages
            m_robotDrive);



    @Override
    public void initialize() {
        // Reset odometry to the starting pose of the trajectory.
        m_robotDrive.resetOdometry(Trajectories.exampleTrajectory.getInitialPose());        
                
    }
    
    @Override
    public void execute(){
        mecanumControllerCommand.schedule();
    }

}
