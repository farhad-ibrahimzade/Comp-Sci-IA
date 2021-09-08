// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Bot.Bot;
import frc.robot.OpMode.Main_Auto;
import frc.robot.OpMode.Main_TeleOp;
import frc.robot.OpMode.Test_Hardware;
/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    Main_Auto.init();
    Main_Auto.execute();
  }

  @Override
  public void autonomousPeriodic() {
    
  }

  @Override
  public void teleopInit() {
    Main_TeleOp.init();
  }

  @Override
  public void teleopPeriodic() {
    Main_TeleOp.execute();
  }

  @Override
  public void disabledInit() {
    Bot.motorMode(false);
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {
    Test_Hardware.init();
  }

  @Override
  public void testPeriodic() {
    Test_Hardware.execute();
  }
  /*
    // Simulation support

    DrivetrainSim dtSim;

    @Override
    public void simulationInit() {
        dtSim = new DrivetrainSim();
    }

    @Override
    public void simulationPeriodic() {
        dtSim.update();
    } */
}
