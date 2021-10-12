/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.JoystickConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.FieldOrientedDrive;
import frc.robot.commands.Intake;
import frc.robot.commands.Shooter;
import frc.robot.commands.TrajectoryCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.NAVXSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  Joystick m_joystick1 = new Joystick(JoystickConstants.kJoysick1Port);
  Joystick m_joystick2 = new Joystick(JoystickConstants.kJoystick2Port);
  
  //subsystems
  private final DriveSubsystem m_drive = new DriveSubsystem();
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  private final ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  final NAVXSubsystem navx = new NAVXSubsystem();
  //commands
  private final FieldOrientedDrive m_FOD = new FieldOrientedDrive(m_drive, () -> m_joystick1.getRawAxis(JoystickConstants.kXStick2), () -> m_joystick1.getRawAxis(JoystickConstants.kYStick1), () -> m_joystick1.getRawAxis(JoystickConstants.kXStick1), () -> navx.getAngle());
  //chooser
  private SendableChooser<Command> autonomousChooser = new SendableChooser<Command>();
  private SendableChooser<Command> driveChooser = new SendableChooser<Command>();
  //limelight stuff
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ta = table.getEntry("ta");

  

  
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    //sets up our auto chooser(see the classes for details)
    autonomousChooser.setDefaultOption("Trajectory", new TrajectoryCommand());
    //autonomousChooser.addOption("Shoot Left Of Target", a_autoDriveToLineAndShootLeft);
    //sets up drive chooser with the option between FOD and default
    driveChooser.setDefaultOption("Field Oriented Drive", m_FOD);
    //driveChooser.addOption("Default Drive", m_default);
    //publishes the choosers
    SmartDashboard.putData("Autonomous Mode", autonomousChooser);
    SmartDashboard.putData("Drive Mode", driveChooser);
    // Configure the button bindings
    configureButtonBindings();
    
    //sets the drive to what it should be
    m_drive.setDefaultCommand(driveChooser.getSelected());

    //guess
    SmartDashboard.putNumber("navx", navx.getAngle());
    
    }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_joystick1, 1)
      .whenPressed(new Intake(m_intakeSubsystem, IntakeConstants.kIntakeSpeed))
      .whenReleased(new Intake(m_intakeSubsystem, 0));
    
    new JoystickButton(m_joystick1, 2)
      .whileHeld(new Shooter(m_shooterSubsystem, ShooterConstants.kIdealShotSpeed));
    
    //new JoystickButton(m_joystick1, 3)
    //.whileHeld(new DriveToTargetLimelight(m_drive, navx));

    //new JoystickButton(m_joystick1, 4)
    //.whileHeld(new SequentialCommandGroup(
    //  new DriveToTargetLimelight(m_drive, navx),
    //  new ShootWithIndex(m_shooterSubsystem)
    //));
    
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autonomousChooser.getSelected();
  }
}
