package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class Shooter extends CommandBase {
    ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final double m_speed;

    public Shooter(ShooterSubsystem shooter, double speed) {
        m_shooter = shooter;
        m_speed = speed;
        addRequirements(shooter);
    }
    @Override
    public void execute() {
        m_shooter.shoot(m_speed);
        
    }
    
}
