package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private TalonSRX shooterLeftTalon;
    private TalonSRX shooterRightTalon;
    private Servo hoodServo1;
    private Servo hoodServo2;

    public ShooterSubsystem() {
        shooterLeftTalon = new TalonSRX(Constants.SHOOTER_1_TALON);
        shooterRightTalon = new TalonSRX(Constants.SHOOTER_2_TALON);
        hoodServo1 = new Servo(Constants.SHOOTER_SERVO_1);
        hoodServo2 = new Servo(Constants.SHOOTER_SERVO_2);
        shooterLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        shooterRightTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void shoot(){
        shooterLeftTalon.set(ControlMode.PercentOutput, Constants.SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.PercentOutput, Constants.SHOOT_2_SPEED);
    }

    public void reverseShoot(){
        shooterLeftTalon.set(ControlMode.PercentOutput, Constants.REVERSE_SHOOT_1_SPEED);
        shooterRightTalon.set(ControlMode.PercentOutput, Constants.REVERSE_SHOOT_2_SPEED);
    }

    public void stopShoot(){
        shooterLeftTalon.set(ControlMode.PercentOutput, 0);
        shooterRightTalon.set(ControlMode.PercentOutput, 0);
    }

    public double getLeftEncoder(){
        return shooterLeftTalon.getSelectedSensorVelocity();
    }

    public double getRightEncoder(){
        return shooterRightTalon.getSelectedSensorVelocity();
    }

    public double getAverageEncoder(){
        return (getLeftEncoder() + getRightEncoder())/2;
    }

    public void testServo(){
        hoodServo1.setAngle(45);
    }

    public void setHoodAngle(double angle){
        hoodServo1.setAngle(angle);
        hoodServo2.setAngle(180-angle);
    }

    public double getHoodAngle(){
        return hoodServo1.getAngle();
    }

    public double calcHoodAngle(){
        return Math.toDegrees(Math.asin(Math.sqrt(Constants.SHOOTER_HEIGHT * 2 * Constants.GRAVITY) / Constants.SHOOT_1_SPEED));
    }

}

