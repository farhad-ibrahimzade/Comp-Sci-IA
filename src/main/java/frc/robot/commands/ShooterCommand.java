package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;

public class ShooterCommand extends CommandBase{
    ShooterSubsystem m_shooter;
    private final double m_speed;
    
    PIDController pid = new PIDController(ShooterConstants.kP, ShooterConstants.kI, ShooterConstants.kD);

    public ShooterCommand(ShooterSubsystem shooter, double speed) {
        m_shooter = shooter;
        m_speed = speed;
        addRequirements(m_shooter);
    }
    @Override
    public void execute() {

      while(!pid.atSetpoint()){

          m_shooter.shoot(pid.calculate(m_shooter.getVelocity(), ShooterConstants.shooterSpeed));

      }

    }
    
}
