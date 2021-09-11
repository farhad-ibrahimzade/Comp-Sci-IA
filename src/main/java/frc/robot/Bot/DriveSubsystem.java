package frc.robot.Bot;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Navigation.Encoders;
import frc.robot.Navigation.Odometry;
import frc.robot.Navigation.WheelSpeeds;

public class DriveSubsystem extends SubsystemBase {

  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {
    // Sets the distance per pulse for the encoders
    Encoders.init();
  }

  @Override
  public void periodic() {
    // Update the odometry in the periodic block
    Odometry.update();
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return Odometry.updatePosition();
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    Odometry.resetPosition(pose, Gyro.getHeading());
  }

  /**
   * Drives the robot at given x, y and theta speeds. Speeds range from [-1, 1] and the linear
   * speeds have no effect on the angular speed.
   *
   * @param xSpeed Speed of the robot in the x direction (forward/backwards).
   * @param ySpeed Speed of the robot in the y direction (sideways).
   * @param rot Angular rate of the robot.
   * @param fieldRelative Whether the provided x and y speeds are relative to the field.
   */
  @SuppressWarnings("ParameterName")
  public void drive(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
    if (fieldRelative) {
      Drivetrain.driveC(ySpeed, xSpeed, rot, -Gyro.getDegrees());
    } else {
      Drivetrain.drive(ySpeed, xSpeed, rot);
    }
  }

  /** Sets the front left drive MotorController to a voltage. */
  public void setDriveMotorControllersVolts(MecanumDriveMotorVoltages volts) {
    Drivetrain.setVolts(volts);
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    Encoders.reset();
  }
  /**
   * Gets the current wheel speeds.
   *
   * @return Current wheel speeds in a MecanumDriveWheelSpeeds object.
   */
  public MecanumDriveWheelSpeeds getCurrentWheelSpeeds() {
    return WheelSpeeds.getSpeeds();
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    Gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return Robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return Gyro.getDegrees();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return Turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -Gyro.getRate();
  }
}
