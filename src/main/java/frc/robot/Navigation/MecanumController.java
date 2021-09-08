package frc.robot.Navigation;

import edu.wpi.first.wpilibj.controller.HolonomicDriveController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import frc.robot.Bot.Constants;
import frc.robot.Bot.Drivetrain;
import edu.wpi.first.wpilibj.Timer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * A command that uses two PID controllers ({@link PIDController}) and a ProfiledPIDController
 * ({@link ProfiledPIDController}) to follow a trajectory {@link Trajectory} with a mecanum drive.
 *
 * <p>The command handles trajectory-following, Velocity PID calculations, and feedforwards
 * internally. This is intended to be a more-or-less "complete solution" that can be used by teams
 * without a great deal of controls expertise.
 *
 * <p>Advanced teams seeking more flexibility (for example, those who wish to use the onboard PID
 * functionality of a "smart" motor controller) may use the secondary constructor that omits the PID
 * and feedforward functionality, returning only the raw wheel speeds from the PID controllers.
 *
 * <p>The robot angle controller does not follow the angle given by the trajectory but rather goes
 * to the angle given in the final state of the trajectory.
 */
@SuppressWarnings("MemberName")
public class MecanumController {
  private final Timer m_timer = new Timer();
  private final boolean m_usePID;
  private final Trajectory m_trajectory;
  private final Pose2d m_pose;
  private final SimpleMotorFeedforward m_feedforward;
  private final MecanumDriveKinematics m_kinematics;
  private final HolonomicDriveController m_controller;
  private final Supplier<Rotation2d> m_desiredRotation;
  private final double m_maxWheelVelocityMetersPerSecond;
  private final PIDController m_frontLeftController;
  private final PIDController m_rearLeftController;
  private final PIDController m_frontRightController;
  private final PIDController m_rearRightController;
  private final MecanumDriveWheelSpeeds m_currentWheelSpeeds;
  private final Consumer<MecanumDriveMotorVoltages> m_outputDriveVoltages ;
  private final Consumer<MecanumDriveWheelSpeeds> m_outputWheelSpeeds;
  private MecanumDriveWheelSpeeds m_prevSpeeds;
  private double m_prevTime;

  /**
   * Constructs a new MecanumControllerCommand that when executed will follow the provided
   * trajectory. PID control and feedforward are handled internally. Outputs are scaled from -12 to
   * 12 as a voltage output to the motor.
   *
   * <p>Note: The controllers will *not* set the outputVolts to zero upon completion of the path
   * this is left to the user, since it is not appropriate for paths with nonstationary endstates.
   *
   * @param trajectory The trajectory to follow.
   * @param desiredRotation The angle that the robot should be facing. This is sampled at each time step.
   */
  @SuppressWarnings("ParameterName")
  public MecanumController(Trajectory trajectory, Supplier<Rotation2d> desiredRotation) {
    m_trajectory = trajectory;
    m_pose = Odometry.updatePosition();
    m_feedforward = FeedForward.getFeedForward();
    m_kinematics = Kinematics.getKinematics();

    m_controller = new HolonomicDriveController(Pid.xController(),Pid.yController(),Pid.thetaController());

    m_desiredRotation =desiredRotation;

    m_maxWheelVelocityMetersPerSecond = Constants.maxVelo;

    m_frontLeftController = Pid.getLeftFront();
    m_rearLeftController = Pid.getLeftBack();
    m_frontRightController = Pid.getrightFront();
    m_rearRightController = Pid.getRightBack();

    m_currentWheelSpeeds = WheelSpeeds.getSpeeds();

    m_outputDriveVoltages = Drivetrain::setVolts;

    m_outputWheelSpeeds = null;

    m_usePID = true;

  }

  /**
   * Constructs a new MecanumControllerCommand that when executed will follow the provided
   * trajectory. PID control and feedforward are handled internally. Outputs are scaled from -12 to
   * 12 as a voltage output to the motor.
   *
   * <p>Note: The controllers will *not* set the outputVolts to zero upon completion of the path
   * this is left to the user, since it is not appropriate for paths with nonstationary endstates.
   *
   * <p>Note 2: The final rotation of the robot will be set to the rotation of the final pose in the
   * trajectory. The robot will not follow the rotations from the poses at each timestep. If
   * alternate rotation behavior is desired, the other constructor with a supplier for rotation
   * should be used.
   *
   * @param trajectory The trajectory to follow.
   */
  @SuppressWarnings("ParameterName")
  public MecanumController(Trajectory trajectory) {
    this(
        trajectory,
        () ->
            trajectory.getStates().get(trajectory.getStates().size() - 1).poseMeters.getRotation());
  }

