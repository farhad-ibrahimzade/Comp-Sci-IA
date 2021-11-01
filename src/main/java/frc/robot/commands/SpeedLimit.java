package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class SpeedLimit extends CommandBase {
    private DriveSubsystem m_drive;

    private boolean m_status = false;

    public SpeedLimit(DriveSubsystem drive, boolean state) {
        m_drive = drive;
        m_status = state;
        addRequirements(drive);
    }
    @Override
    public void execute() {
        if(m_status){
            m_drive.speedLimit(0.4);
        }
        else{
            m_drive.speedLimit(1);
        }
        
        
    }
    
}
