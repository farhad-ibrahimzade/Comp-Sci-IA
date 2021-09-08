package frc.robot.Bot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.kinematics.MecanumDriveMotorVoltages;
import frc.robot.Navigation.Encoders;
import frc.robot.Navigation.Pid;

public class Drivetrain {
    //initialize motor controllers
    public static CANSparkMax leftMotor1 = new CANSparkMax(2, MotorType.kBrushless); //front left
    public static CANSparkMax leftMotor2 = new CANSparkMax(6, MotorType.kBrushless); //front right
    public static CANSparkMax rightMotor1 = new CANSparkMax(3, MotorType.kBrushless); //back left
    public static CANSparkMax rightMotor2 = new CANSparkMax(5, MotorType.kBrushless); //back right

    //initialize the mecanum drivetrain
    private static MecanumDrive m_drive = new MecanumDrive(leftMotor1, leftMotor2, rightMotor1, rightMotor2);
    
    /**
     * A method that initializes the drivetrain by setting conversion values, motor
     * directions, etc.
     *
     */
    public static void init() {
        
    }

    /**
     * A method that drives the Mecanum drivetrain based on three paramethers
     *
     * @param RX X-axis value of the right joystick - translated into rotational
     *           speed (clockwise is positive).
     * @param RY Y-axis value of the right joystick - translated into speed along
     *           the vertical axis (forward is positive).
     * @param LX X-axis value of the left joystick - translated into speed along
     *           the horizontal axis (right is positive).
     */
    public static void drive(double RX, double RY, double LX) {
        m_drive.driveCartesian(-RY * Constants.speed, LX * Constants.speed, RX * Constants.speed);
    }

    /**
     * A method that drives the Mecanum drivetrain based on three paramethers
     *
     * @param RX X-axis value of the right joystick - translated into speed along
     *           the horizontal axis (right is positive).
     * @param RY Y-axis value of the right joystick - translated into speed along
     *           the vertical axis (forward is positive).
     * @param LX X-axis value of the left joystick - translated into rotational
     *           speed (clockwise is positive).
     */
    public static void driveC(double RX, double RY, double LX, double gyro) {
        m_drive.driveCartesian(RX * Constants.speed, RY * Constants.speed, LX * Constants.speed, gyro);
    }

    /**
     * A method that applies a power value to all 4 motors on the drivetrain (the
     * power for the right side is automatically reversed)
     *
     * @param value The power applied to the motors (between -1.0 and 1.0)
     */
    public static void setValue(double value) {
        leftMotor1.set(-value);
        leftMotor2.set(value);
        rightMotor1.set(-value);
        rightMotor2.set(value);
    }

    /**
     * A method that stops the robot by applying a zero power value to all motors.
     */
    public static void stop() {
        leftMotor1.set(0);
        leftMotor2.set(0);
        rightMotor1.set(0);
        rightMotor2.set(0);
    }

    /**
     * A method that sets the zero-power mode for the drivetrain motors: either
     * coast or brake. Brake is typically recommended when robot is active, and
     * coast for when robot is turned off.
     *
     * @param on True for brake and false for coast.
     */
    public static void motorMode(boolean on) {
        IdleMode mode;
        if (on)
            mode = IdleMode.kBrake;
        else
            mode = IdleMode.kCoast;
        leftMotor1.setIdleMode(mode);
        leftMotor2.setIdleMode(mode);
        rightMotor1.setIdleMode(mode);
        rightMotor2.setIdleMode(mode);
    }

    /**
     * A method that will move the drivetrain a certain distance using a PID
     * controller.
     *
     * @param distance Distance in inches.
     */
    public static void PIDdrive(double distance) {
        Pid.setSetpoint(distance);
        while (!Pid.atSetpoint()) {
            setValue(Pid.calculate(Encoders.getPosition()));
        }
        stop();
    }

    public static void setVolts(MecanumDriveMotorVoltages volts){
        leftMotor1.setVoltage(volts.frontLeftVoltage);
        leftMotor2.setVoltage(volts.rearLeftVoltage);
        rightMotor1.setVoltage(volts.frontRightVoltage);
        rightMotor2.setVoltage(volts.rearRightVoltage);
    }

    public static void speedControl(double speed){
        Constants.speed = speed;
    }
}
