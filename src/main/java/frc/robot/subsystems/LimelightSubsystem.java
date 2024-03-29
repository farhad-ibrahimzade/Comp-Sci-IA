package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LimelightConstants;

public class LimelightSubsystem extends SubsystemBase {
  
    public LimelightSubsystem() {

    }
    /**
    * This function returns whether the limelight has any valid targets (0 or 1)
    * @return Target status (0/1)
     */
    public double getTV() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    }

    /**
    * This function returns whether there is a target detected by the camera
    * @return Target status (boolean)
     */
    public boolean hasTarget() {
      return getTV() == 1.0;
    }

    /**
    * This function returns the Horizontal Offset From Crosshair To Target
    *-27 degrees to 27 degrees
    * @return Horizontal Offset From Crosshair To Target
     */
    public double getTX() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    }

    /**
    * This function returns the Vertical Offset From Crosshair To Target
    *-20.5 degrees to 20.5 degrees
    * @return Vertical Offset From Crosshair To Target
     */
    public double getTY() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    }

    /**
    * This function returns the Target Area
    * @return Target Area
     */
    public double getTA() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    }

    /**
    * This function returns the forward speed to approach the target
    * @return Forward Speed
     */
    public double getForwardSpeed() {
      if(this.hasTarget()){
        // try to drive forward until the target area reaches our desired area
        double forwardSpeed = (LimelightConstants.DESIRED_TARGET_AREA - getTA()) * LimelightConstants.DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (forwardSpeed > LimelightConstants.MAX_DRIVE)
        {
          forwardSpeed = LimelightConstants.MAX_DRIVE;
        }
        return -forwardSpeed;
      }
      return 0;
    }

    /**
    * This function returns the shooter speed
    * @return Shooter Speed
     */
    public double getShooterSpeed() {
      if(this.hasTarget()){
        // try to drive forward until the target area reaches our desired area
        double shooterSpeed = ((LimelightConstants.DESIRED_TARGET_AREA - getTA()) + 0.2);
        return -shooterSpeed;
      }
      return 0;
    }

    /**
    * This function returns theturning speed to approach the target
    * @return Turning Speed
     */
    public double getTurnSpeed(){
      if(this.hasTarget()){
      // Start with proportional steering
        double turnSpeed = getTX() * LimelightConstants.STEER_K;
        return turnSpeed;
      }
      return 0;
    }
}
