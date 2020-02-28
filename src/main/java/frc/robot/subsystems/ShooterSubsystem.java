package frc.robot.subsystems;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {

    private TalonFX shooterLeftFalcon;
    private TalonFX shooterRightFalcon;
    public Servo hoodServo1;
    private Servo hoodServo2;

    public ShooterSubsystem() {
        shooterLeftFalcon = new TalonFX(Constants.SHOOTER_1_FALCON);
        shooterRightFalcon = new TalonFX(Constants.SHOOTER_2_FALCON);
        hoodServo1 = new Servo(Constants.SHOOTER_SERVO_1);
        hoodServo2 = new Servo(Constants.SHOOTER_SERVO_2);
        shooterLeftFalcon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
        shooterRightFalcon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    }

    public void shoot() {
        shooterLeftFalcon.set(ControlMode.PercentOutput, -Constants.SHOOT_1_SPEED);
        shooterRightFalcon.set(ControlMode.PercentOutput, Constants.SHOOT_2_SPEED);
    }

    public void reverseShoot() {
        shooterLeftFalcon.set(ControlMode.PercentOutput, -Constants.REVERSE_SHOOT_1_SPEED);
        shooterRightFalcon.set(ControlMode.PercentOutput, Constants.REVERSE_SHOOT_2_SPEED);
    }

    public void stopShoot() {
        shooterLeftFalcon.set(ControlMode.PercentOutput, 0);
        shooterRightFalcon.set(ControlMode.PercentOutput, 0);
    }

    public double getLeftEncoder() {
        return shooterLeftFalcon.getSelectedSensorVelocity();
    }

    public double getRightEncoder() {
        return shooterRightFalcon.getSelectedSensorVelocity();
    }

    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder()) / 2;
    }

    public void resetServo() {
        hoodServo1.set(0.5);
    }

    public void stopServo() {
        hoodServo1.setSpeed(0);
    }

    public void setServo(double pos) {
        pos *= Constants.SERVO_RATIO;
        hoodServo1.setAngle(pos);
//        hoodServo2.setAngle(180 - pos);
    }
    
    public double getHoodPosition() {
        return hoodServo1.getPosition();
        }

    public void setHoodAngle(double angle) {
        angle *= Constants.SERVO_RATIO;
        hoodServo1.setAngle(angle);
        hoodServo2.setAngle(180 - angle);
    }

    public double getHoodAngle() {
        return hoodServo1.getAngle() * Constants.SERVO_GEAR_RATIO; 
    }

    public double calcHoodAngle() {
        return Math.toDegrees(Math.asin(Math.sqrt(Constants.SHOOTER_HEIGHT * 2 * Constants.GRAVITY) / Constants.SHOOT_1_SPEED));
    }

    public double getSpeed() {
        return shooterLeftFalcon.getMotorOutputPercent();
    }

}