  public void initialize() {
    var initialState = m_trajectory.sample(0);

    var initialXVelocity =
        initialState.velocityMetersPerSecond * initialState.poseMeters.getRotation().getCos();
    var initialYVelocity =
        initialState.velocityMetersPerSecond * initialState.poseMeters.getRotation().getSin();

    m_prevSpeeds =
        m_kinematics.toWheelSpeeds(new ChassisSpeeds(initialXVelocity, initialYVelocity, 0.0));

    m_timer.reset();
    m_timer.start();
  }

  @SuppressWarnings("LocalVariableName")
  public void execute() {
    double curTime = m_timer.get();
    double dt = curTime - m_prevTime;

    var desiredState = m_trajectory.sample(curTime);

    var targetChassisSpeeds =
        m_controller.calculate(m_pose, desiredState, m_desiredRotation.get());
    var targetWheelSpeeds = m_kinematics.toWheelSpeeds(targetChassisSpeeds);

    targetWheelSpeeds.normalize(m_maxWheelVelocityMetersPerSecond);

    var frontLeftSpeedSetpoint = targetWheelSpeeds.frontLeftMetersPerSecond;
    var rearLeftSpeedSetpoint = targetWheelSpeeds.rearLeftMetersPerSecond;
    var frontRightSpeedSetpoint = targetWheelSpeeds.frontRightMetersPerSecond;
    var rearRightSpeedSetpoint = targetWheelSpeeds.rearRightMetersPerSecond;

    double frontLeftOutput;
    double rearLeftOutput;
    double frontRightOutput;
    double rearRightOutput;

    if (m_usePID) {
      final double frontLeftFeedforward =
          m_feedforward.calculate(
              frontLeftSpeedSetpoint,
              (frontLeftSpeedSetpoint - m_prevSpeeds.frontLeftMetersPerSecond) / dt);

      final double rearLeftFeedforward =
          m_feedforward.calculate(
              rearLeftSpeedSetpoint,
              (rearLeftSpeedSetpoint - m_prevSpeeds.rearLeftMetersPerSecond) / dt);

      final double frontRightFeedforward =
          m_feedforward.calculate(
              frontRightSpeedSetpoint,
              (frontRightSpeedSetpoint - m_prevSpeeds.frontRightMetersPerSecond) / dt);

      final double rearRightFeedforward =
          m_feedforward.calculate(
              rearRightSpeedSetpoint,
              (rearRightSpeedSetpoint - m_prevSpeeds.rearRightMetersPerSecond) / dt);

      frontLeftOutput =
          frontLeftFeedforward
              + m_frontLeftController.calculate(
                  m_currentWheelSpeeds.frontLeftMetersPerSecond, frontLeftSpeedSetpoint);

      rearLeftOutput =
          rearLeftFeedforward
              + m_rearLeftController.calculate(
                  m_currentWheelSpeeds.rearLeftMetersPerSecond, rearLeftSpeedSetpoint);

      frontRightOutput =
          frontRightFeedforward
              + m_frontRightController.calculate(
                  m_currentWheelSpeeds.frontRightMetersPerSecond, frontRightSpeedSetpoint);

      rearRightOutput =
          rearRightFeedforward
              + m_rearRightController.calculate(
                  m_currentWheelSpeeds.rearRightMetersPerSecond, rearRightSpeedSetpoint);

      m_outputDriveVoltages.accept(
          new MecanumDriveMotorVoltages(
              frontLeftOutput, frontRightOutput, rearLeftOutput, rearRightOutput));

    } else {
      m_outputWheelSpeeds.accept(
          new MecanumDriveWheelSpeeds(
              frontLeftSpeedSetpoint,
              frontRightSpeedSetpoint,
              rearLeftSpeedSetpoint,
              rearRightSpeedSetpoint));
    }

    m_prevTime = curTime;
    m_prevSpeeds = targetWheelSpeeds;
  }

  public void end(boolean interrupted) {
    m_timer.stop();
  }

  public boolean isFinished() {
    return m_timer.hasElapsed(m_trajectory.getTotalTimeSeconds());
  }
}
