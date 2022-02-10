package frc.robot.Vision.Limelight;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Limelight {
    // These numbers must be tuned for your Robot! Be careful!
    final static double STEER_K = 0.01; // how hard to turn toward the target
    final static double DRIVE_K = 0.1; // how hard to drive fwd toward the target
    final static double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
    final static double MAX_DRIVE = 0.6; // Simple speed limit so we don't drive too fast

    /**
    * This function returns whether the limelight has any valid targets (0 or 1)
    * @return Target status (0/1)
     */
    public static double getTV() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    }

    /**
    * This function returns whether there is a target detected by the camera
    * @return Target status (boolean)
     */
    public static boolean hasTarget() {
      return getTV() == 1.0;
    }

    /**
    * This function returns the Horizontal Offset From Crosshair To Target
    *-27 degrees to 27 degrees
    * @return Horizontal Offset From Crosshair To Target
     */
    public static double getTX() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    }

    /**
    * This function returns the Vertical Offset From Crosshair To Target
    *-20.5 degrees to 20.5 degrees
    * @return Vertical Offset From Crosshair To Target
     */
    public static double getTY() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    }

    /**
    * This function returns the Target Area
    * @return Target Area
     */
    public static double getTA() {
      return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
    }

    /**
    * This function returns the forward speed to approach the target
    * @return Forward Speed
     */
    public static double getForwardSpeed() {
      if(hasTarget()){
        // try to drive forward until the target area reaches our desired area
        double forwardSpeed = (DESIRED_TARGET_AREA - getTA()) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (forwardSpeed > MAX_DRIVE)
        {
          forwardSpeed = MAX_DRIVE;
        }
        return -forwardSpeed;
      }
      return 0;
    }

    /**
    * This function returns theturning speed to approach the target
    * @return Turning Speed
     */
    public static double getTurnSpeed(){
      if(hasTarget()){
      // Start with proportional steering
        double turnSpeed = getTX() * STEER_K;
        return turnSpeed;
      }
      return 0;
    }
}
