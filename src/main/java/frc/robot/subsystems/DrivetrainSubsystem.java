package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXSensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.opencv.video.SparsePyrLKOpticalFlow;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class DrivetrainSubsystem extends SubsystemBase {
    private TalonFX leftFalcon1, rightFalcon1, leftFalcon2, rightFalcon2;

    public DrivetrainSubsystem() {
        
        leftFalcon1 = new TalonFX(Constants.LEFT_FALCON_1);
        leftFalcon2 = new TalonFX(Constants.LEFT_FALCON_2);
        rightFalcon1 = new TalonFX(Constants.RIGHT_FALCON_1);
        rightFalcon2 = new TalonFX(Constants.RIGHT_FALCON_2);
        leftFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,0);
        rightFalcon1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor,0,0);
    }

    public void drive(double leftPower, double rightPower) {

       leftFalcon1.set(ControlMode.PercentOutput, leftPower);
       leftFalcon2.set(ControlMode.PercentOutput, leftPower);
       rightFalcon1.set(ControlMode.PercentOutput, rightPower);
       rightFalcon2.set(ControlMode.PercentOutput, rightPower);
    }

    public void stopDrive() {

        leftFalcon1.set(ControlMode.PercentOutput, 0);
        leftFalcon2.set(ControlMode.PercentOutput, 0);
        rightFalcon1.set(ControlMode.PercentOutput, 0);
        rightFalcon2.set(ControlMode.PercentOutput, 0);
    }

    public double getLeftEncoder() {
        return leftFalcon1.getSelectedSensorPosition();
    }

    public double getRightEncoder() {
        return rightFalcon1.getSelectedSensorPosition();
    }
    public double getAverageEncoder() {
        return (getLeftEncoder() + getRightEncoder() ) / 2;
    }

    public void resetEncoder() {
        leftFalcon1.setSelectedSensorPosition(0);
        rightFalcon1.setSelectedSensorPosition(0);
    }

    public void driveWithMinPower(double leftPower, double rightPower, double minAbsolutePower) {
        double realLeftPower = (leftPower / Math.abs(leftPower)) * Math.max(Math.abs(leftPower), minAbsolutePower);
        double realRightPower = (rightPower / Math.abs(rightPower)) * Math.max(Math.abs(rightPower), minAbsolutePower);
    }
    @Override
    public void periodic() {
        double leftY = Robot.robotContainer.leftJoy.getY();
        double rightY = Robot.robotContainer.rightJoy.getY();
        drive(-leftY, rightY);

        // System.out.println("drivetrain periodic");

        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

//post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }

}

