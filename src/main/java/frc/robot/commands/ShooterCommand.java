package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class ShooterCommand extends CommandBase{
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
        m_shooter.speedUp();
        for(int i=0; i<=2; i++){

            m_shooter.moveIndex(1);
            Robot.wait(1000);
            m_shooter.moveIndex(0);
            Robot.wait(1000);
            IntakeSubsystem.shotOne();
        }
        m_shooter.shoot(0);
        m_shooter.speedUp();
    }
    
}
