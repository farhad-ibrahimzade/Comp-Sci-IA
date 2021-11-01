package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
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
    
}
