package frc.robot.subsystems;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile.Constraints;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
  // The motors on drive system
  public static CANSparkMax m_frontLeftMotor = new CANSparkMax(DriveConstants.kFrontLeftWheelPort, MotorType.kBrushless);
  public static CANSparkMax m_frontRightMotor = new CANSparkMax(DriveConstants.kFrontRightWheelPort, MotorType.kBrushless);
  public static CANSparkMax m_backLeftMotor = new CANSparkMax(DriveConstants.kBackLeftWheelPort, MotorType.kBrushless);
  public static CANSparkMax m_backRightMotor = new CANSparkMax(DriveConstants.kBackRightWheelPort, MotorType.kBrushless);
  
  private static CANEncoder m_frontLeftEncoder = m_frontLeftMotor.getEncoder();
  private static CANEncoder m_frontRightEncoder = m_frontRightMotor.getEncoder();
  private static CANEncoder m_backLeftEncoder = m_backLeftMotor.getEncoder();
  private static CANEncoder m_backRightEncoder = m_backRightMotor.getEncoder();
    
  public static Translation2d m_frontLeft = new Translation2d(DriveConstants.frontLeftWheelX, DriveConstants.frontLeftWheelY);
  public static Translation2d m_frontRight = new Translation2d(DriveConstants.frontRightWheelX, DriveConstants.frontRightWheelY);
  public static Translation2d m_backLeft = new Translation2d(DriveConstants.backLeftWheelX, DriveConstants.backLeftWheelY);
  public static Translation2d m_backRight = new Translation2d(DriveConstants.backRightWheelX, DriveConstants.backRightWheelY);

  private static PIDController xController = new PIDController(DriveConstants.kP, DriveConstants.kI, DriveConstants.kD);
  private static PIDController yController = new PIDController(DriveConstants.kP, DriveConstants.kI, DriveConstants.kD);

  private static Constraints constraints = new Constraints(Constants.maxVelo,Constants.maxAccel);
  private static ProfiledPIDController tController = new ProfiledPIDController(DriveConstants.kP, DriveConstants.kI, DriveConstants.kD, constraints);

  private final NAVXSubsystem m_gyro = new NAVXSubsystem();

  private static SimpleMotorFeedforward feedForward = new SimpleMotorFeedforward(Constants.ks, Constants.kv, Constants.ka);
  
  private final MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(m_frontLeft, m_frontRight, m_backLeft, m_backRight);
   // Odometry class for tracking robot pose
   private final MecanumDriveOdometry m_odometry = new MecanumDriveOdometry(m_kinematics, m_gyro.getRotation2d());

  private MecanumDrive m_drive = new MecanumDrive(m_frontLeftMotor, m_backLeftMotor, m_frontRightMotor, m_backRightMotor);
  /**
   * Creates a new DriveSubsystem.
   */
  public DriveSubsystem() {
    m_drive.setSafetyEnabled(false);
    m_frontLeftMotor.setIdleMode(IdleMode.kBrake);
    m_frontRightMotor.setIdleMode(IdleMode.kBrake);
    m_backLeftMotor.setIdleMode(IdleMode.kBrake);
    m_backRightMotor.setIdleMode(IdleMode.kBrake);
  }

  /**
   * Drives the robot using base mecanum (y stick 1 = forward, x stick 1 = sideways, x stick 2 = rotation)
   *
   * @param x = speed in x direction
   * @param y = speed in y direction
   * @param c = rotation speed
   */
  public void mecanumDrive(double x, double y, double c) {
    m_drive.driveCartesian(x,-y,c);
  }
  /**
   * Drives the robot using base mecanum (y stick 1 = forward, x stick 1 = sideways, x stick 2 = rotation)
   * This, however, is in relation to the field instead of the robot
   * @param x = speed in x direction
   * @param y = speed in y direction
   * @param c = rotation speed
   * @param theta = navx gyro angle
   */
  public void mecanumDriveGyro(double x, double y, double c, double theta)
  {
      m_drive.driveCartesian(-x, y, c, theta);
  }
  
  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    m_odometry.update(m_gyro.getRotation2d(), getWheelSpeeds());
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public MecanumDriveWheelSpeeds getWheelSpeeds() {
    return new MecanumDriveWheelSpeeds(m_frontLeftEncoder.getVelocity(), m_frontRightEncoder.getVelocity(), m_backLeftEncoder.getVelocity(), m_backRightEncoder.getVelocity());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, m_gyro.getRotation2d());
  }
  
  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts  the commanded left output
   * @param rightVolts the commanded right output
   */
  public static void MecanumDriveVolts(MecanumDriveMotorVoltages volts){
    m_frontLeftMotor.setVoltage(volts.frontLeftVoltage);
    m_frontRightMotor.setVoltage(volts.frontRightVoltage);
    m_backLeftMotor.setVoltage(volts.rearLeftVoltage);
    m_backRightMotor.setVoltage(volts.rearRightVoltage);
}

  /**
   * Resets the drive encoders to currently read a position of 0.
   */
  public void resetEncoders() {
    m_frontLeftEncoder.setPosition(0);
    m_frontRightEncoder.setPosition(0);
    m_backLeftEncoder.setPosition(0);
    m_backRightEncoder.setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_frontLeftEncoder.getPosition() + m_frontRightEncoder.getPosition()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public CANEncoder getFrontLeftEncoder() {
    return m_frontLeftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public CANEncoder getFrontRightEncoder() {
    return m_frontRightEncoder;
  }
  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public CANEncoder getBackLeftEncoder() {
    return m_backLeftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public CANEncoder getBackRightEncoder() {
    return m_backRightEncoder;
  }

  /**
   * Sets the max output of the drive.  Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /**
   * Zeroes the heading of the robot.
   */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return m_gyro.getAngle();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }
}
