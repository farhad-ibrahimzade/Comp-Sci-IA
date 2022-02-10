package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Robot;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
    private  CANSparkMax m_intake = new CANSparkMax(IntakeConstants.kIntakePort, MotorType.kBrushed);

    private static int count = 0;
    /**
     * this is the class for the intake
     * thats it its really simple
     */
    public IntakeSubsystem() {

    }
    
    /**
     * will i keep the name as succ? probably can you change it? no.
     * 
     * @param speed = speed at which succage occurs
     * @throws InterruptedException
     */
    public void succ(double speed){
        if(!pickedUp()){
            m_intake.set(speed);
        }
        else{
            m_intake.set(speed);
            Robot.wait(2000);
            m_intake.set(0);
            count++;
        }
    }

    public boolean pickedUp(){
        //add code to notify that element picked up
        return SonarSubsystem.getInches() < 10;
    }
    public int getCount(){
        return count;
    }

    public static void shotOne(){
        count--;
    }
}