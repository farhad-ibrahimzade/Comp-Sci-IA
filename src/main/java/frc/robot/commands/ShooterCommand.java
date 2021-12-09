package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;

public class ShooterCommand extends CommandBase extends PIDSubsystem {
    ShooterSubsystem m_shooter;
    private final double m_speed;

    public ShooterCommand(ShooterSubsystem shooter, double speed) {
        m_shooter = shooter;
        m_speed = speed;
        addRequirements(m_shooter);
    }
    @Override
    public void execute() {
        m_shooter.shoot(m_speed);
        
    }
    @Override
  public void useOutput(double output, double setpoint) {
    m_shooter.setVoltage(output + m_shooterFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    return m_shooter.getRate();
  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }
    
}
